import maingui.*;

public class Main {
	static MainUI mainUI;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		mainUI = MainUI.getInstance();
		mainUI.initialize();
		mainUI.showUI();
	}

}
