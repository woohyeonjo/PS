package BOJ.go;

import java.util.Scanner;

public class B2455 {
	
	static int passengers = 0;
	static int max_passengers = 0;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for(int i = 0 ; i < 4 ; ++i){
			passengers -= sc.nextInt();
			passengers += sc.nextInt();
			max_passengers = passengers > max_passengers ? passengers : max_passengers;
		}
		
		System.out.println(max_passengers);
	}
}
