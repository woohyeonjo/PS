package Algorithm.BOJ.go;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class B7576 {
	// 1 ?��?? ?��마토, 0 ?���? ?��?? ?��마토, -1 ?��?��
	static int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static Tomato[][] container;
	static String[] line;
	static Queue<Tomato> q;
	static int N, M, day;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		line = in.readLine().split(" ");
		N = Integer.parseInt(line[1]);
		M = Integer.parseInt(line[0]);
		
		container = new Tomato[N][M];
		q = new LinkedList<Tomato>();
		day = 0;
		
		for(int row = 0 ; row < N ; ++row) {
			line = in.readLine().split(" ");
			for(int col = 0 ; col < M ; ++col) {
				container[row][col] = new Tomato(row, col, Integer.parseInt(line[col]), 0);
				if(container[row][col].status == 1) q.offer(container[row][col]);
			}
		}
		
		while(!q.isEmpty()) {
			Tomato t = q.poll();
			int beforeR = t.row;
			int beforeC = t.col;
			
			for(int d = 0 ; d < 4 ; ++d) {
				int nextR = beforeR + direction[d][0];
				int nextC = beforeC + direction[d][1];
				
				if (nextR >= 0 && nextR < N && nextC >= 0 && nextC < M) {
					Tomato temp = container[nextR][nextC];
					if(temp.status == 0) {
						temp.day = t.day + 1;
						temp.status = 1;
						q.offer(temp);
						day = temp.day > day ? temp.day : day;
					}
				}
			}
		}
		
		for(int row = 0 ; row < N ; ++row) {
			for(int col = 0 ; col < M ; ++col) {
				if(container[row][col].status == 0) day = -1;
			}
		}
		
		System.out.println(day);
		
	}
	static class Tomato{
		int row, col, status, day;

		public Tomato(int row, int col, int status, int day) {
			super();
			this.row = row;
			this.col = col;
			this.status = status;
			this.day = day;
		}

		@Override
		public String toString() {
			return  status + "";
		}
		
		
	}
}
