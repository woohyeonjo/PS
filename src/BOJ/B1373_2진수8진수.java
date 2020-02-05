package BOJ;

import java.util.Scanner;

public class B1373_2진수8진수 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		String bin = sc.next();
		int temp = 0;
		int remain = bin.length() % 3;
		
		if(remain == 1) {
			sb.append(bin.charAt(0) - '0');
		} else if(remain == 2) {
			temp += (bin.charAt(0) - '0') * 2;
			temp += bin.charAt(1) - '0';
			
			sb.append(temp);
		}
		
		for(int i = remain ; i < bin.length() ; i += 3) {
			temp = 0;
			temp += (bin.charAt(i) - '0') * 4;
			temp += (bin.charAt(i + 1) - '0') * 2;
			temp += bin.charAt(i + 2) - '0';
			
			sb.append(temp);
		}
		
		System.out.println(sb.toString());
	}
}
