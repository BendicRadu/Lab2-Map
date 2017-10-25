package controller;

import exceptions.CtrlException;
import exceptions.RepoException;
import exceptions.StmtException;
import model.IStatement;
import model.IprgState;
import repository.IRepo;

public class Ctrl {
	
	IRepo r;
	
	public Ctrl(IRepo r){
		this.r = r;
	}
	
	public void oneStepEval() throws CtrlException{
		try {
			
			IprgState p = r.getCurrentProgram();
			IStatement s = r.popExeStack();
		
			p = s.execute(p);
			r.setCurrentProgram(p);
		
			this.logPrgStateExec();
			
		} catch (RepoException r) {
			throw new CtrlException("Cotroller recieved a repo exeption: " + r.getMessage());
			
		}  catch (StmtException e) {
			throw new CtrlException("Cotroller recieved a statement exeption: " + e.getMessage());
		}
		
	}
	
	public void completeEval() throws CtrlException{
		
		while(true){
			try {
				
				IprgState p = r.getCurrentProgram();
				IStatement s = r.popExeStack();
			
				p = s.execute(p);
				r.setCurrentProgram(p);

				this.logPrgStateExec();
			
			} catch (RepoException r) {
				throw new CtrlException("Cotroller recieved a repo exeption: " + r.getMessage());
				
			}  catch (StmtException e) {
				throw new CtrlException("Cotroller recieved a statement exeption: " + e.getMessage());
			}
		}
	}
	
	public IprgState getCurrentProgram() throws CtrlException{
		try {
			return r.getCurrentProgram();
		} catch (RepoException r) {
			throw new CtrlException("Cotroller recieved a repo exeption: " + r.getMessage());
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
			throw new CtrlException("Cotroller recieved a repo exeption: " + r.getMessage());
		}
	} 
	
}
