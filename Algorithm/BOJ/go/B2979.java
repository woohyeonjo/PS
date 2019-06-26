package B190626;

import java.util.Scanner;

public class B2979 {
	
	static int A, B, C, ans;
	static int[] parking = new int[101];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		A = sc.nextInt();
		B = sc.nextInt();
		C = sc.nextInt();
		
		for(int i = 0 ; i < 3 ; ++i){
			int start = sc.nextInt();
			int end = sc.nextInt();
			for(int j = start ; j < end ; ++j) parking[j]++;
		}
		
		for(int i = 0 ; i < parking.length ; ++i){
			if(parking[i] == 1) ans += parking[i] * A;
			else if(parking[i] == 2) ans += parking[i] * B;
			else ans += parking[i] * C;
		}
		
		System.out.println(ans);
	}
}
