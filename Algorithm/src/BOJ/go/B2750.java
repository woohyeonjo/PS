package BOJ.go;


import java.util.Arrays;
import java.util.Scanner;

public class B2750 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[1000];
		Arrays.fill(arr, 1001);
		
		for(int i = 0 ; i < N ; i++) {
			arr[i] = sc.nextInt();
		}
		
		Arrays.sort(arr);
		
		for(int i = 0 ; i < N ; i++) {
			System.out.println(arr[i]);
		}
		
	}
}
