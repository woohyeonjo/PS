package BOJ.go;

import java.util.Arrays;
import java.util.Scanner;

public class B1026_보물 {
	static int[] A;
	static int[] B;
	static int[] C;
	static int N, ans, current;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		ans = 0;
		A = new int[N];
		B = new int[N];
		
		for(int i = 0 ; i < N ; ++i) A[i] = sc.nextInt();
		for(int i = 0 ; i < N ; ++i) B[i] = sc.nextInt();
		
		Arrays.sort(A);
		Arrays.sort(B);
		
		for(int i = 0 ; i < N ; ++i){
			ans += A[i] * B[N - 1 - i];
		}
		
		System.out.println(ans);
	}

	
	public static void print(int[] arr){
		for(int i = 0 ; i < arr.length ; ++i){
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
}
