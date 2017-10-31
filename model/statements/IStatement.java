package statements;

import exceptions.StmtException;
import model.IprgState;

public interface IStatement {
	IprgState execute(IprgState state) throws StmtException;
}
