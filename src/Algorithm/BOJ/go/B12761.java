package Algorithm.BOJ.go;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B12761 {

	static Queue<Node> q = new LinkedList<Node>();
	static boolean[] isVisited = new boolean[100001];
	static int A, B, N, M, s, e, ans = 0;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		A = sc.nextInt();
		B = sc.nextInt();
		N = sc.nextInt();
		M = sc.nextInt();
		
		int[] move = {1, -1, A, -A, B, -B, A, B};
		q.offer(new Node(N, 0));
		isVisited[N] = true;
		bfs(move);

		System.out.println(ans);
	}

	private static void bfs(int[] move) {
		while (!q.isEmpty()) {
			Node n = q.poll();

			if (n.spot == M) {
				ans = n.time;
				return;
			}

			for(int i = 0 ; i < 8 ; ++i) {
				if(i >= 6) {
					if (n.spot * move[i] <= 100000 && !isVisited[n.spot * move[i]]) {
						q.offer(new Node(n.spot * move[i], n.time + 1));
						isVisited[n.spot * move[i]] = true;
					}
				} else {
					if (n.spot + move[i] >= 0 && n.spot + move[i] <= 100000 && !isVisited[n.spot + move[i]]) {
						q.offer(new Node(n.spot + move[i], n.time + 1));
						isVisited[n.spot + move[i]] = true;
					}
				}
			}
		}
	}

	static class Node {
		int spot, time;

		public Node(int spot, int time) {
			super();
			this.spot = spot;
			this.time = time;
		}

	}
}
