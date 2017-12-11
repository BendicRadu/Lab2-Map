package statements;

import exceptions.DictException;
import exceptions.ExpressionException;
import exceptions.StmtException;
import expressions.Expression;
import model.IprgState;
import utils.ImyDict;

public class WriteHeap implements IStatement{

	String varName;
	Expression exp;
	
	public WriteHeap(String varName, Expression exp){
		this.varName = varName;
		this.exp = exp;
	}
	
	@Override
	public IprgState execute(IprgState state) throws StmtException {
		
		ImyDict<String, Integer> symTable  = state.getSymTable();
		ImyDict<Integer, Integer> heap = state.getHeap();
		
		int addr;
		
		try {
			addr = symTable.get(varName);
		} catch (DictException e) {
			throw new StmtException("Statement \"" + this.toString() + "\" encountered an error: No value associated with \"" + varName + "\"");
		}
		
		if(!heap.containsKey(addr)){
			throw new StmtException("Statement \"" + this.toString() + "\" encountered an error: No heap address associated with \"" + addr + "\"");
		}
		
		try {
			heap.put(addr, exp.eval(symTable, heap));
		} catch (ExpressionException e) {
			throw new StmtException("Statement \"" + this.toString() + "\" encountered an error: expression \"" + exp + "\" is invalid: " + e.getMessage());
		}
		return null;
	}
	
	@Override
	public String toString(){
		return "wH(" + varName + ", " + exp + ")";
	}
	

}
