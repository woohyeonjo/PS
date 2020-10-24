package BOJ;



import java.util.Arrays;
import java.util.Scanner;

public class B1978 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int count = 0;
		int[] arr = new int[100];
		
		Arrays.fill(arr, 1001);
		for(int i = 0 ; i < N ; i++) {
			arr[i] = sc.nextInt();
		}
		
		Arrays.sort(arr);
		
		for(int i = 0; i < N ; i++ ) {
				if(arr[i] == 1) {
					arr[i] = 0;
					continue;
				}
			for(int j = 2 ; j * j <= arr[i] ; j++ ) {
				if(arr[i] != j && arr[i] % j == 0) {
					arr[i] = 0;
				}
			}
		}
		
		for(int i = 0 ; i < N ; i++) {
			if(arr[i] != 0) {
				count++;
			}
		}
		
		System.out.println(count);
	}
}
