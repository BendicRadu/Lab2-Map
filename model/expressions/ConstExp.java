package expressions;

import utils.ImyDict;

public class ConstExp extends Expression{
	
	private int number;
	
	public ConstExp(int number){
		this.number = number;
	}
	
	@Override
	public int eval(ImyDict<String, Integer> sym, ImyDict<Integer, Integer> heap){
		return this.number;
	}
	
	@Override
	public String toString(){
		return "" + number;
	}
	
}
