package model;


import java.io.BufferedReader;

import exceptions.PrgStateException;
import exceptions.StackException;
import statements.IStatement;
import utils.ImyDict;
import utils.ImyList;
import utils.ImyStack;
import utils.ImyTuple;

public class PrgState implements IprgState{

	ImyStack<IStatement> stk;
	ImyDict<String, Integer> symTable;
	ImyList<Integer> output;
	ImyDict<Integer, ImyTuple<String, BufferedReader>> fileTable;
	IStatement originalProgram;
	
	public PrgState(ImyStack<IStatement> stk, ImyDict<String, Integer> symTable, ImyList<Integer> output, ImyDict<Integer, ImyTuple<String, BufferedReader>> fileTable, IStatement o){
		this.stk = stk;
		this.symTable = symTable;
		this.output = output;
		this.fileTable = fileTable;
		this.originalProgram = o;
		this.stk.push(originalProgram);
	}
	
	public ImyStack<IStatement> getExeStack(){
		return this.stk;
	}
	
	public ImyDict<String, Integer> getSymTable(){
		return this.symTable;
	}
	public ImyList<Integer> getOutput(){
		return this.output;
	}
	
	@Override
	public ImyDict<Integer, ImyTuple<String, BufferedReader>> getFileTable() {
		return this.fileTable;
	}
	
	
	public IStatement popExeStack() throws PrgStateException{
		try {
			return this.stk.pop();
		} catch (StackException e) {
			throw new PrgStateException("Empty stack");
		}
	}
	
	public Boolean stackIsEmpty(){
		return this.stk.isEmpty();
	}
	
	public void setExeStack(ImyStack<IStatement> e){
		this.stk = e;
	}
	public void setSynTable(ImyDict<String, Integer> d){
		this.symTable = d;
	}
	public void setOutput(ImyList<Integer> o){
		this.output = o;
	}

	public void setFileTable(ImyDict<Integer, ImyTuple<String, BufferedReader>> f) {
		this.fileTable = f;
	}
	
	public String ExeStackStr(){
		return stk.toString();
	}
	public String SymTableStr(){
		return symTable.toString();
	}
	public String OutputStr(){
		return output.toString();
	}
	
	public String FileTableStr() {
		return fileTable.toString();
	}
	
	@Override
	public String toString(){
		String str = "";
		str += this.ExeStackStr();
		str += this.SymTableStr();
		str += this.OutputStr();
		str += this.FileTableStr();
		str += '\n';
		return str;
		
	}

	
	
	
}
