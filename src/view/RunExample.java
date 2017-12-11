package view;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import controller.Ctrl;
import exceptions.CtrlException;
import model.IprgState;

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
		}finally{
			try {
				
				List<IprgState> tmpList= ctrl.getPrgList();
				
				tmpList.stream().
				forEach(p -> p.getFileTable().entrySet().stream()
					.forEach(s->{
						try {
							s.getValue().getSecond().close();
						} catch (IOException e) {
							System.out.println("\nDone: " + e.getMessage());
						}
					})
					);
				
			} catch (CtrlException e) {
				System.out.println("\nDone: " + e.getMessage());
			}
		}
	}
}