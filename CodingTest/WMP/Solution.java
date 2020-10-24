package CodingTest.WMP;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Solution {
	// 다익스트라 알고리즘 역으로 이용하기
	static class Rail implements Comparable<Rail> {
		int to, passenger;

		public Rail(int to, int passenger) {
			this.to = to;
			this.passenger = passenger;
		}

		@Override
		public int compareTo(Rail o) {
			return -(this.passenger - o.passenger);
		}
	}

	
	static ArrayList<Rail>[] adj;
	static PriorityQueue<Rail> q;
	static boolean[] visited;
	static int[] amount, passengers;
	static int station; 
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		station = sc.nextInt();
		
		visited = new boolean[station + 1];
		amount = new int[station + 1];
		passengers = new int[station + 1];
		adj = new ArrayList[station + 1];
		for(int i = 1 ; i <= station ; ++i) adj[i] = new ArrayList<Rail>();
		q = new PriorityQueue<Rail>();
		
		for(int i = 1 ; i <= station ; ++i) {
			passengers[i] = sc.nextInt();
		}
		
		int from, to;
		for(int i = 0 ; i < station - 1 ; ++i) {
			from = sc.nextInt();
			to = sc.nextInt();
			adj[from].add(new Rail(to, passengers[to]));
			adj[to].add(new Rail(from, passengers[from]));
		}
		
		q.offer(new Rail(1, passengers[1]));
		amount[1] = passengers[1];
		
		while(!q.isEmpty()) {
			Rail r = q.poll();
			
			visited[r.to] = true;
			
			for(Rail nr : adj[r.to]) {
				if(visited[nr.to]) continue;
				if(amount[nr.to] < amount[r.to] + nr.passenger) {
					amount[nr.to] = amount[r.to] + nr.passenger;
					q.offer(new Rail(nr.to, amount[nr.to]));
				}
			}
		}
		
		int max = 0, end = 1;
		for(int i = 2 ; i <= station ; ++i) {
			if(amount[i] >= max) {
				max = amount[i];
				end = i;
			}
		}
		
		System.out.println(end + " " + max);
	}
}