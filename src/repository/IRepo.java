package repository;

import java.util.List;

import exceptions.RepoException;
import model.IprgState;
import statements.IStatement;

public interface IRepo {
	
	public List<IprgState> getPrgList() throws RepoException;
	public void setPrgList(List<IprgState> newList);
	
	public IStatement popExeStack(IprgState state) throws RepoException;
	public Boolean stackIsEmpty(IprgState state) throws RepoException;	
	
	public void logPrgStateExec(IprgState state) throws RepoException;
	public void logException(Exception e) throws RepoException;
	
	public void setLogPath(String path);
	public String getPath();
	
}
