package model;

import exceptions.ExpressionException;
import exceptions.StmtException;
import utils.ImyDict;
import utils.ImyStack;

public class IfStmt implements IStatement{

	Expression e;
	IStatement thenS;
	IStatement elseS;
	 
	public IfStmt(Expression e, IStatement t, IStatement el) {
		this.e = e; 
		this.thenS = t;
		this.elseS = el;
	}
	
	@Override
	public String toString(){
		return "IF (" + e.toString() + ") THEN " + thenS.toString() + " ELSE " + elseS.toString();  
	}
	
	@Override
	public IprgState execute(IprgState state) throws StmtException {
		
		ImyStack<IStatement> stk = state.getExeStack();
		ImyDict<String, Integer> symTable = state.getSymTable();
		
		try {
			if ( e.eval(symTable) != 0){
				stk.push(thenS);
			}
			else{
				stk.push(elseS);
			}
			
			return state;
			
		} catch (ExpressionException e) {
			throw new StmtException("Id in \"" + this.toString() + "\" statement has no value asociated with it");
		}
	
	}
	
}
