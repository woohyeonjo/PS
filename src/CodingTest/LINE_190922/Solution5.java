package CodingTest.LINE_190922;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution5 {
	
	static class Point {
		int x, y, time;

		public Point(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}
	
	static int N, M, X, Y, time, cnt;
	static Queue<Point> q;
	static int[][] dir = {{1, 0}, {0, 1}};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		X = sc.nextInt();
		Y = sc.nextInt();
		
		if(X > N || X < 0 || Y > M || Y < 0) {
			System.out.println("fail");
			return;
		}
		
		time = Integer.MAX_VALUE;
		cnt = 0;
		
		q = new LinkedList<Point>();
		q.offer(new Point(0, 0, 0));
		
		bfs();
		
		System.out.println(time);
		System.out.println(cnt);
		
	}

	private static void bfs() {
		int nx, ny;
		
		while(!q.isEmpty()) {
			Point now = q.poll();
			
			if(now.x == X && now.y == Y) {
				if(time == now.time) cnt++; 
				else if(time > now.time) {
					time = now.time;
					cnt = 1;
				}
				continue;
			}
			
			for(int i = 0 ; i < 2 ; ++i) {
				nx = now.x + dir[i][0];
				ny = now.y + dir[i][1];
				
				if(nx > N || nx < 0 || ny > M || ny < 0) continue;
				q.offer(new Point(nx, ny, now.time + 1));
			}
		}
		
	}
}
