package Chapter_08;

public class E07RemoteControlExample {
	public static void main(String[] args){
		E02RemoteControl rc = null;
		
		rc = new E02Television();
		rc.turnOn();
		rc.setMute(true);
		
		rc = new E02Audio();
		rc.turnOn();
		rc.setMute(true);
	}
}
