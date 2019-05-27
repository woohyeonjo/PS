package Algorithm.SWEA.go;

import java.util.ArrayList;
import java.util.Scanner;

public class S5213 {
	static ArrayList<Integer> list;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int t = 1 ; t <= T ; ++t) {
			list = new ArrayList<Integer>();
			int L = sc.nextInt();
			int R = sc.nextInt();
			int ans = 0;
			
			for(int i = L ; i <= R ; ++i) {
				ans += cal(i);
			}
			
			System.out.println("#" + t + " " + ans);
			
		}
	}

	private static int cal(int num) {
		int sqrt = (int) Math.sqrt(num);
		int sum = 0;
			
		for(int i = 1 ; i <= num ; ++i) {
			if(num % i == 0) {
				if(i % 2 != 0) list.add(i);
			}
		}
		
		return sum;
	}
	
	
}
