package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B15558_점프게임 {

	static class Cell {
		int r, c;
		
		Cell(int r, int c) { 
			this.r = r;
			this.c = c;
		}
		
		@Override
		public String toString() {
			return "(" + r + ", " + c + ")";
		}
	}
	
	static Queue<Cell> q;
	static int[][] map;
	static boolean[][] visited;
	static int N, K;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		N = stoi(st.nextToken());
		K = stoi(st.nextToken());
		
		map = new int[2][N + 1];
		visited = new boolean[2][N + 1];
		q = new LinkedList<>();
		
		for(int i = 0 ; i < 2 ; ++i) {
			char[] line = br.readLine().toCharArray();
			for(int j = 1 ; j <= N ; ++j) {
				map[i][j] = line[j - 1] - '0';
			}
		}
		
		visited[0][1] = true;
		q.offer(new Cell(0, 1));
		
		System.out.println(bfs());
	}
	
	private static int bfs() {
		int time = 0;
		
		while(!q.isEmpty()) {
			int size = q.size();
			time++;
			
			for(int i = 0 ; i < size ; ++i) {
				Cell cur = q.poll();
				
				// 다음 위치 3가지 만들기
				int[][] dir = {{cur.r, cur.c + 1}, {cur.r, cur.c - 1}, {(cur.r + 1) % 2, cur.c + K}};
			
				for(int j = 0 ; j < 3 ; ++j) {
					int nr = dir[j][0];
					int nc = dir[j][1];
					
					// N칸을 넘으면 게임 클리어
					if(nc > N) return 1;

					// 1보다 뒤로 갈 수 없으며 visited로 방문체크와 없어진 칸을 모두 관리한다.  
					if(nc <= 0 || visited[nr][nc] || map[nr][nc] == 0) continue;
					
					// 현재 시간과 같은 c의 칸으로 이동하는 경우는 큐에 넣으면 안된다.
					if(nc == time) continue;
					
					visited[nr][nc] = true;
					q.offer(new Cell(nr, nc));
				}
			}
			
			// 시간에 따라 앞쪽 칸이 사라짐
			visited[0][time] = true;
			visited[1][time] = true;
		}
		// 클리어 못할시 
		return 0;
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
