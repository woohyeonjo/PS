package BOJ;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B17142_연구소3_3 {
	
	static class Node {
		int r, c;
		int time;
		
		public Node(int r, int c, int time) {
			super();
			this.r = r;
			this.c = c;
			this.time = time;
		}
		
		@Override
		public String toString() {
			return "[" + r + ", " + c + "]";
		}
	}
	
	static Queue<Node> q;
	static int[][] map;
	static int[][] temp;
	static boolean[][] visited;
	static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static Node[] selected;
	static int N, M, ans, current;
	
	public static void main(String[] args) {
		 Scanner sc = new Scanner(System.in);
		 
		 N = sc.nextInt();
		 M = sc.nextInt();
		 ans = Integer.MAX_VALUE;
		 
		 q = new LinkedList<>();
		 map = new int[N][N];
		 visited = new boolean[N][N];
		 selected = new Node[M];
		 
		 for(int r = 0 ; r < N ; ++r){
			 for(int c = 0 ; c < N ; ++c){
				 map[r][c] = -(sc.nextInt());
			 }
		 }
		 
		 select(0, -1);
		 if(ans == Integer.MAX_VALUE) ans = -1;
		 System.out.println(ans);
	}

	private static void select(int index, int limit) {
		
		if(index == M){
			temp = copy ();
			for(Node node : selected) q.offer(node);
			visited = new boolean[N][N];
			spread();
			current = check();
			ans = ans > current ? current : ans;
			return;
		}
		
		for(int r = 0 ; r < N ; ++r){
			for(int c = 0 ; c < N ; ++c){
				if(r * 100 + c <= limit) continue;
				if(map[r][c] != -2) continue;
				selected[index] = new Node(r, c, 0);
				visited[r][c] = true;
				select(index + 1, r * 100 + c);
				visited[r][c] = false;
			}
		}
	}

	private static void spread() {
		int nr, nc;
		
		while(!q.isEmpty()){
			Node node = q.poll();
			
			for(int i = 0 ; i < 4 ; ++i){
				nr = node.r + dir[i][0];
				nc = node.c + dir[i][1];
				
				if(nr >= N || nr < 0 || nc >= N || nc < 0 || visited[nr][nc]) continue;
				if(temp[nr][nc] == -1) continue;
				else if(temp[nr][nc] == 0) {
					visited[nr][nc] = true;
					temp[nr][nc] = node.time + 1;
					q.offer(new Node(nr, nc, temp[nr][nc]));
				} else if (temp[nr][nc] == -2) {
					visited[nr][nc] = true;
					q.offer(new Node(nr, nc, node.time + 1));
				}
			}
		}
	}

	private static int check() {
		int time = 0;
		
		for(int r = 0 ; r < N ; ++r){
			for(int c = 0 ; c < N ; ++c){
				if(temp[r][c] == 0) return Integer.MAX_VALUE;
				time = time < temp[r][c] ? temp[r][c] : time;
			}
		}
		
		return time;
	}
	
	private static int[][] copy() {
		int[][] temp = new int[N][N];
		
		for(int r = 0 ; r < N ; ++r){
			for(int c = 0 ; c < N ; ++c){
				temp[r][c] = map[r][c];
			}
		}
		
		return temp;
	}
}
