package BOJ.solution;

import java.util.Scanner;

public class B9663_S {
	static int ans, n;
	static int[] col;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();

		for (int i = 1; i <= n; i++) {
			col = new int[15];
			col[1] = i;
			// ?��?��?? ?��?�� 기�?. (i = 1) => 1?��(1?��), (i = 2) => 2?��(1?��), (i = 3) => 3?��(1?��)
			dfs(1);
		}
		System.out.println(ans);
	}

	public static void dfs(int row) {
		if (row == n) {
			++ans;
		} else {
			for (int i = 1; i <= n; i++) {
				col[row + 1] = i;
				if (isPossible(row + 1)) {
					dfs(row + 1);
				} else {
					col[row + 1] = 0;
				}
			}
		}
		col[row] = 0;
	}

	public static boolean isPossible(int c) {
	    // col[1]?�� ?��미는 1?�� *?��?��?��.
	    // col[1] = 1 => 1?�� 1?��, col[2] = 3 => 2?�� 3?��
	 
	    // ?��?�� ?��?��?�� ?��?��?��면서 배치 �??�� ?���? ?��?��
	    for (int i = 1; i < c; i++) {
	        // 같�? ?��, ?��
	        if (col[i] == col[c]) {
	            return false;
	        }
	        // ??각선
	        if (Math.abs(col[i] - col[c]) == Math.abs(i - c)) {
	            return false;
	        }
	    }
	    return true;
	}
}
