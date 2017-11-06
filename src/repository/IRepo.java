package repository;

import exceptions.RepoException;
import model.IprgState;
import statements.IStatement;

public interface IRepo {
	
	public IprgState getCurrentProgram() throws RepoException;
	public IStatement popExeStack() throws RepoException;
	public void setCurrentProgram(IprgState p) throws RepoException;
	public Boolean stackIsEmpty() throws RepoException;	
	
	public void logPrgStateExec() throws RepoException;
	public void setLogPath(String path);
	public String getPath();
}
