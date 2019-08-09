package BOJ.go;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B2161 {
	
	static Queue<Integer> q = new LinkedList<Integer>();
	static int N;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		for(int n = 1 ; n <= N ; ++n) q.offer(n);
		
		while(q.size() > 1){
			System.out.print(q.poll() + " ");
			q.offer(q.poll());
		}
		System.out.print(q.poll() + " ");
	}
}
