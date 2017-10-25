package model;

import utils.ImyDict;
import utils.ImyList;
import utils.ImyStack;
import exceptions.PrgStateException;
import model.IStatement;

public interface IprgState {
	
	public ImyStack<IStatement> getExeStack();
	public ImyDict<String, Integer> getSymTable();
	public ImyList<Integer> getOutput();
	
	public IStatement popExeStack() throws PrgStateException;
	public Boolean stackIsEmpty();
	
	public void setExeStack(ImyStack<IStatement> e);
	public void setSynTable(ImyDict<String, Integer> d);
	public void setOutput(ImyList<Integer> o);
	
	public String ExeStackStr();
	public String SymTableStr();
	public String OutputStr();
	
	public String toString();
}
