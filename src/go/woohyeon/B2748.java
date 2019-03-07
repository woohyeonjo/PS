package go.woohyeon;


import java.math.BigInteger;
import java.util.Scanner;

public class B2748 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		BigInteger[] fiboNumber = new BigInteger[N + 1];
		fiboNumber[0] = BigInteger.valueOf(0);
		fiboNumber[1] = BigInteger.valueOf(1);
		
		for(int i = 2 ; i <= N ; i++) {
			fiboNumber[i] = fiboNumber[i - 1].add(fiboNumber[i - 2]);
		}
		System.out.println(fiboNumber[N]);
	}
}
