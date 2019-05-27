package Algorithm.SWEA.go;

import java.util.Scanner;

public class S4530 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int t = 1; t <= T ; ++t) {
			long A = sc.nextLong();
			long B = sc.nextLong();
			
			String as = A + "";
			String bs = B + "";
			char[] ac = as.toCharArray();
			char[] bc = bs.toCharArray();
			System.out.println(Math.abs(A - B) - 1);
			System.out.println("#" + t + " " + (Math.abs(A - B) - 1 - (fourNum(ac) + fourNum(bc))));
		}
	}
	
	static long fourNum(char[] arr) {
		long fourNum = 0;
		boolean isNagative = false;
		
		for(int i = 0 ; i < arr.length ; ++i) {
			if(arr[i] == '-') {
				isNagative = true;
				continue;
			}
			if ((arr[i] - '0') >= 4 &&(arr[i] - '0') / 4 >= 1) {
				fourNum += Math.pow(10, (arr.length - (i + 1)));
			}
			if(isNagative) {
				String temp = "";
				for(int j = 1 ; j < i ; ++j) {
					temp += arr[j];
				}
				if(!temp.equals("")) fourNum += Long.parseLong(temp);
			} else {
				String temp = "";
				for(int j = 0 ; j < i ; ++j) {
					temp += arr[j];
				}
				if(!temp.equals("")) fourNum += Long.parseLong(temp);
			}
		}
		System.out.println(fourNum);
		return fourNum;
	}
}
