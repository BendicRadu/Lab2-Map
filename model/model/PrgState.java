package model;


import exceptions.PrgStateException;
import exceptions.StackException;
import utils.ImyDict;
import utils.ImyList;
import utils.ImyStack;

public class PrgState implements IprgState{

	ImyStack<IStatement> stk;
	ImyDict<String, Integer> symTable;
	ImyList<Integer> output;
	IStatement originalProgram;
	
	public PrgState(ImyStack<IStatement> stk, ImyDict<String, Integer> symTable, ImyList<Integer> output, IStatement o){
		this.stk = stk;
		this.symTable = symTable;
		this.output = output;
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

	public String ExeStackStr(){
		return stk.toString();
	}
	public String SymTableStr(){
		return symTable.toString();
	}
	public String OutputStr(){
		return output.toString();
	}
	
	@Override
	public String toString(){
		String str = "";
		str += this.ExeStackStr();
		str += this.SymTableStr();
		str += this.OutputStr();
		str += '\n';
		return str;
		
	}
	
	
}
