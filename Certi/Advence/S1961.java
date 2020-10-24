package SWEA;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class S1961 {
	
	static int[][] arr;
	static StringBuilder sb;
	
	public static String rotation(int[][] arr) {
		int size = arr.length;
		
		sb = new StringBuilder();
		
		for(int n = 0 ; n < size ; ++n) {
			for(int i = size - 1 ; i >= 0 ; --i) {
				sb.append(arr[i][n]);
			}
			sb.append(" ");
			
			for(int i = size - 1 ; i >= 0 ; --i) {
				sb.append(arr[size - 1 - n][i]);
			}
			sb.append(" ");
			
			for(int i = 0 ; i < size ; ++i) {
				sb.append(arr[i][size - 1 - n]);
			}
			sb.append(System.getProperty("line.separator"));
		}
		
		return sb.toString();
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int t = 1 ; t <= T ; ++t) {
			int N = sc.nextInt();
			arr = new int[N][N];
			
			for(int n = 0 ; n < N ; ++n) {
				for(int m = 0 ; m < N ; ++m) {
					arr[n][m] = sc.nextInt();
				}
			}
			
			System.out.println("#" + t);
			System.out.print(rotation(arr));
		
		}
	}
}
