package expressions;

import exceptions.DictException;
import exceptions.ExpressionException;
import utils.ImyDict;

public class ReadHeap extends Expression{

	String varName;
	
	public ReadHeap(String varName) {
		this.varName = varName;
	}

	@Override
	public int eval(ImyDict<String, Integer> symTable, ImyDict<Integer, Integer> heap) throws ExpressionException {
		
		int addr;
		
		try {
			 addr = symTable.get(varName);
		} catch (DictException e) {
			throw new ExpressionException("No address asociated to \"" + varName + "\"");
		}
		
		try {
			 return heap.get(addr);
		} catch (DictException e) {
			throw new ExpressionException("No value at address \"" + addr + "\"");
		}
	}

	@Override
	public String toString(){
		return "rH(" + varName + ")";
	}
	
}