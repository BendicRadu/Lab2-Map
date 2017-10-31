package view;

import java.io.BufferedReader;

import controller.Ctrl;
import expressions.ArithExp;
import expressions.ConstExp;
import expressions.VarExp;
import model.IprgState;
import model.PrgState;
import repository.IRepo;
import repository.Repo;
import statements.AssignmentStmt;
import statements.CloseRFile;
import statements.CompoundStmt;
import statements.IStatement;
import statements.IfStmt;
import statements.PrintStmt;
import statements.ReadFile;
import statements.openRFile;
import utils.ImyDict;
import utils.ImyList;
import utils.ImyStack;
import utils.ImyTuple;
import utils.MyDict;
import utils.MyList;
import utils.MyStack;
import utils.MyTDict;

class Interpreter {
	public static void main(String[] args) {
		
		/*
		openRFile(var_f,"test.in");
		readFile(var_f,var_c);print(var_c);
		(if var_c then readFile(var_f,var_c);print(var_c)
		else print(0));
		closeRFile(var_f) 
		*/
		
		IStatement ex1 
		= new CompoundStmt(
			new CompoundStmt(
				new openRFile("f", "Input"),
				new ReadFile(new ArithExp(new VarExp("f"), new ConstExp(2), 0), "read")
				),
			new CompoundStmt(
				new CompoundStmt(
					new PrintStmt(new VarExp("read")),
					new IfStmt(
						new VarExp("read"), 
						new CompoundStmt(
							new ReadFile(new VarExp("f"), "read"),
							new PrintStmt(new VarExp("read"))
						),
						new PrintStmt(new ConstExp(0))
					)
				), 
				new CloseRFile(new VarExp("f"))
			)
		);

		ImyStack<IStatement> stk1 = new MyStack<IStatement>();
		ImyDict<String, Integer> sym1 = new MyDict<String, Integer>();
		ImyList<Integer> out1 = new MyList<Integer>();
		ImyDict<Integer, ImyTuple<String, BufferedReader>> file1 = new MyTDict<Integer, ImyTuple<String, BufferedReader>>();
		
		IprgState p1 = new PrgState(stk1, sym1, out1, file1, ex1);
		IRepo r1 = new Repo(p1);
		Ctrl c1 = new Ctrl(r1);
		
		IStatement ex2 
		= new CompoundStmt(
			new CompoundStmt(
				new openRFile("f", "Input"),
				new ReadFile(new VarExp("f"), "read")
				),
			new CompoundStmt(
				new CompoundStmt(
					new PrintStmt(new VarExp("read")),
					new IfStmt(
						new VarExp("read"), 
						new CompoundStmt(
							new ReadFile(new VarExp("f"), "read"),
							new PrintStmt(new VarExp("read"))
						),
						new PrintStmt(new ConstExp(0))
					)
				), 
				new CloseRFile(new VarExp("f"))
			)
		);


		ImyStack<IStatement> stk2 = new MyStack<IStatement>();
		ImyDict<String, Integer> sym2 = new MyDict<String, Integer>();
		ImyList<Integer> out2 = new MyList<Integer>();
		ImyDict<Integer, ImyTuple<String, BufferedReader>> file2 = new MyTDict<Integer, ImyTuple<String, BufferedReader>>();
		
		IprgState p2 = new PrgState(stk2, sym2, out2, file2, ex2);
		IRepo r2 = new Repo(p2);
		Ctrl c2 = new Ctrl(r2);
		
		IStatement ex3 
		= new CompoundStmt(
			new CompoundStmt(
				new openRFile("f", "NoFileTest"),
				new ReadFile(new VarExp("f"), "read")
				),
			new CompoundStmt(
				new CompoundStmt(
					new PrintStmt(new VarExp("read")),
					new IfStmt(
						new VarExp("read"), 
						new CompoundStmt(
							new ReadFile(new VarExp("f"), "read"),
							new PrintStmt(new VarExp("read"))
						),
						new PrintStmt(new ConstExp(0))
					)
				), 
				new CloseRFile(new VarExp("f"))
			)
		);


		ImyStack<IStatement> stk3 = new MyStack<IStatement>();
		ImyDict<String, Integer> sym3 = new MyDict<String, Integer>();
		ImyList<Integer> out3 = new MyList<Integer>();
		ImyDict<Integer, ImyTuple<String, BufferedReader>> file3 = new MyTDict<Integer, ImyTuple<String, BufferedReader>>();
		
		
		IprgState p3 = new PrgState(stk3, sym3, out3, file3, ex3);
		IRepo r3 = new Repo(p3);
		Ctrl c3 = new Ctrl(r3);
		

		/*
		a=2-2;
		If a Then v=2 Else v=3;
		Print(v)
		*/
		
		
		IStatement ex4 = 
		new CompoundStmt(
			new CompoundStmt(
					new AssignmentStmt("a", new ArithExp(new ConstExp(2), new ConstExp(2), 1)),
					new IfStmt(new VarExp("a"), new AssignmentStmt("v", new ConstExp(2)), new AssignmentStmt("v", new ConstExp(3)))
			),
			new PrintStmt(new VarExp("v"))
		);
		
		
		ImyStack<IStatement> stk4 = new MyStack<IStatement>();
		ImyDict<String, Integer> sym4 = new MyDict<String, Integer>();
		ImyList<Integer> out4 = new MyList<Integer>();
		ImyDict<Integer, ImyTuple<String, BufferedReader>> file4 = new MyTDict<Integer, ImyTuple<String, BufferedReader>>();
		
		
		IprgState p4 = new PrgState(stk4, sym4, out4, file4, ex4);
		IRepo r4 = new Repo(p4);
		Ctrl c4 = new Ctrl(r4);
		
		
		TextMenu menu = new TextMenu();
		menu.addCommand(new Exit("0", "exit"));
		menu.addCommand(new RunExample("1", ex1.toString(), c1));
		menu.addCommand(new RunExample("2", ex2.toString(), c2));
		menu.addCommand(new RunExample("3", ex3.toString(), c3));
		menu.addCommand(new RunExample("4", ex4.toString(), c4));
		menu.show();
	}
}