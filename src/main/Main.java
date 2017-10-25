package main;

import model.IprgState;
import model.PrgState;
import model.PrintStmt;
import model.VarExp;
import repository.IRepo;
import repository.Repo;

import view.View;

import controller.Ctrl;
import utils.ImyDict;
import utils.ImyList;
import utils.ImyStack;
import utils.MyDict;
import utils.MyList;
import utils.MyStack;

import model.ArithExp;
import model.AssignmentStmt;
import model.CompoundStmt;
import model.ConstExp;
import model.IStatement;
import model.IfStmt;

public class Main {

	public static void main(String[] args) {

		ImyStack<IStatement> stk = new MyStack<IStatement>();
		ImyDict<String, Integer> sym = new MyDict<String, Integer>();
		ImyList<Integer> out = new MyList<Integer>();
		
		ConstExp e1 = new ConstExp(2);
		
		/*
		a=2-2;
		If a Then v=2 Else v=3;
		Print(v)
		*/
			
		IStatement s1 = new AssignmentStmt("a", new ArithExp(new ConstExp(4), new ConstExp(2), 3));
		VarExp a = new VarExp("a");
		
		IStatement sT = new AssignmentStmt("v", new ConstExp(2));
		IStatement sE = new AssignmentStmt("v", new ConstExp(3));
		
		IStatement s2 = new IfStmt(a, sT, sE);
		IStatement s3 = new PrintStmt(new VarExp("v"));
				
		IStatement s4 = new CompoundStmt(s1, s2);
		IStatement s5 = new CompoundStmt(s4, s3);
	
		IprgState p = new PrgState(stk, sym, out, s5);
		
		IRepo r = new Repo(p);
		Ctrl c = new Ctrl(r);
		View v = new View(c);
		
		v.run();
		
	}
}
