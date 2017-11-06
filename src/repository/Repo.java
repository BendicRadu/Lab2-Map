package repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import exceptions.ListException;
import exceptions.PrgStateException;
import exceptions.RepoException;
import model.IprgState;
import statements.IStatement;
import utils.ImyList;
import utils.MyList;

public class Repo implements IRepo{
	
	ImyList<IprgState> prgList;
	int index = 0;
	String logPath;
	
	public Repo(IprgState originalState){
		this.prgList = new MyList<IprgState>();
		this.prgList.add(originalState);
	}

	@Override
	public IprgState getCurrentProgram() throws RepoException{
		try {
			return prgList.get(index);
		} catch (ListException e) {
			throw new RepoException("No program at " + index + " index");
		}
	}
	
	@Override
	public IStatement popExeStack() throws RepoException{
		try {
			return this.getCurrentProgram().popExeStack();
		} catch (PrgStateException e) {
			throw new RepoException("Empty stack");
		} catch (RepoException r) {
			throw r;
		}
	}
	
	@Override
	public Boolean stackIsEmpty() throws RepoException{
		try {
			return this.getCurrentProgram().stackIsEmpty();
		} catch (RepoException e) {
			throw new RepoException("No program at " + index + " index");
		}
	}
	
	@Override
	public void setCurrentProgram(IprgState p) throws RepoException{
		try {
			prgList.set(0, p);
		} catch (ListException e) {
			throw new RepoException("Program at index: " + index + " is missing");
		}
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
	public void logPrgStateExec() throws RepoException{
		try {
			
			java.io.File f = new java.io.File(this.logPath);
			if(!f.exists() || f.isDirectory())
				throw new RepoException("IOException: File does not exist (" + this.logPath + ")");
				
			PrintWriter logFile= new PrintWriter(new BufferedWriter(new FileWriter(this.logPath, true)));
			logFile.print(this.getCurrentProgram().toString());
			logFile.close();
		} catch (IOException e) {
			throw new RepoException("IOException: " + e.getMessage());
		} 
	}
	
	
	
}
