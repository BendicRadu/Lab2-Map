package statements;

import exceptions.ExpressionException;
import exceptions.StmtException;
import expressions.Expression;
import model.IprgState;
import utils.ImyDict;

public class AssignmentStmt implements IStatement{
	
	private String id;
	private Expression e;
	
	public AssignmentStmt(String id, Expression e){
		this.id = id;
		this.e = e;
	}
	
	@Override
	public String toString(){
		return "" + id + " = " + e.toString();
	}
	
	@Override
	public IprgState execute(IprgState state) throws StmtException {
		
		ImyDict<String, Integer> symTable = state.getSymTable();
		ImyDict<Integer, Integer> heap = state.getHeap();
				
		int val;
		try {
			val = e.eval(symTable, heap);
			symTable.put(id, val);
			return null;
		} catch (ExpressionException e1) {
			throw new StmtException("Statement \"" + this.toString() + "\": " + e1.getMessage());
		}
	}

}
