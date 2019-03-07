package go.woohyeon;


import java.util.Scanner;

public class B1924 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		int y = sc.nextInt();
		int maxY = 0;
		int day = 0;
		String[] week = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
		
		for(int i = 1 ; i < x ; i++) {
			if(i == 1 || i == 3 || i == 5 || i == 7
					|| i == 8 || i == 10 || i == 12) {
				maxY = 31;
			} else if (i == 4 || i == 6 || i == 9 || i == 11) {
				maxY = 30;
			} else if (i == 2) {
				maxY = 28;
			}
			day += maxY;
		}
		day += y;
		System.out.println(week[(day % 7)]);
	}
}
