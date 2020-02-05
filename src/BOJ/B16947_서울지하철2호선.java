package BOJ;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B16947_서울지하철2호선 {
	
	static ArrayList<Integer> cycle;
	static ArrayList<ArrayList<Integer>> adj;
	static Queue<Integer> q;
	static boolean[] visited;
	static int[] distance;
	static int N;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		q = new LinkedList<>();
		distance = new int[N + 1];
		Arrays.fill(distance, -1);
		cycle = new ArrayList<>();
		adj = new ArrayList<>();
		for(int i = 0 ; i < N + 1 ; ++i) {
			adj.add(new ArrayList<>());
		}
		
		for(int i = 0 ; i < N ; ++i) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			
			adj.get(from).add(to);
			adj.get(to).add(from);
		}
		
		for(int i = 1 ; i < N + 1 ; ++i) {
			visited = new boolean[N + 1];
			visited[i] = true;
			if(findCycle(i, i, 1)) {
				for(Integer station : cycle) {
					distance[station] = 0;
				}
				break;
			}
		}

		for(int i = 1 ; i < N + 1 ; ++i) {
			if(distance[i] == 0) q.offer(i);
		}
		
		bfs();
		
		for(int i = 1 ; i < N + 1 ; ++i) {
			System.out.print(distance[i] + " ");
		}
	}

	private static void bfs() {
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(Integer idx : adj.get(cur)) {
				if(distance[idx] == -1) {
					q.offer(idx);
					distance[idx] = distance[cur] + 1;
				}
			}
		}
	}

	private static boolean findCycle(int start, int from, int depth) {
		for(Integer to : adj.get(from)) {
			if(visited[to] && to == start && depth >= 3) {
				cycle.add(to);
				return true;
			}
			if(!visited[to]) {
				cycle.add(to);
				visited[to] = true;
				if(findCycle(start, to, depth + 1)) return true;
				visited[to] = false;
				cycle.remove(cycle.size() - 1);
			}
		}
		
		return false;
	}
	
}
