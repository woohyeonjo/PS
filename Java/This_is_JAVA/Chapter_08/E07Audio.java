package Chapter_08;

public class E07Audio implements E02RemoteControl {
	
	private int volume;
	private boolean mute;
	
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
	
	@Override
	public void setMute(boolean mute){
		this.mute = mute;
		if(mute){
			System.out.println("Audio 무음 처리합니다.");
		} else {
			System.out.println("Audio 무음 해제합니다.");
		}
	}
}
