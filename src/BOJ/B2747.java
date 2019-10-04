package BOJ;



import java.util.Scanner;

public class B2747 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] fiboNumber = new int[N + 1];
		fiboNumber[0] = 0;
		fiboNumber[1] = 1;
		
		
		for(int i = 2 ; i <= N ; i++) {
			fiboNumber[i] = fiboNumber[i - 1] + fiboNumber[i - 2];
		}
		System.out.println(fiboNumber[N]);
	}
}
