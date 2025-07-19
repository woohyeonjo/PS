package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class S5653_모의줄게세포배양 {
	
	static class Cell implements Comparable<Cell>{
		int x, y, vitality;
		int start, end;
		
		public Cell(int x, int y, int vitality, int start, int end) {
			this.x = x;
			this.y = y;
			this.vitality = vitality;
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Cell o) {
			return -(this.vitality - o.vitality);
		}
	}
	
	static final int CORRECTION_VALUE = 400;
	static final int MAP_SIZE = 1000;
	
	static int[][] map;
	static boolean[][] visited;
	
	static PriorityQueue<Cell> pq;
	static Queue<Cell> q;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int T, N, M, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = stoi(br.readLine());
		
		for(int t = 1 ; t <= T ; ++t) {
			st = new StringTokenizer(br.readLine());
			
			N = stoi(st.nextToken());
			M = stoi(st.nextToken());
			K = stoi(st.nextToken());
			
			q = new LinkedList<>();
			pq = new PriorityQueue<>();
			map = new int[MAP_SIZE][MAP_SIZE];
			visited = new boolean[MAP_SIZE][MAP_SIZE];
			
			for(int i = 0 ; i < N ; ++i) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < M ; ++j) {
					int x = CORRECTION_VALUE + i;
					int y = CORRECTION_VALUE + j;
					int vitality = stoi(st.nextToken());
					
					if(vitality > 0) {
						map[x][y] = vitality;
						visited[x][y] = true;
						q.add(new Cell(x, y, vitality, vitality, vitality * 2));
					}
				}
			}
			
			for(int time = 1 ; time <= K ; ++time) {
				print();
				check(time);
				bfs(time);
			}
			
			System.out.println("#" + t + " " + q.size());
		}
	}
	
	
	
	private static void bfs(int time) {
		while(!pq.isEmpty()) {
			Cell cell = pq.poll();
			
			if (time < cell.end) { 
				q.add(cell);
			}
			
			for(int d = 0 ; d < 4 ; ++d) {
				int nx = cell.x + dx[d];
				int ny = cell.y + dy[d];
				
				if(!visited[nx][ny]) {
					visited[nx][ny] = true;
					map[nx][ny] = cell.vitality;
					q.add(new Cell(nx, ny, cell.vitality,
							time + cell.vitality,
							time + cell.vitality * 2));
				}
			}
		}
	}

	private static void check(int time) {
		int qSize = q.size();
		
		for(int i = 0 ; i < qSize ; ++i) {
			Cell cell = q.poll();
			
			if(time <= cell.start) {
			// 활성화 되기 전 
				q.add(cell);
			} else if(time == cell.start + 1) {
			// 활성화
				pq.add(cell);
			} else if(time > cell.start && time < cell.end) {
			// 죽기 전 
				q.add(cell);
			}
			
		}
		
	}

	private static void print() {
		for (int i = CORRECTION_VALUE - 20 ; i < CORRECTION_VALUE + 50 ; i++) {
			for (int j = CORRECTION_VALUE - 20; j < CORRECTION_VALUE + 50; j++) {
				System.out.format("%2c", map[i][j] == 0 ? ' ' : map[i][j] + '0');
			}
			System.out.println();
		}
		System.out.println();
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
