package LevelB;

import java.util.Scanner;

public class S4038_단어가등장하는횟수 {

	static final int D = 100;

	static char[] book, temp;
	static int target, firstHashValue;
	static int T, cnt;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();

		for (int t = 1; t <= T; ++t) {
			book = sc.next().toCharArray();
			temp = sc.next().toCharArray();

			cnt = 0;
			target = toHash();
			System.out.println("목표 해시 값 : " + target);
			System.out.println("==============================");
			firstHashValue = hash(temp.length);
			
			int hashCode = 0;
			for(int i = 0 ; i <= book.length - temp.length ; ++i) {
				hashCode = toHash(i, hashCode);
				System.out.println("현재 해시 값 : " + hashCode);
				if(target == hashCode) cnt++;
				
			}
			
			System.out.println("#" + t + " " + cnt);
		}
	}

	private static int toHash(int index, int before) {
		int hashCode = 0;
		int hashValue;
		
		if(index == 0) {
			for(int i = temp.length - 1 ; i >= 0 ; --i) {
				hashValue = hash(i);
				hashCode = mod(hashCode + book[index++] * hashValue);
			}
		} else {
			hashCode = D * before + book[index + temp.length - 1] - (book[index - 1] * firstHashValue);
			hashCode = mod(hashCode);
		}
		
		return hashCode;
	}

	private static int toHash() {
		int hashCode = 0;
		int hashValue;
		int index = 0;
		
		for(int i = temp.length - 1 ; i >= 0 ; --i) {
			hashValue = hash(i);
			hashCode = mod(hashCode + temp[index++] * hashValue);
		}
		
		return hashCode;
	}

	private static int hash(int i) {
		int hashValue = 1;
		for(int j = 0 ; j < i ; ++j) hashValue = mod(hashValue * D);
		
		return hashValue;
	}
	
	private static int mod(int number) {
		int M = 10000007;
		if(number > 0) return number % M;
		else return number + M;
	}
}
