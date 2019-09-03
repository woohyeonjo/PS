package BOJ.go;

import java.util.ArrayList;
import java.util.Scanner;

public class B2580_스도쿠 {
	static class Cell {
		int r, c;

		public Cell(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	
	static int[][] map = new int[9][9];
	static ArrayList<Cell> YEONG = new ArrayList<>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for(int r = 0 ; r < 9 ; ++r){
			for(int c = 0 ; c < 9 ; ++c){
				map[r][c] = sc.nextInt();
				if(map[r][c] == 0) YEONG.add(new Cell(r, c));
			}
		}
		go(0);
	}

	private static boolean go(int depth) {
		if(depth == YEONG.size()){
			print();
			return true;
		}
		
		Cell start = YEONG.get(depth);
		
		for(int i = 1 ; i < 10 ; ++i){
			if(!check(start.r, start.c, i)) continue;
			map[start.r][start.c] = i;
			if(go(depth + 1)) return true;
			map[start.r][start.c] = 0;
		}
		return false;
	}

	private static void print() {
		for(int r = 0 ; r < 9 ; ++r){
			for(int c = 0 ; c < 9 ; ++c){
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
	}

	private static boolean check(int R, int C, int num) {
		for(int r = 0 ; r < 9 ; ++r)if(map[r][C] == num) return false;
		for(int c = 0 ; c < 9 ; ++c)if(map[R][c] == num) return false;
		
		int rr = (R / 3) * 3;
		int cc = (C / 3) * 3;
		
		for(int r = rr ; r < rr + 3 ; ++r){
			for(int c = cc ; c < cc + 3 ; ++c){
				if(map[r][c] == num) return false;
			}
		}
		
		return true;
	}
}
