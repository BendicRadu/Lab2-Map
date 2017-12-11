package statements;

import exceptions.ExpressionException;
import exceptions.StmtException;
import expressions.Expression;
import model.IprgState;
import utils.ImyDict;
import utils.ImyList;

public class PrintStmt implements IStatement{

	Expression e;
	
	public PrintStmt(Expression e){
		this.e = e;
	}
	
	@Override
	public String toString(){
		return "print(" + e.toString() + ")"; 
	}
	
	@Override
	public IprgState execute(IprgState state) throws StmtException {
		
		ImyList<Integer> out = state.getOutput();
		ImyDict<String, Integer> symTable = state.getSymTable();
		ImyDict<Integer, Integer> heap = state.getHeap();
		
 		try {
			out.add(e.eval(symTable, heap));
		} catch (ExpressionException e) {
			throw new StmtException("Id in \"" + this.toString() + "\" statement has no value asociated with it");
		}
	
		return null;
	}

}
