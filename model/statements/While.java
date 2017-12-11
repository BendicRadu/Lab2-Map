package statements;

import exceptions.ExpressionException;
import exceptions.StmtException;
import expressions.Expression;
import model.IprgState;
import utils.ImyDict;
import utils.ImyStack;

public class While implements IStatement{

	public Expression exp;
	public IStatement stmt;
	
	public While(Expression exp, IStatement stmt){
		this.exp = exp;
		this.stmt = stmt;
	}

	@Override
	public IprgState execute(IprgState state) throws StmtException {
	
		ImyDict<String, Integer> symTable = state.getSymTable();
		ImyDict<Integer, Integer> heap = state.getHeap();
		ImyStack<IStatement> stk = state.getExeStack();
		
		try {
			int val = exp.eval(symTable, heap);
			
			if ( val != 0 ){
				stk.push(this);
				stk.push(stmt);
			}
			
		} catch (ExpressionException e) {
			throw new StmtException("Statement " + this.toString() + " has encountered an expression exception in \"" + exp.toString() + "\"" + e.getMessage());
		}
		
		
		
		return null;
	}
	
	@Override
	public String toString(){
		return "While ( " + exp.toString() + ") " + stmt.toString();
	}
	
	
}
