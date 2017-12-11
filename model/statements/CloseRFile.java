package statements;

import java.io.BufferedReader;
import java.io.IOException;

import exceptions.DictException;
import exceptions.ExpressionException;
import exceptions.StmtException;
import expressions.Expression;
import model.IprgState;
import utils.ImyDict;
import utils.ImyTuple;

public class CloseRFile implements IStatement {

	Expression exp_file_id;

	public CloseRFile(Expression exp_file_id) {
		this.exp_file_id = exp_file_id;
	}

	@Override
	public IprgState execute(IprgState state) throws StmtException {

		
		ImyDict<String, Integer> symTable = state.getSymTable();
		ImyDict<Integer, ImyTuple<String, BufferedReader>> fileTable = state.getFileTable(); 
		ImyDict<Integer, Integer> heap = state.getHeap();
		
		int v;
		BufferedReader buffReader;
		
		try {
			v = exp_file_id.eval(symTable, heap);
		} catch (ExpressionException e) {
			throw new StmtException("Expression \"" + exp_file_id + "\" in statement + \"" + this.toString() + "\" is invalid: " + e.getMessage());
		}
		
		try {
			buffReader = fileTable.get(v).getSecond();
			buffReader.close();
			fileTable.remove(v);
			
		} catch (DictException | IOException e) {
			throw new StmtException("File does not exist or hasn't been open");
		}
		
		return null;	
	}
	
	@Override
	public String toString(){
		return "closeRFile(" + exp_file_id.toString() + ")";
	}
	
}
