package statements;

import java.io.BufferedReader;

import exceptions.StmtException;
import model.IprgState;
import model.PrgState;
import utils.ImyDict;
import utils.ImyList;
import utils.ImyStack;
import utils.ImyTuple;
import utils.MyStack;

public class Fork implements IStatement{

	IStatement stmt;
	
	public Fork(IStatement stmt){
		this.stmt = stmt;
	}

	@Override
	public IprgState execute(IprgState state) throws StmtException {

		ImyStack<IStatement> stk = new MyStack<IStatement>();
		//stk.push(this.stmt);/
		
		ImyDict<String, Integer> symTable = state.getSymTable().clone();
		ImyDict<Integer, Integer> heap = state.getHeap();
		ImyDict<Integer, ImyTuple<String, BufferedReader>> fileTable = state.getFileTable();
		ImyList<Integer> out = state.getOutput();
		
		int newId = (state.getId() + state.getIdBuffer()) * 10;
		state.setIdBuffer(state.getIdBuffer() + 1);
		
		return new PrgState(stk, symTable, out, fileTable, heap, this.stmt, newId);
	}
	
	@Override
	public String toString(){
		return "fork( " + this.stmt + " )";
	}
	
}
