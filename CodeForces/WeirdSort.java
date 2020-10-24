package CodeForces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class WeirdSort {

	static HashSet<Integer> P;
	static int[] A;
	static int T, N, M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = stoi(br.readLine());
		
		for(int t = 0 ; t < T ; ++t) {
			st = new StringTokenizer(br.readLine());
			
			N = stoi(st.nextToken());
			M = stoi(st.nextToken());
			
			A = new int[N + 1];
			P = new HashSet<>();
			
			st = new StringTokenizer(br.readLine());
			for(int i = 1 ; i <= N ; ++i) {
				A[i] = stoi(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < M ; ++i) {
				P.add(stoi(st.nextToken()));
			}
			
			if(bubbleSort()) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}
	}
	
	private static boolean bubbleSort() {
		for(int i = 1 ; i < N ; ++i) {
			for(int j = 1 ; j < N ; ++j) {
				if(A[j] > A[j + 1]) {
					if(!P.contains(j)) return false;
					swap(j, j + 1);
				}
			}
		}
		
		return true;
	}

	private static void swap(int i, int j) {
		int temp = A[i];
		A[i] = A[j];
		A[j] = temp;
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
