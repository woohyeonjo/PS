package Algorithm.SWEA.go;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class S2805 {
	
	static int[][] arr;
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int T = Integer.parseInt(sc.nextLine());
		
		for(int t = 1 ; t <= T ; ++t) {
			int result = 0;
			int N = Integer.parseInt(sc.nextLine());
			String[] line = null;
			arr = new int[N][N];
			
			for(int i = 0 ; i < N ; ++i) {
				line = sc.nextLine().split("");
				for(int j = 0 ; j < N ; ++j) {
					arr[j][i] = Integer.parseInt(line[j]); 
				}
			}
			
			for(int i = 0 ; i <= N/2 ; ++i) {
				for(int j = N/2 - i ; j < N - N/2 + i ; ++j) {
					result += arr[j][i];
				}
			}
			for(int i = N/2 + 1 ; i < N ; ++i) {
				for(int j = i - N/2 ; j < N - (i - N/2); ++j) {
					result += arr[j][i];
				}
			}
			
			System.out.println("#" + t + " " + result);
		}
	}
}
