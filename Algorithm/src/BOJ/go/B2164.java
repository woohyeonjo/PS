package BOJ.go;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B2164 {

	static Queue<Integer> q = new LinkedList<Integer>();
	static int N;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int card = 0;

		N = sc.nextInt();

		for (int n = 1; n <= N; ++n)
			q.offer(n);
		while (!q.isEmpty()) {
			card = q.poll();
			if (q.size() == 0)
				break;
			q.offer(q.poll());
		}
		System.out.print(card);
	}
}
