package BOJ;


import java.util.PriorityQueue;
import java.util.Scanner;

public class B6087_레이저통신 {

	static class Node implements Comparable<Node>{
		int r, c;
		int dir, mirror;
		
		public Node(int r, int c, int dir, int mirror) {
			super();
			this.r = r;
			this.c = c;
			this.dir = dir;
			this.mirror = mirror;
		}

		@Override
		public int compareTo(Node o) {
			return this.mirror - o.mirror;
		}
		
		@Override
		public String toString() {
			return "[" + r + ", " + c + ", " + mirror + "]";
		}
	}
	
	static PriorityQueue<Node> pq;
	static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int[][] map;
	static boolean[][] visited;
	static int C, R, ans;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		C = sc.nextInt();
		R = sc.nextInt();
		sc.nextLine();
		
		map = new int[R][C];
		visited = new boolean[R][C];
		pq = new PriorityQueue<>();
		ans = 0;
		
		char[] line;
		for(int r = 0 ; r < R ; ++r){
			line = sc.nextLine().toCharArray();
			for(int c = 0 ; c < C ; ++c){
				switch(line[c]){
					case '.':
						map[r][c] = 0;
						break;
					case '*':
						map[r][c] = 1;
						break;
					case 'C':
						map[r][c] = 2;
						if(pq.isEmpty()) {
							pq.offer(new Node(r, c, -1, 0));
							visited[r][c] = true;
						}
						break;
				}
			}
		}
		
		bfs();
		System.out.println(ans);
	}

	private static void bfs() {
		int nr, nc;
		
		while(!pq.isEmpty()){
			Node node = pq.poll();
			
			if(!visited[node.r][node.c] && map[node.r][node.c] == 2){
				ans = node.mirror;
				return;
			}
			
			visited[node.r][node.c] = true;
			
			for(int i = 0 ; i < 4 ; ++i){
				nr = node.r + dir[i][0];
				nc = node.c + dir[i][1];
				
				if(nr >= R || nr < 0 || nc >= C || nc < 0 || visited[nr][nc]) continue;
				if(map[nr][nc] == 1) continue;
				if(node.dir == i || node.dir == -1){
					pq.offer(new Node(nr, nc, i, node.mirror));
				} else {
					pq.offer(new Node(nr, nc, i, node.mirror + 1));
				}
			}
		}
		
	}
}
