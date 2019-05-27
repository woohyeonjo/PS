package Algorithm.BOJ.go;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.TreeSet;

public class B2210 {
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static Cell[][] map = new Cell[5][5];
	static TreeSet<String> set = new TreeSet<String>();
	static Queue<Cell> q = new PriorityQueue<Cell>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for(int r = 0 ; r < 5 ; ++r) {
			for(int c = 0 ; c < 5 ; ++c) {
				map[r][c] = new Cell(r, c, sc.nextInt(), "");
				q.offer(map[r][c]);
			}
		}
		bfs();
		
		System.out.println(set.size());
	}
	
	private static void bfs() {
		int nr, nc;
		while(!q.isEmpty()) {
			Cell cell = q.poll();
			
			if(cell.digit.length() == 6) {
				set.add(cell.digit);
				continue;
			}
			
			for(int i = 0 ; i < 4; ++i) {
				nr = cell.r + dir[i][0];
				nc = cell.c + dir[i][1];
				
				if(nr >= 5 || nc >= 5 || nr < 0 || nc < 0) continue;
				Cell nCell = new Cell();
				nCell.r = map[nr][nc].r;
				nCell.c = map[nr][nc].c;
				nCell.num = map[nr][nc].num;
				nCell.digit = cell.digit + "" + map[nr][nc].num;
				q.offer(nCell);
			}
			
		}
		
	}

	static class Cell implements Comparable<Cell>{
		int r, c, num;
		String digit;
		
		public Cell() {
		}
		
		public Cell(int r, int c, int num, String digit) {
			super();
			this.r = r;
			this.c = c;
			this.num = num;
			this.digit = digit;
		}

		@Override
		public int compareTo(Cell o) {
			return this.digit.length() - o.digit.length();
		}
	}
}
