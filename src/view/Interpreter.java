package view;

import java.io.BufferedReader;

import controller.Ctrl;
import expressions.ArithExp;
import expressions.ConstExp;
import expressions.ReadHeap;
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
import statements.New;
import statements.PrintStmt;
import statements.ReadFile;
import statements.WriteHeap;
import statements.openRFile;
import utils.ImyDict;
import utils.ImyList;
import utils.ImyStack;
import utils.ImyTuple;
import utils.MyDict;
import utils.MyHDict;
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
		ImyDict<Integer, Integer> heap1 = new MyHDict<Integer>();
		
		IprgState p1 = new PrgState(stk1, sym1, out1, file1, heap1, ex1);
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
		ImyDict<Integer, Integer> heap2 = new MyHDict<Integer>();
		
		IprgState p2 = new PrgState(stk2, sym2, out2, file2, heap2, ex2);
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
		ImyDict<Integer, Integer> heap3 = new MyHDict<Integer>();
		
		IprgState p3 = new PrgState(stk3, sym3, out3, file3, heap3, ex3);
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
		ImyDict<Integer, Integer> heap4 = new MyHDict<Integer>();
		
		IprgState p4 = new PrgState(stk4, sym4, out4, file4, heap4, ex4);
		IRepo r4 = new Repo(p4);
		Ctrl c4 = new Ctrl(r4);
		
		
		//v=10;new(v,20);new(a,22);print(v) 
		
		IStatement ex5 = 
				new CompoundStmt(
					new CompoundStmt(
						new AssignmentStmt("v", new ConstExp(10)),
						new New("v", new ConstExp(20))
						),
					new CompoundStmt(
						new New("a", new ConstExp(22)),
						new PrintStmt(new VarExp("v"))
						)
					);
	
		ImyStack<IStatement> stk5 = new MyStack<IStatement>();
		ImyDict<String, Integer> sym5 = new MyDict<String, Integer>();
		ImyList<Integer> out5 = new MyList<Integer>();
		ImyDict<Integer, ImyTuple<String, BufferedReader>> file5 = new MyTDict<Integer, ImyTuple<String, BufferedReader>>();
		ImyDict<Integer, Integer> heap5 = new MyHDict<Integer>();
		
		IprgState p5 = new PrgState(stk5, sym5, out5, file5, heap5, ex5);
		IRepo r5 = new Repo(p5);
		Ctrl c5 = new Ctrl(r5);
		
		// v=10;new(v,20);new(a,22);print(100+rH(v));print(100+rH(a)) 
		
		IStatement ex6 = 
				new CompoundStmt(
					new CompoundStmt(
						new CompoundStmt(
							new AssignmentStmt("v", new ConstExp(10)),
							new New("v", new ConstExp(20))
							),
						new CompoundStmt(
							new New("a", new ConstExp(22)),
							new PrintStmt(new ArithExp(new ConstExp(100), new ReadHeap("v"), 0))
							)
					),
					new PrintStmt(new ArithExp(new ConstExp(100), new ReadHeap("a"), 0))
				);
				
		ImyStack<IStatement> stk6 = new MyStack<IStatement>();
		ImyDict<String, Integer> sym6 = new MyDict<String, Integer>();
		ImyList<Integer> out6 = new MyList<Integer>();
		ImyDict<Integer, ImyTuple<String, BufferedReader>> file6 = new MyTDict<Integer, ImyTuple<String, BufferedReader>>();
		ImyDict<Integer, Integer> heap6 = new MyHDict<Integer>();
		
		IprgState p6 = new PrgState(stk6, sym6, out6, file6, heap6, ex6);
		IRepo r6 = new Repo(p6);
		Ctrl c6 = new Ctrl(r6);
		
		//v=10;new(v,20);new(a,22);wH(a,30);print(a);print(rH(a))
		
		IStatement ex7 = 
				new CompoundStmt(
					new CompoundStmt(
						new CompoundStmt(
							new CompoundStmt(
								new AssignmentStmt("v", new ConstExp(10)),
								new New("v", new ConstExp(20))
								),
							new CompoundStmt(
								new New("a", new ConstExp(22)),
								new WriteHeap("a", new ConstExp(30))
								)
						),
						new PrintStmt(new VarExp("a"))
					),
					new PrintStmt(new ReadHeap("a"))
				);
				
		ImyStack<IStatement> stk7 = new MyStack<IStatement>();
		ImyDict<String, Integer> sym7 = new MyDict<String, Integer>();
		ImyList<Integer> out7 = new MyList<Integer>();
		ImyDict<Integer, ImyTuple<String, BufferedReader>> file7 = new MyTDict<Integer, ImyTuple<String, BufferedReader>>();
		ImyDict<Integer, Integer> heap7 = new MyHDict<Integer>();
		
		IprgState p7 = new PrgState(stk7, sym7, out7, file7, heap7, ex7);
		IRepo r7 = new Repo(p7);
		Ctrl c7 = new Ctrl(r7);
		
		//v=10;new(v,20);new(a,22);wH(a,30);print(a);print(rH(a));a=0 
		
		IStatement ex8 = 
				new CompoundStmt(
					new CompoundStmt(
						new CompoundStmt(
							new CompoundStmt(
								new CompoundStmt(
									new AssignmentStmt("v", new ConstExp(10)),
									new New("v", new ConstExp(20))
									),
								new CompoundStmt(
									new New("a", new ConstExp(22)),
									new WriteHeap("a", new ConstExp(30))
									)
							),
							new PrintStmt(new VarExp("a"))
						),
						new PrintStmt(new ReadHeap("a"))
					),
					new AssignmentStmt("a", new ConstExp(0))
					);
				
		ImyStack<IStatement> stk8 = new MyStack<IStatement>();
		ImyDict<String, Integer> sym8 = new MyDict<String, Integer>();
		ImyList<Integer> out8 = new MyList<Integer>();
		ImyDict<Integer, ImyTuple<String, BufferedReader>> file8 = new MyTDict<Integer, ImyTuple<String, BufferedReader>>();
		ImyDict<Integer, Integer> heap8 = new MyHDict<Integer>();
		
		IprgState p8 = new PrgState(stk8, sym8, out8, file8, heap8, ex8);
		IRepo r8 = new Repo(p8);
		Ctrl c8 = new Ctrl(r8);
	
		
		TextMenu menu = new TextMenu();
		menu.addCommand(new Exit("0", "exit"));
		menu.addCommand(new RunExample("1", ex1.toString(), c1));
		menu.addCommand(new RunExample("2", ex2.toString(), c2));
		menu.addCommand(new RunExample("3", ex3.toString(), c3));
		menu.addCommand(new RunExample("4", ex4.toString(), c4));
		menu.addCommand(new RunExample("5", ex5.toString(), c5));
		menu.addCommand(new RunExample("6", ex6.toString(), c6));
		menu.addCommand(new RunExample("7", ex7.toString(), c7));
		menu.addCommand(new RunExample("8", ex8.toString(), c8));
		menu.show();
		
			
		
	}
}