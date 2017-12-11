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

public class ReadFile implements IStatement{

	public Expression exp_file_id;
	public String var_name;
	
	public ReadFile(Expression exp_file_id, String var_name){
		this.exp_file_id = exp_file_id;
		this.var_name = var_name;
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
		} catch (DictException e) {
			throw new StmtException("File does not exist or hasn't been open \"" + exp_file_id.toString() + "\"");
		}
		
		try {
			String readValue = buffReader.readLine();
			
			int value;
			
			if(readValue == null){
				value = 0;
			}
			else{
				value = Integer.parseInt(readValue);
			}
			
			symTable.put(var_name, value);
			
		} catch (NumberFormatException e1) {
			throw new StmtException("Statement \"" + this.toString() + "\" encountered an exception: " + e1.getMessage());
		} catch (IOException e2) {
			throw new StmtException("Statement \"" + this.toString() + "\" encountered an exception: " + e2.getMessage());
		}
		
		return null;
	}

	@Override
	public String toString(){
		return "readFile(" + exp_file_id + ", " +  var_name + ")";
	}
	
	
}
