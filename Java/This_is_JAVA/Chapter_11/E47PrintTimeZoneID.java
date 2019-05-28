package Chapter_11;

import java.util.TimeZone;

public class E47PrintTimeZoneID {
	public static void main(String[] args){
		String[] availableIDs = TimeZone.getAvailableIDs();
		for(String ID : availableIDs) {
			System.out.println(ID);
		}
	}
}
