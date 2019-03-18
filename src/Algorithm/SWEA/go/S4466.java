package Algorithm.SWEA.go;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class S4466 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int t = 1 ; t <= T ; ++t) {
			int N = sc.nextInt();
			int K = sc.nextInt();
			int result = 0;
			
			int[] arr = new int[N];
			
			for(int n = 0 ; n < N ; ++n) arr[n] = sc.nextInt();
			Arrays.sort(arr);
			int index = arr.length - 1;
			
			for(int k = 0 ; k < K ; ++k) result += arr[index--];
			
			System.out.println("#" + t + " " + result);
		}
	}
}
