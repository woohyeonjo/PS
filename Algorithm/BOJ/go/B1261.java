package Algorithm.BOJ.go;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class B1261 {
	
	static Room[][] map;
	static boolean[][] visited;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static Queue<Room> q;
	static int N, M, ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] line = in.readLine().split(" ");
		
		M = Integer.parseInt(line[0]);
		N = Integer.parseInt(line[1]);
		
		map = new Room[N][M];
		visited = new boolean[N][M];
		q = new PriorityQueue<Room>();
		ans = Integer.MAX_VALUE;
		
		for(int r = 0 ;r < N ; ++r) {
			line = in.readLine().split("");
			for(int c = 0 ; c < M ; ++c) {
				map[r][c] = new Room(r, c, Integer.parseInt(line[c]), 0);
			}
		}
		
		q.offer(map[0][0]);
		visited[0][0] = true;
		
		bfs();
		
		System.out.println(ans);
	}
	
	
	private static void bfs() {
		while(!q.isEmpty()) {
			Room room = q.poll();
			int nr = 0, nc = 0;
			
			if(room.r == N - 1 && room.c == M - 1) {
				ans = ans > room.cnt ? room.cnt : ans;
				return;
			}
			
			for(int i = 0 ; i < 4; ++i) {
				nr = room.r + dir[i][0];
				nc = room.c + dir[i][1];
				
				if(nr >= N || nr < 0 || nc >= M || nc < 0 || visited[nr][nc]) continue;
				Room nRoom = new Room(nr, nc, map[nr][nc].type, map[nr][nc].cnt);
				if(nRoom.type == 1) nRoom.cnt = room.cnt + 1;
				else nRoom.cnt = room.cnt;
				visited[nRoom.r][nRoom.c] = true;
				q.offer(nRoom);
			}
		}
	}


	static class Room implements Comparable<Room>{
		int r, c, type, cnt;

		public Room(int r, int c, int type, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.type = type;
			this.cnt = cnt;
		}
		
		@Override
		public String toString() {
			return this.cnt + "";
		}

		@Override
		public int compareTo(Room o) {
			return this.cnt - o.cnt;
		}
	}
}
