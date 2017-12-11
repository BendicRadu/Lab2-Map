package controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import exceptions.CtrlException;
import exceptions.RepoException;
import model.IprgState;
import repository.IRepo;

public class Ctrl {
	
	public IRepo r;
	ExecutorService executor;
	
	public Ctrl(IRepo r){
		this.r = r;
	}
	
	public IRepo getRepo(){
		return this.r;
	}
	
	public List<IprgState> getPrgList() throws CtrlException{
		try {
			return r.getPrgList();
		} catch (RepoException e) {
			throw new CtrlException("Controller recieved a repo exception: " + e.getMessage());
		}
	}
	
	Map<Integer,Integer> conservativeGarbageCollector(Collection<Integer> symTableValues, Map<Integer,Integer> heap){
			return heap.entrySet().stream()
			 .filter(e->symTableValues.contains(e.getKey()))
			 .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}
	
	List<IprgState> removeCompletedPrg(List<IprgState> inPrgList){
		return inPrgList.stream()
				.filter(p -> ! p.isNotCompleted())
				.collect(Collectors.toList());
	}

	
	public void oneStepForAllPrg(List<IprgState> prgList) throws CtrlException, InterruptedException{
		
	
		for(IprgState prg: prgList){
			try {
				r.logPrgStateExec(prg);
			} catch (RepoException e) {
				throw new CtrlException("Controller encountered a repo exception: " + e.getMessage());
			}
		}
		
		
		List<Callable<IprgState>> callList = prgList.stream()
				.map((IprgState p) -> (Callable<IprgState>)(() -> {
					return p.oneStep();
					}
				))
				.collect(Collectors.toList());
		
		/*
		List<IprgState> newPrgList = executor.invokeAll(callList).stream()
				 . map(future -> { 
					 	try {
							return future.get();
						} catch (Exception e){
							
							try {
								r.logException(e);
							} catch (RepoException e1) {
								e1.printStackTrace();
							}
							
							return null;
						}
					} 
				 )
				 .filter(p -> p != null)
				 .collect(Collectors.toList());
		*/
		
		List<IprgState> newPrgList = new ArrayList<IprgState>();
		List<Future<IprgState>> execList = executor.invokeAll(callList);
		
		for(Future<IprgState> f: execList){
			try {
				if( f.get() != null ){
					newPrgList.add(f.get());
				}
			} catch (ExecutionException e) {
				throw new CtrlException("Controller recieved an execution exception: " + e.getMessage());
			}
		}
		
		prgList.addAll(newPrgList);
		
		prgList.forEach(prg ->{
			try {
				r.logPrgStateExec(prg);
			} catch (RepoException e) {
				try {
					r.logException(e);
				} catch (RepoException e1) {}
			}
		});
		r.setPrgList(prgList);
	}
	
	public void completeEval() throws CtrlException{
		
		executor = Executors.newFixedThreadPool(2);
	
		List<IprgState> prgList;
		try {
			prgList = removeCompletedPrg( r.getPrgList() );
		} catch (RepoException e) {
			throw new CtrlException("Controller encountered a repo exception: " + e.getMessage());
		}
		while(prgList.size() > 0){
			try {
				this.oneStepForAllPrg(prgList);
			} catch (InterruptedException e) {
				throw new CtrlException("Controller encountered an Interrupted exception: " + e.getMessage());
			}
			try {
				prgList = removeCompletedPrg( r.getPrgList() );
			} catch (RepoException e) {
				throw new CtrlException("Controller encountered a repo exception: " + e.getMessage());
			}
		}
		executor.shutdownNow();
		r.setPrgList(prgList);
		}
	
	public void setLogPath(String path){
		this.r.setLogPath(path);
	}
	
	public String getLogPath(){
		return this.r.getPath();
	}
	
	public void logPrgStateExec(IprgState state) throws CtrlException{
		try {
			this.r.logPrgStateExec(state);
		} catch (RepoException r) {
			throw new CtrlException("Cotroller received a repo exeption: " + r.getMessage());
		}
	} 
	
}
