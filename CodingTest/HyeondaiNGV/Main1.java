package CodingTest.HyeondaiNGV;

import java.util.Scanner;

public class Main1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int K = sc.nextInt();
		char[] numbers = sc.next().toCharArray();
		
		StringBuilder number = new StringBuilder();
		for(int i = 0 ; i < K ; ++i) {
			number.append(numbers[i]);
		}
		
		long max = Long.parseLong(number.toString());
		int right = K;
		
		while(right < N) {
			number.deleteCharAt(0);
			number.append(numbers[right++]);
			
			long cur = Long.parseLong(number.toString());
			max = cur > max ? cur : max;
		}
		System.out.println(max);
	}
}
