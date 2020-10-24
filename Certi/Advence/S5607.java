package SWEA;


import java.math.BigInteger;
import java.util.Scanner;

public class S5607 {
	
	static final int P = 1234567891;
	
	public static void main(String[] args) {
		BigInteger result;
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int t = 1 ; t <= T ; ++t) {
			BigInteger combination;
			BigInteger N = new BigInteger(sc.nextInt() + "");
			int R = sc.nextInt();
			
			//combination = (fac(N) / (fac(R) * fac(N.subtract(BigInteger.valueOf(R).intValue()))));
			
			//System.out.println("#" + t + " " + result.intValue());
			
			//?��?��?�� N?? R�? 주어진다.
			//?�� ?��?�� N combination R?�� 값을 1234567891�? ?��?�� ?��머�?�?  출력?��?��?��.
			//?��를들�? N?�� 4, R?�� 2?���? 4 combination 2?�� (4 * 3) / (2 * 1) = 6?�� ?��?��.
		}
	}
	
	public static BigInteger fac(int i) {
		BigInteger fac = BigInteger.ONE;
		
		for(int j = 1 ; j <= i ; ++j) {
			fac = fac.multiply(BigInteger.valueOf(i));
		}
		return fac;
	}
}
