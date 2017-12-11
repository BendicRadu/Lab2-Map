package statements;

import exceptions.DictException;
import exceptions.ExpressionException;
import exceptions.StmtException;
import expressions.Expression;
import model.IprgState;
import utils.ImyDict;

public class New implements IStatement{

	public String varName;
	public Expression exp; 
	
	public New(String varName, Expression exp){
		this.varName = varName;
		this.exp = exp;
	}
	
	@Override
	public IprgState execute(IprgState state) throws StmtException {
	
		ImyDict<String, Integer> symTable = state.getSymTable();
		ImyDict<Integer, Integer> heap = state.getHeap();
		
		try {
			
			int e = exp.eval(symTable, heap);
			int addr = heap.putKey(e);
			symTable.put(varName, addr);
			
		} catch (ExpressionException e) {
			throw new StmtException("Expression \"" + exp + "\" in statement + \"" + this.toString() + "\" is invalid: " + e.getMessage());
		} catch (DictException e1) {
			throw new StmtException("Statement \"" + this.toString() + "\" encountered a head error: " + e1.getMessage());
		}
		
		return null;
	}
	
	@Override
	public String toString(){
		return "new(" + varName + ", " + exp.toString() + ")";
	}
	
}
