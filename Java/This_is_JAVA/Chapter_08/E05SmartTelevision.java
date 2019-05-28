package Chapter_08;

public class E05SmartTelevision implements E02RemoteControl, E05Searchable {
	private int volume;

	@Override
	public void search(String url) {
		// TODO Auto-generated method stub
		System.out.println(url + "을 검색합니다.");
	}

	@Override
	public void turnOn() {
		// TODO Auto-generated method stub
		System.out.println("TV를 켭니다.");
	}

	@Override
	public void turnOff() {
		// TODO Auto-generated method stub
		System.out.println("TV를 끕니다.");
	}

	@Override
	public void setVolume(int volume) {
		// TODO Auto-generated method stub
		if(volume > E02RemoteControl.MAX_VOLUME){
			this.volume = E02RemoteControl.MAX_VOLUME;
		} else if ( volume < E02RemoteControl.MIN_VOLUME){
			this.volume = E02RemoteControl.MIN_VOLUME;
		} else {
			this.volume = volume;
		}
		System.out.println("현재 TV 볼륨 : " + volume);
	}
	
	
}
