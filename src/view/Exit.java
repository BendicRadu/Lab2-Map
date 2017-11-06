package view;

public class Exit extends Command{
	
	public Exit(String key, String desc){
		 super(key, desc);
		 }

	@Override
	public void execute() {
		System.exit(0);
	}
}

