package statements;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import exceptions.DictException;
import exceptions.StmtException;
import model.IprgState;
import utils.ImyDict;
import utils.ImyTuple;
import utils.MyTuple;

public class openRFile implements IStatement{

	String var_file_id;
	String filename;
	
	public openRFile(String var_file_id, String filename){
		this.var_file_id = var_file_id;
		this.filename = filename;
	}
	
	@Override
	public IprgState execute(IprgState state) throws StmtException {
		
		ImyDict<Integer, ImyTuple<String, BufferedReader>> fileTable = state.getFileTable();
		ImyDict<String, Integer> symTable = state.getSymTable();
		
		try {
			if(fileTable.isInFirst(filename)){
				throw new StmtException("File allready opened");
			}
		} catch (DictException e1) {
			throw new StmtException("Statement \"" + this.toString() + "\" encountered a dictionary exception: " + e1.getMessage());
		}
		BufferedReader buffReader;		
		try {
			 buffReader = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException e) {
			throw new StmtException("File not found \"" + filename + "\"");
		}
		
		MyTuple<String, BufferedReader> entry = new MyTuple<String, BufferedReader>(filename, buffReader);
		int id = fileTable.generateId();
		fileTable.put(id, entry);
		symTable.put(var_file_id, id);
		
		return null;
	}
	
	@Override
	public String toString(){
		return "openRFile(" + var_file_id + ", " + "\"" + filename + "\")";
	}

}
