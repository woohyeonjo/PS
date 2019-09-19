package B;

import java.util.Scanner;

public class S3814_평등주의 {
	
	static int T, N, K, C, count;
	static int[] sequence;
	static int[] sequence_copy;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		
		for(int t = 1 ; t <= T ; ++t) {
			N = sc.nextInt();
			K = sc.nextInt();
			C = 0;
			
			sequence = new int[N];
			
			for(int i = 0 ; i < N ; ++i) sequence[i] = sc.nextInt();

			binarySearch();
			
			System.out.println("#" + t + " " + C);
		}
	}

	private static void binarySearch() {
		int left = 0;
		int right = 1000000000;
		int mid = 0;
		
		while(right > left) {
			mid = (right + left) >> 1;
			System.out.println("MID : " + mid + " LEFT : " + left + " RIGHT : " + right);
			if(check(mid)) {
				right = mid;
			} else {
				left = mid + 1;
				C = mid + 1;
			}
		}
	}

	private static boolean check(int c) {
		int gap = 0;
		int count = 0;
		sequence_copy = sequence.clone();
		
		for(int i = 0 ; i < sequence_copy.length - 1 ; ++i) {
			gap = sequence_copy[i + 1] - sequence_copy[i];
			if(gap > c) {
				count += gap - c;
				if(count > K) return false;
				sequence_copy[i + 1] -= (gap - c);
			}
		}
		
		for(int i = sequence_copy.length - 1 ; i > 0 ; --i) {
			gap = sequence_copy[i - 1] - sequence_copy[i];
			if(gap > c) {
				count += gap - c;
				if(count > K) return false;
				sequence_copy[i - 1] -= (gap - c);
			}
		}
		return true;
	}
}
