package expressions;
import exceptions.ExpressionException;
import utils.ImyDict;

public abstract class Expression {
	public abstract int eval(ImyDict<String, Integer> symTable) throws ExpressionException;
}
