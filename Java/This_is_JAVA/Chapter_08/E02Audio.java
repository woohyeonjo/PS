package Chapter_08;

public class E02Audio implements E02RemoteControl {

	private int volume;
	
	public void turnOn(){
		System.out.println("Audio를 켭니다.");
	}
	
	public void turnOff(){
		System.out.println("Audio를 끕니다.");
	}
	
	public void setVolume(int volume){
		if(volume > E02RemoteControl.MAX_VOLUME){
			this.volume = E02RemoteControl.MAX_VOLUME;
		} else if (volume < E02RemoteControl.MIN_VOLUME){
			this.volume = E02RemoteControl.MIN_VOLUME;
		} else {
			this.volume = volume;
		}
		System.out.println("현재 Audio Volume : " + volume);
	}
}
