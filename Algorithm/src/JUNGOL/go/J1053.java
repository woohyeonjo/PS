package JUNGOL.go;

import java.util.Scanner;

public class J1053 {
	static int[] pisa;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			pisa = new int[30001];
			pisa[0] = 0;
			pisa[1] = 1;
			int N = sc.nextInt();
			
			if(N <0) break;
			
			System.out.println(fibo(N));
			
		}
	}
	static int fibo(int N) {
		if(N == 0) return 0;
		else if(N == 1) return 1;
		
		int mod = 10000;
		long p = 30000;
		int i = 2;
		while(i<p) {
			pisa[i] = (pisa[i-1] + pisa[i-2])%mod;
			i++;
		}
		return pisa[(int) (N%p)];
	}

}
