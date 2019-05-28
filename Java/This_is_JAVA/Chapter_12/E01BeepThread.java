package Chapter_12;

import java.awt.Toolkit;

public class E01BeepThread extends Thread {
	@Override
	public void run() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		for(int i = 0 ; i < 5 ; i ++){
			toolkit.beep();
			try { Thread.sleep(500);; } catch(Exception e) {}
		}
	}
}
