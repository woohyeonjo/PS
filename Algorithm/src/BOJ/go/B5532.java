package BOJ.go;

import java.util.Scanner;

public class B5532 {
	
	static int L, A, B, C, D, ans;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		L = sc.nextInt();
		A = sc.nextInt();
		B = sc.nextInt();
		C = sc.nextInt();
		D = sc.nextInt();
		
		int korea = Math.round(A / C);
		int math = Math.round(B / D);
		
		if(A % C != 0) korea++;
		if(B % D != 0) math++;
		
		ans = (korea > math ? L - korea : L - math);
		System.out.println(ans);
	}
}
