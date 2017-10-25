package view;

import controller.Ctrl;
import exceptions.CtrlException;
import model.IprgState;

import java.util.Scanner;

public class View {

	private Ctrl c;
	private Scanner reader;
	
	public View(Ctrl c){
		this.c = c ;
		this.reader = new Scanner(System.in);
	}
	
	
	public void printMenu(){
		System.out.println("Menu");
		System.out.println("1). One step evaluation");
		System.out.println("2). Complete evaluation");
		System.out.println("Option: ");
	}
		
	public void run(){
		
		System.out.println("Enter log file path: ");
		String path = reader.next();
		c.setLogPath(path);
		
		System.out.println("Original program: \n");
		try {
			System.out.println(c.getCurrentProgram().toString());
		} catch (CtrlException e) {
			System.out.println("\nDone\n");
		}
		
		while(true){
				
			printMenu();
			int opt = reader.nextInt();
		
			try {
				IprgState p = c.getCurrentProgram();
			
				switch(opt){
				
				case 1:{		
					c.oneStepEval();
					System.out.println(p.toString());
					break;
				}
				case 2:{
					c.completeEval();
					System.out.println(p.toString());
					break;
					}
				}	
			} catch (CtrlException e1) {
				System.out.println("\nDone: " + e1.getMessage());
			}
			
		}
			
			
	}
}
