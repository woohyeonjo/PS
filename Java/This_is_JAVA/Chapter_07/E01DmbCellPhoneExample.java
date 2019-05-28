package Chapter_07;

public class E01DmbCellPhoneExample {
	public static void main(String args[]){
		E01DmbCellPhone dmbCellPhone = new E01DmbCellPhone("자바폰", "검정", 10);
		
		System.out.println("모델 : " + dmbCellPhone.model);
		System.out.println("색상 : " + dmbCellPhone.color);
		
		System.out.println("채널 : " + dmbCellPhone.channel);
		
		dmbCellPhone.powerOn();
		dmbCellPhone.bell();
		dmbCellPhone.sendVoice("여보세요");
		dmbCellPhone.receiveVoice("안녕하세요! 저는 홍길동인데요");
		dmbCellPhone.sendVoice("아~ 예 반갑습니다.");
		dmbCellPhone.hangUp();
		
		dmbCellPhone.turnOnDmb();
		dmbCellPhone.changeCannelDmb(12);
		dmbCellPhone.turnOffDmb();
		
	}
}
