package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1939_중량제한 {

	static class Node {
		int to, weight;
		
		Node(int to, int weight){
			this.to = to;
			this.weight = weight;
		}
	}
	
	static Queue<Integer> q;
	static boolean[] visited;
	static ArrayList<ArrayList<Node>> adj;
	static int N, M, max;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());

		max = 0;
		q = new LinkedList<>();
		visited = new boolean[N + 1];
		adj = new ArrayList<>();
		for(int i = 0 ; i <= N ; ++i) {
			adj.add(new ArrayList<>());
		}
		
		for(int i = 0 ; i < M ; ++i) {
			st = new StringTokenizer(br.readLine());
			int from = stoi(st.nextToken());
			int to = stoi(st.nextToken());
			int weight = stoi(st.nextToken());
			
			max = weight > max ? weight : max;

			adj.get(from).add(new Node(to, weight));
			adj.get(to).add(new Node(from, weight));
		}
		
		st = new StringTokenizer(br.readLine());
		int from = stoi(st.nextToken());
		int to = stoi(st.nextToken());
		
		System.out.println(binarySearch(from, to));
		
	}
	
	private static long binarySearch(int from, int to) {
		long left = 1;
		long right = max;
		long mid = 0;
		long ans = 0;
		
		while(left <= right) {
			mid = (left + right) / 2;
			
			if(go(from, to, mid)) {
				ans = mid > ans ? mid : ans;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		
		return ans;
	}

	private static boolean go(int from, int to, long weight) {
		clear();
		
		q.offer(from);
		visited[from] = true;
		
		while(!q.isEmpty()) {
			int now = q.poll();
			
			if(now == to) {
				return true;
			}
			
			for(Node next : adj.get(now)) {
				if(!visited[next.to] && next.weight >= weight) {
					q.offer(next.to);
					visited[next.to] = true;
				}
			}
		}
		
		return false;
	}
	
	private static void clear() {
		q.clear();
		
		for(int i = 1 ; i <= N ; ++i) {
			visited[i] = false;
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
