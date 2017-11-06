package controller;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import exceptions.CtrlException;
import exceptions.RepoException;
import exceptions.StmtException;
import model.IprgState;
import repository.IRepo;
import statements.IStatement;

public class Ctrl {
	
	IRepo r;
	
	public Ctrl(IRepo r){
		this.r = r;
	}
	
	Map<Integer,Integer> conservativeGarbageCollector(Collection<Integer> symTableValues, Map<Integer,Integer> heap){
			return heap.entrySet().stream()
			 .filter(e->symTableValues.contains(e.getKey()))
			 .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}

	public void oneStepEval() throws CtrlException{
		try {
			
			IprgState p = r.getCurrentProgram();
			IStatement s = r.popExeStack();
		
			p = s.execute(p);
			r.setCurrentProgram(p);
		
			this.logPrgStateExec();
			
		} catch (RepoException r) {
			throw new CtrlException("Cotroller received a repo exeption: " + r.getMessage());
			
		}  catch (StmtException e) {
			throw new CtrlException("Cotroller received a statement exeption: " + e.getMessage());
		}
		
	}
	
	public void completeEval() throws CtrlException{
		
		while(true){
			try {
				
				IprgState p = r.getCurrentProgram();
				IStatement s = r.popExeStack();
			
				p = s.execute(p);
				p.getHeap().setContent(conservativeGarbageCollector(p.getSymTable().values(), p.getHeap().getContent()));
				r.setCurrentProgram(p);
				this.logPrgStateExec();
			
			} catch (RepoException r) {
				throw new CtrlException("Cotroller received a repo exeption: " + r.getMessage());
				
			}  catch (StmtException e) {
				throw new CtrlException("Cotroller received a statement exeption: " + e.getMessage());
			}
		}
	}
	
	public IprgState getCurrentProgram() throws CtrlException{
		try {
			return r.getCurrentProgram();
		} catch (RepoException r) {
			throw new CtrlException("Cotroller received a repo exeption: " + r.getMessage());
		}
	}
	
	public void setLogPath(String path){
		this.r.setLogPath(path);
	}
	
	public String getLogPath(){
		return this.r.getPath();
	}
	
	public void logPrgStateExec() throws CtrlException{
		try {
			this.r.logPrgStateExec();
		} catch (RepoException r) {
			throw new CtrlException("Cotroller received a repo exeption: " + r.getMessage());
		}
	} 
	
}
