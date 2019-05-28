package Chapter_09;

public class E06MessageListener implements E06Button.OnClickListener {
	@Override
	public void onClick(){
		System.out.println("메시지를 보냅니다.");
	}
}
