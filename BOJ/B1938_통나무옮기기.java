package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class B1938_통나무옮기기 {
	
	static class Log implements Comparable<Log> {
		int r, c, s;
		
		Log(int r, int c){
			this.r = r;
			this.c = c;
		}
		
		Log(int r, int c, int s){
			this.r = r;
			this.c = c;
			this.s = s;
		}

		@Override
		public int compareTo(Log o) {
			if(this.r == o.r) {
				return this.c - o.c;
			} else return this.r - o.r;
		}
		
		@Override
		public String toString() {
			return "[" + r + ", " + c + ", " + s +  "]";
		}
	}
	
	static int[][] dir = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
	static int[][] map;
	static boolean[][][] visited;
	static Queue<Log> q;
	static Log start, target;
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		visited = new boolean[N][N][2];
		q = new LinkedList<>();
		
		ArrayList<Log> starts = new ArrayList<>();
		ArrayList<Log> targets = new ArrayList<>();
		
		for(int i = 0 ; i < N ; ++i) {
			char[] line = br.readLine().toCharArray();
			for(int j = 0 ; j < N ; ++j) {
				char ch = line[j];
				if(ch == 'B') {
					starts.add(new Log(i, j));
					map[i][j] = 0;
				} else if(ch == 'E') {
					targets.add(new Log(i, j));
					map[i][j] = 0;
				} else {
					map[i][j] = ch - '0';
				}
			}
		}
		
		Collections.sort(starts);
		Collections.sort(targets);
		
		Log mid;
		
		if(starts.get(0).r == starts.get(2).r) {
			mid = starts.get(1);
			start = new Log(mid.r, mid.c, 1);
		} else {
			mid = starts.get(1);
			start = new Log(mid.r, mid.c, 0);
		}
		
		if(targets.get(0).r == targets.get(2).r) {
			mid = targets.get(1);
			target = new Log(mid.r, mid.c , 1);
		} else {
			mid = targets.get(1);
			target = new Log(mid.r, mid.c , 0);
		}
		
		visited[start.r][start.c][start.s] = true;
		q.offer(start);
		
		System.out.println(bfs());
	}

	private static int bfs() {
		int cnt = 0;
		
		while(!q.isEmpty()) {
			int size = q.size();
			
			for(int i = 0 ; i < size ; ++i) {
				Log cur = q.poll();
				
				if(cur.r == target.r &&
				   cur.c == target.c &&
				   cur.s == target.s) {
					   return cnt;
				}
				
				// 세로
				if(cur.s == 0) {
					// 위
					if(cur.r - 2 >= 0 && !visited[cur.r - 1][cur.c][cur.s]) {
						int nr = cur.r - 1;
						
						if(map[nr][cur.c] == 0 && map[nr - 1][cur.c] == 0 && map[nr + 1][cur.c] == 0) {
							q.offer(new Log(nr, cur.c, cur.s));
							visited[nr][cur.c][cur.s] = true;
						}
					}
					
					// 아래 
					if(cur.r + 2 < N && !visited[cur.r + 1][cur.c][cur.s]) {
						int nr = cur.r + 1;
						
						if(map[nr][cur.c] == 0 && map[nr - 1][cur.c] == 0 && map[nr + 1][cur.c] == 0) {
							q.offer(new Log(nr, cur.c, cur.s));
							visited[nr][cur.c][cur.s] = true;
						}
					}
					
					// 왼
					if(cur.c - 1 >= 0 && !visited[cur.r][cur.c - 1][cur.s]) {
						int nc = cur.c - 1;
						
						if(map[cur.r][nc] == 0 && map[cur.r - 1][nc] == 0 && map[cur.r + 1][nc] == 0) {
							q.offer(new Log(cur.r, nc, cur.s));
							visited[cur.r][nc][cur.s] = true;
						}
					}
					
					// 오른
					if(cur.c + 1 < N && !visited[cur.r][cur.c + 1][cur.s]) {
						int nc = cur.c + 1;
						
						if(map[cur.r][nc] == 0 && map[cur.r - 1][nc] == 0 && map[cur.r + 1][nc] == 0) {
							q.offer(new Log(cur.r, nc, cur.s));
							visited[cur.r][nc][cur.s] = true;
						}
					}
				} else {
					// 위
					if(cur.r - 1 >= 0 && !visited[cur.r - 1][cur.c][cur.s]) {
						int nr = cur.r - 1;
						
						if(map[nr][cur.c] == 0 && map[nr][cur.c - 1] == 0 && map[nr][cur.c + 1] == 0) {
							q.offer(new Log(nr, cur.c, cur.s));
							visited[nr][cur.c][cur.s] = true;
						}
					}
					
					// 아래 
					if(cur.r + 1 < N && !visited[cur.r + 1][cur.c][cur.s]) {
						int nr = cur.r + 1;
						
						if(map[nr][cur.c] == 0 && map[nr][cur.c - 1] == 0 && map[nr][cur.c + 1] == 0) {
							q.offer(new Log(nr, cur.c, cur.s));
							visited[nr][cur.c][cur.s] = true;
						}
					}
					
					// 왼
					if(cur.c - 2 >= 0 && !visited[cur.r][cur.c - 1][cur.s]) {
						int nc = cur.c - 1;
						
						if(map[cur.r][nc] == 0 && map[cur.r][nc - 1] == 0 && map[cur.r][nc + 1] == 0) {
							q.offer(new Log(cur.r, nc, cur.s));
							visited[cur.r][nc][cur.s] = true;
						}
					}
					
					// 오른
					if(cur.c + 2 < N && !visited[cur.r][cur.c + 1][cur.s]) {
						int nc = cur.c + 1;
						
						if(map[cur.r][nc] == 0 && map[cur.r][nc - 1] == 0 && map[cur.r][nc + 1] == 0) {
							q.offer(new Log(cur.r, nc, cur.s));
							visited[cur.r][nc][cur.s] = true;
						}
					}
				}
				
				// 회전
				boolean canTurn = true;
				for(int d = 0 ; d < 8 ; ++d) {
					int nr = cur.r + dir[d][0];
					int nc = cur.c + dir[d][1];
					
					if(nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] == 1) {
						canTurn = false;
						break;
					}
				}
				
				if(canTurn) {
					int ns = (cur.s + 1) % 2;
					if(visited[cur.r][cur.c][ns]) continue;
					visited[cur.r][cur.c][ns] = true;
					q.offer(new Log(cur.r, cur.c, ns));
				}
			}
			cnt++;
		}
		
		return 0;
	}
	
	
}
