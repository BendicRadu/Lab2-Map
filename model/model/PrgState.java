package model;


import java.io.BufferedReader;

import exceptions.PrgStateException;
import exceptions.StackException;
import exceptions.StmtException;
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
	ImyDict<Integer, Integer> heap;
	IStatement originalProgram;
	int id;
	int idBuffer = 0;
	
	public PrgState(ImyStack<IStatement> stk, ImyDict<String, Integer> symTable, ImyList<Integer> output, ImyDict<Integer, ImyTuple<String, BufferedReader>> fileTable, ImyDict<Integer, Integer> heap, IStatement o, int id){
		this.stk = stk;
		this.symTable = symTable;
		this.output = output;
		this.fileTable = fileTable;
		this.heap = heap;
		this.id = id;
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
	
	@Override
	public ImyDict<Integer, Integer> getHeap(){
		return this.heap;
	}
	
	@Override
	public int getId(){
		return this.id;
	}
	
	public int getIdBuffer(){
		return this.idBuffer;
	}
	
	public void setIdBuffer(int value){
		this.idBuffer = value;
	}
	
	public IStatement popExeStack() throws PrgStateException{
		try {
			return this.stk.pop();
		} catch (StackException e) {
			throw new PrgStateException("Empty stack");
		}
	}
	
	public Boolean isNotCompleted(){
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
	
	public void setHeap(ImyDict<Integer, Integer> h){
		this.heap = h;
	}
	
	
	public IprgState oneStep() throws PrgStateException{
		
		IStatement crtStmt;
		try {
			crtStmt = stk.pop();
		} catch (StackException e) {
			throw new PrgStateException("Empty stack in program: " + this.toString());
		}
		 try {
			return crtStmt.execute(this);
		} catch (StmtException e) {
			throw new PrgStateException("Statement \"" + crtStmt.toString() +  " has encountered an exception: " + e.getMessage());
			
		}
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
	
	public String HeapStr(){
		return heap.toString();
	}
	
	@Override
	public String toString(){
		String str = "";
		str += "PID: " + this.id + "\n";
		str += this.ExeStackStr();
		str += this.SymTableStr();
		str += this.OutputStr();
		str += this.FileTableStr();
		str += this.HeapStr();
		str += '\n';
		return str;
		
	}

	
	
	
}
