package BOJ;

import java.util.Scanner;

public class B1790_수이어쓰기2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		long unitCnt = 9;
		int targetCnt = K;
		int length = 1;
		int lastNum = 0;
		int position = 0;
		
		while(targetCnt > unitCnt * length) {
			lastNum += unitCnt;
			targetCnt -= unitCnt * length;
			unitCnt *= 10;
			length++;
		}
		
		// 이 부분 도무지 이해 안감!!!
		lastNum = (lastNum + 1) + (targetCnt - 1) / length;
		position = (targetCnt - 1) % length;
		
		if(lastNum > N) {
			System.out.println(-1);
		} else {
			System.out.println((lastNum + "").charAt(position));
		}
	}

}
