package model;

import exceptions.DictException;
import exceptions.ExpressionException;
import utils.ImyDict;

public class VarExp extends Expression{
	
	String id;
	
	public VarExp(String id){
		this.id = id;
	}
	
	@Override
	public String toString(){
		return id;
	}
	
	@Override
	public int eval(ImyDict<String, Integer> sym) throws ExpressionException{
		try {
			return sym.get(id);
		} catch (DictException e) {
			throw new ExpressionException("No value associated to \"" + id + "\"");
		}
	}
	
}
