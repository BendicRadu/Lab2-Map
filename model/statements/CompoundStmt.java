package statements;

import model.IprgState;
import utils.ImyStack;

public class CompoundStmt implements IStatement{

	IStatement first;
	IStatement second;
	
	
	public CompoundStmt(IStatement first, IStatement second){
		this.first = first;
		this.second = second;
	}
	
	@Override
	public String toString() {
		return ""+first.toString() + " ; " + second.toString()+"";
	}

	public IprgState execute(IprgState state) {
		ImyStack<IStatement> stk = state.getExeStack();
		stk.push(second);
		stk.push(first);
		return null;
	}
}
