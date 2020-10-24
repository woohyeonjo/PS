package BOJ;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B1697 {
	static Queue<Node> q = new LinkedList<Node>();
	static boolean[] isVisited = new boolean[100001];
	static int N, K, ans = 0;
public static void main(String[] args) {
	
	Scanner sc = new Scanner(System.in);
	
	N = sc.nextInt();
	K = sc.nextInt();
	
	q.offer(new Node(N, 0));
	isVisited[N] = true;
	bfs();
	
	System.out.println(ans);
}

private static void bfs() {
	while(!q.isEmpty()) {
		Node n = q.poll();
		
		if(n.spot == K) {
			ans = n.time;
			return;
		}
		
		if(n.spot + 1 <= 100000 && !isVisited[n.spot + 1]) {
			q.offer(new Node(n.spot + 1, n.time + 1));
			isVisited[n.spot + 1] = true;
		}
		if(n.spot - 1 >= 0 && !isVisited[n.spot - 1]) {
			q.offer(new Node(n.spot - 1, n.time + 1));
			isVisited[n.spot - 1] = true;
		}
		if(n.spot * 2 <= 100000 && !isVisited[n.spot * 2]) {
			q.offer(new Node(n.spot * 2, n.time + 1));
			isVisited[n.spot * 2] = true;
		}
	}
}

static class Node{
	int spot, time;

	public Node(int spot, int time) {
		super();
		this.spot = spot;
		this.time = time;
	}
}
}
