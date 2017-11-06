package model;

import utils.ImyDict;
import utils.ImyList;
import utils.ImyStack;
import utils.ImyTuple;

import java.io.BufferedReader;

import exceptions.PrgStateException;
import statements.IStatement;

public interface IprgState {
	
	public ImyStack<IStatement> getExeStack();
	public ImyDict<String, Integer> getSymTable();
	public ImyList<Integer> getOutput();
	public ImyDict<Integer, ImyTuple<String, BufferedReader>> getFileTable();
	public ImyDict<Integer, Integer> getHeap();
	
	public IStatement popExeStack() throws PrgStateException;
	public Boolean stackIsEmpty();
	
	public void setExeStack(ImyStack<IStatement> e);
	public void setSynTable(ImyDict<String, Integer> d);
	public void setOutput(ImyList<Integer> o);
	public void setFileTable(ImyDict<Integer, ImyTuple<String, BufferedReader>> f);
	public void setHeap(ImyDict<Integer, Integer> h);
	
	public String ExeStackStr();
	public String SymTableStr();
	public String OutputStr();
	public String FileTableStr();
	public String HeapStr();
	
	public String toString();
}
