package Algorithm.BOJ.go;


import java.util.Scanner;

public class B4344 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int C = sc.nextInt();
		
		for(int t = 1 ; t <= C ; t++) {
			int N = sc.nextInt();
			int[] arr = new int[N];
			int count = 0;
			int sum = 0;
			float avg = 0;
			
			for(int n = 0 ; n < N ; n++) {
				int temp = sc.nextInt();
				arr[n] = temp;
				sum += temp;
			}
			avg = sum / N;
			
			for(int i : arr) {
				if(i > avg) {
					count++;
				}
			}
			System.out.print(String.format("%.3f", (float)count/N * 100));
			System.out.println("%");
		}
	}
}
