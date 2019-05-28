package Chapter_09;

public class E09Window {
	E09Button button1 = new E09Button();
	E09Button button2 = new E09Button();
	
	E09Button.OnClickListener listener = new E09Button.OnClickListener(){
		@Override
		public void onClick(){
			System.out.println("전화를 겁니다.");
		}
	};
	
	E09Window(){
		button1.setOnClickListener(listener);
		button2.setOnClickListener(new E09Button.OnClickListener() {
			
			@Override
			public void onClick() {
				// TODO Auto-generated method stub
				System.out.println("메시지를 보냅니다.");
			}
		});
	}
}
