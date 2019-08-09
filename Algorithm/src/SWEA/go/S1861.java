package SWEA.go;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class S1861 {
	
	static Room[][] map;
	static int[][] dir = {{-1, 0}, {1,0}, {0, -1}, {0, 1}};
	static Queue<Room> q;
	static int T, N, roomNum, count;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		for(int t = 1; t <= T ; ++t) {
			N = sc.nextInt();
			
			map = new Room[N][N];
			q = new PriorityQueue<Room>();
			count = 0;
			
			for(int row = 0 ; row < N ; ++row) {
				for(int col = 0 ; col < N ; ++col) {
					map[row][col] = new Room(row, col, sc.nextInt());
					q.offer(map[row][col]);
				}
			}
			bfs();
			
			System.out.println("#" + t + " " + roomNum + " " + count);
		}
	}
	
	private static void bfs() {
		int cnt;
		boolean isGoingOn;
		int nextR, nextC, beforeR, beforeC, beforeVal;
		
		while(!q.isEmpty()) {
			Room r = q.poll();
			beforeR = r.row;
			beforeC = r.col;
			beforeVal = r.val;
			cnt = 1;
			
			while(true) {
				isGoingOn = false;
				for(int i = 0 ; i < 4 ; ++i) {
					nextR = beforeR + dir[i][0];
					nextC = beforeC + dir[i][1];
					if(nextR >= 0 && nextR < N && nextC >=0 && nextC < N) {
						Room nextRoom = map[nextR][nextC];
						if(nextRoom.val == beforeVal + 1) {
							cnt++;
							beforeVal = nextRoom.val;
							if(cnt > count) {
								count = cnt;
								roomNum = r.val;
							}
							beforeR = nextR;
							beforeC = nextC;
							isGoingOn = true;
							break;
						}
					}
				}
				if(!isGoingOn) break;
			}
		}
		
	}

	static class Room implements Comparable<Room> { 
		int row, col, val;

		public Room(int row, int col, int val) {
			super();
			this.row = row;
			this.col = col;
			this.val = val;
		}

		@Override
		public int compareTo(Room o) {
			// TODO Auto-generated method stub
			return this.val - o.val;
		}
		
		
		
	}
}
