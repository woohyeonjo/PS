package BOJ;

import java.util.Scanner;

public class B1212_8진수2진수 {
	
	static String[] octToBin = {"000", "001", "010", "011", "100", "101", "110", "111"};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		char[] oct = sc.next().toCharArray();
		
		// 주어진 8진수가 0일 경우 
		if(oct.length == 1 && oct[0] == '0') {
			System.out.println(0);
			return;
		}
		
		// 주어진 8진수의 각 수를 2진수로 바꾼다. 
		for(int i = 0 ; i < oct.length ; ++i) {
			sb.append(octToBin[oct[i] - '0']);
		}

		// 변환된 2진수의 가장 앞에 오는 0을 없앤다. 
		while(sb.charAt(0) == '0') {
			sb.deleteCharAt(0);
		}
			
		System.out.println(sb.toString());
	}
}
