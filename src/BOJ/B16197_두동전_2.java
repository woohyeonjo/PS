package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B16197_두동전_2 {

	static class Node {
		int r, c, t;
		
		Node(int r, int c, int t){
			this.r = r;
			this.c = c;
			this.t = t;
		}
	}
	
	static char[][] map;
	static Queue<Node> q;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int N, M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		q = new LinkedList<Node>();
		
		for(int i = 0 ; i < N ; ++i) {
			char[] line = br.readLine().toCharArray();
			for(int j = 0 ; j < M ; ++j) {
				map[i][j] = line[j];
				if(map[i][j] == 'o') {
					q.offer(new Node(i, j, 0));
				}
			}
		}
		
		System.out.println(bfs());
	}

	private static int bfs() {
		while(!q.isEmpty()) {
			Node coin1 = q.poll();
			Node coin2 = q.poll();
			
			if(coin1.t >= 10) return -1;
	
			for(int d = 0 ; d < 4 ; ++d) {
				boolean drop1 = false;
				boolean drop2 = false;
				
				int nr1 = coin1.r + dir[d][0];
				int nc1 = coin1.c + dir[d][1];
				int nr2 = coin2.r + dir[d][0];
				int nc2 = coin2.c + dir[d][1];
				
				// 두 동전의 떨어짐을 체크 
				if(nr1 >= N || nr1 < 0 || nc1 >= M || nc1 < 0) {
					drop1 = true;
				}
				if(nr2 >= N || nr2 < 0 || nc2 >= M || nc2 < 0) {
					drop2 = true;
				}
				
				// 두 동전 모두 떨어진 경우
				if(drop1 && drop2) continue;
				// 한 동전만 떨어진 경우
				if(drop1 || drop2) {
					return coin1.t + 1;
				}
	
				// 두 동전 모두 안떨어진 경우 
				// 동전이 벽을 만난 경우
				if(map[nr1][nc1] == '#' && map[nr2][nc2] == '#') continue;
				
				if(map[nr1][nc1] == '#') {
					nr1 = coin1.r;
					nc1 = coin1.c;
				}
				if(map[nr2][nc2] == '#') {
					nr2 = coin2.r;
					nc2 = coin2.c;
				}
				
				q.offer(new Node(nr1, nc1, coin1.t + 1));
				q.offer(new Node(nr2, nc2, coin1.t + 1));
			}
		}
		return -1;
	}
}