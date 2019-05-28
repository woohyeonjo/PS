package Chapter_07;

import javax.management.remote.SubjectDelegationPermission;

public class E04SupersonicAirplaneExample {
	public static void main(String args[]){
		E04SupersonicAirplane sa = new E04SupersonicAirplane();
		sa.takeOff();
		sa.fly();
		sa.flyMode = E04SupersonicAirplane.SUPERSONIC;
		sa.fly();
		sa.flyMode = E04SupersonicAirplane.NORMAL;
		sa.fly();
		sa.land();
	}
}

