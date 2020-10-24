package BOJ;

import java.math.BigInteger;
import java.util.Scanner;

public class B1676_팩토리얼0의개수 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String N = sc.next();
		int cnt = 0;

		String num = pectorial(new BigInteger(N)).toString();
		
		for(int i = num.length() - 1 ; i >= 0 ; --i) {
			if(num.charAt(i) == '0') cnt++;
			else break;
		}

		System.out.println(cnt);
	}
	
	private static BigInteger pectorial(BigInteger n) {
		if(n.equals(BigInteger.ZERO) || n.equals(BigInteger.ONE)) return BigInteger.ONE;
		
		return n.multiply(pectorial(n.subtract(BigInteger.ONE)));
	}
}
