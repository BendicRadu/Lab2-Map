package model;

import exceptions.ExpressionException;
import utils.ImyDict;

public class ArithExp extends Expression{

	Expression e1;
	Expression e2;
	int op; // 0=+, 1=-, 2=*, 3=/ 
	
	public ArithExp(Expression e1, Expression e2, int op){
		this.e1 = e1;
		this.e2 = e2;
		this.op = op;
	}
	
	
	@Override
	public int eval(ImyDict<String, Integer> symTable) throws ExpressionException{
		if( op == 0 ){
			return e1.eval(symTable) + e2.eval(symTable);
		}
		
		if ( op == 1 ){
			return e1.eval(symTable) - e2.eval(symTable);
		}
		
		if ( op == 2 ){
			return e1.eval(symTable) * e2.eval(symTable);
		}
		
		if ( op == 3 ){
			
			if(e2.eval(symTable) == 0){
				throw new ExpressionException("Expression " + this.toString() + " is invalid (Division by 0)");
			}
			
			return e1.eval(symTable) / e2.eval(symTable);
		}
		
		return 0;
	}
	
	@Override
	public String toString(){
		char[] opList = {'+', '-', '*', '/'};
		return "(" + e1.toString() + opList[op] + e2.toString()	+ ")";	
	}

}
