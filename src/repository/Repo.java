package repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import exceptions.PrgStateException;
import exceptions.RepoException;
import model.IprgState;
import statements.IStatement;

public class Repo implements IRepo{
	
	List<IprgState> prgList;
	String logPath;
	
	public Repo(IprgState originalState){
		this.prgList = new ArrayList<IprgState>();
		this.prgList.add(originalState);
	}

	@Override
	public List<IprgState> getPrgList() throws RepoException{
	
		if(prgList == null){
			throw new RepoException("No program list");
		}
		return prgList;
	}
	
	@Override
	public void setPrgList(List<IprgState> newList){
		this.prgList = newList;
	}
	
	@Override
	public IStatement popExeStack(IprgState state) throws RepoException{
		try {
			return state.popExeStack();
		} catch (PrgStateException e) {
			throw new RepoException("Empty stack");
		}
	}
	
	@Override
	public Boolean stackIsEmpty(IprgState state) throws RepoException{
		return state.isNotCompleted();
	}
	
	
	@Override
	public void setLogPath(String path){
		this.logPath = path;
	}
	
	@Override
	public String getPath(){
		return this.logPath;
	}
	
	@Override
	public void logPrgStateExec(IprgState state) throws RepoException{
		try {
			
			java.io.File f = new java.io.File(this.logPath);
			if(!f.exists() || f.isDirectory())
				throw new RepoException("IOException: File does not exist (" + this.logPath + ")");
				
			PrintWriter logFile= new PrintWriter(new BufferedWriter(new FileWriter(this.logPath, true)));
			logFile.print(state.toString());
			logFile.close();
		} catch (IOException e) {
			throw new RepoException("IOException: " + e.getMessage());
		} 
	}
	
	@Override
	public void logException(Exception e) throws RepoException{
		
		try{
		
		java.io.File f = new java.io.File(this.logPath);
		if(!f.exists() || f.isDirectory())
			throw new RepoException("IOException: File does not exist (" + this.logPath + ")");
		
		PrintWriter logFile= new PrintWriter(new BufferedWriter(new FileWriter(this.logPath, true)));
		logFile.print(e.getMessage());
		logFile.close();
		} catch (IOException e1) {
			throw new RepoException("IOException: " + e1.getMessage());
		} 
		
	}
	
	
	
	
	
}
