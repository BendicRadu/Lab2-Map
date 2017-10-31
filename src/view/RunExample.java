package view;

import java.util.Scanner;

import controller.Ctrl;
import exceptions.CtrlException;

public class RunExample extends Command {
	private Ctrl ctrl;
	
	public RunExample(String key, String desc, Ctrl ctrl){
		super(key, desc);
		this.ctrl = ctrl;
	}
	
	@Override
	public void execute() {
		try{
			System.out.println("Log file path: ");
			@SuppressWarnings("resource")
			Scanner s = new Scanner(System.in);
			this.ctrl.setLogPath(s.nextLine());
			this.ctrl.completeEval();		
		}catch (CtrlException e1) {
			System.out.println("\nDone: " + e1.getMessage());
		}
	}
}