package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B5213_과외맨 {
	
	static class Node {
		int r, c;
		// 각 노드가 자신이 지나온 경로를 모두 가지고 있게 하였다.
		ArrayList<Integer> path;
		
		Node(int r, int c, ArrayList<Integer> path) {
			this.r = r;
			this.c = c;
			this.path = path;
		}
		
		void add(int i){
			this.path.add(i);
		}
	}
	
	static Queue<Node> q;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int[][] map;
	static int[][] tileMap;
	static boolean[][] visited;
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = stoi(br.readLine());
		
		q = new LinkedList<>();
		map = new int[N + 1][N * 2 + 1];
		tileMap = new int[N + 1][N * 2 + 1];
		visited = new boolean[N + 1][N * 2 + 1];
		
		// 각 칸에 1 ~ 6의 숫자가 적혀있는 Map 배열과 각 칸이 몇번째 타일인지 적혀있는 tileMap 배열
		int tile = N;
		int tileNum = 1;
		int c = 1;
		for(int r = 1 ; r <= N ; ++r) {
			if(r % 2 == 0) {
				tile = N - 1;
				c = 2;
			} else {
				tile = N;
				c = 1;
			}
			
			for(int i = 0 ; i < tile ; ++i) {
				st = new StringTokenizer(br.readLine());
				tileMap[r][c] = tileNum;
				map[r][c++] = stoi(st.nextToken());
				tileMap[r][c] = tileNum;
				map[r][c++] = stoi(st.nextToken());
				tileNum++;
			}
		}
		
		// 타일 단위로 이동해야하기 때문에 같은 타일의 두 개의 칸을 동시에 넣는다. 
		visited[1][1] = true;
		visited[1][2] = true;
		ArrayList<Integer> path = new ArrayList<>();
		path.add(1);
		q.offer(new Node(1, 1, path));
		q.offer(new Node(1, 2, path));
		
		Node ans = bfs();
		
		System.out.println(ans.path.size());
		for(Integer i : ans.path) {
			System.out.print(i + " ");
		}
	}
	
	private static Node bfs() {
		Node ans = null;
		int max = 0;
		while(!q.isEmpty()) {
			Node now = q.poll();
			
			// 타일번호가 가장 큰 것을 계속 갱신한다. 
			if(tileMap[now.r][now.c] > max) {
				max = tileMap[now.r][now.c];
				ans = now;
			}
			
			for(int d = 0 ; d < 4 ; ++d) {
				int nr = now.r + dir[d][0];
				int nc = now.c + dir[d][1];
				
				if(nr > N || nr < 1 || nc > N * 2 || nc < 1 ||
					map[nr][nc] == 0 || visited[nr][nc]) continue;
				
				// 다른 타일로 넘어갈 때 
				if(map[now.r][now.c] == map[nr][nc]) {
					ArrayList<Integer> path = new ArrayList<>();
					path.addAll(now.path);
					path.add(tileMap[nr][nc]);
					
					// 탐색한 타일의 칸을 큐에 넣는다. 
					visited[nr][nc] = true;
					q.offer(new Node(nr, nc, path));

					// 탐색한 타일의 다른 칸을 큐에 넣는다. 
					if(tileMap[nr][nc + 1] == tileMap[nr][nc]) {
						visited[nr][nc + 1] = true;
						q.offer(new Node(nr, nc + 1, path));
					} else if(tileMap[nr][nc - 1] == tileMap[nr][nc]) {
						visited[nr][nc - 1] = true;
						q.offer(new Node(nr, nc - 1, path));
					}
				}
			}
		}
		
		return ans;
	}
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
