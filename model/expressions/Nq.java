package expressions;

import exceptions.ExpressionException;
import utils.ImyDict;

public class Nq extends Expression{
	
	Expression e1;
	Expression e2; 
	
	public Nq(Expression e1, Expression e2){
		this.e1 = e1;
		this.e2 = e2;
	}
	
	@Override
	public int eval(ImyDict<String, Integer> symTable, ImyDict<Integer, Integer> heap) throws ExpressionException {
		if( e1.eval(symTable, heap) != e2.eval(symTable, heap))
			return 1;
		return 0;
	}
	
	@Override
	public String toString(){
		return e1.toString() + " != " + e2.toString();
	}
	
}