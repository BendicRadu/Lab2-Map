package model;

import utils.ImyDict;

public class ConstExp extends Expression{
	
	private int number;
	
	public ConstExp(int number){
		this.number = number;
	}
	
	@Override
	public int eval(ImyDict<String, Integer> sym){
		return this.number;
	}
	
	@Override
	public String toString(){
		return "" + number;
	}
	
}
