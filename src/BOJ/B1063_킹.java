package BOJ;


import java.util.Scanner;

public class B1063_킹 {
	
	static final int[] R = {0, 1};
	static final int[] L = {0, -1};
	static final int[] B = {1, 0};
	static final int[] T = {-1, 0};
	static final int[] RT = {-1, 1};
	static final int[] LT = {-1, -1};
	static final int[] RB = {1, 1};
	static final int[] LB = {1, -1};
	
	static class Node {
		int x, y;

		public Node(){}
		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	static int[][] map = new int[8 + 1][8 + 1];
	static Node king, stone;
	static char[] input;
	static int N;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String s = sc.next();
		king = new Node();
		king.x = (s.charAt(0) - 'A') + 1;
		king.y = Math.abs((s.charAt(1) - '0') - 9);
		
		s = sc.next();
		stone = new Node();
		stone.x = (s.charAt(0) - 'A') + 1;
		stone.y = Math.abs((s.charAt(1) - '0') - 9);
		
		N = sc.nextInt();
		
		for(int i = 0 ; i < N ; ++i){
			move(sc.next());
		}
		
		System.out.println((char)(king.x - 1 + 'A') + "" + Math.abs(king.y - 9));
		System.out.println((char)(stone.x - 1 + 'A') + "" + Math.abs(stone.y - 9));
		
	}

	private static void move(String commend) {
		int nx, ny;
		switch(commend){
		case "R":
			nx = king.x + R[1];
			ny = king.y + R[0];
			if(nx > 8 || nx < 1 || ny > 8 || ny < 1) return;
			if(nx == stone.x && ny == stone.y){
				nx = stone.x + R[1];
				ny = stone.y + R[0];
				if(nx > 8 || nx < 1 || ny > 8 || ny < 1) return;
				king.x = stone.x;
				king.y = stone.y;
				stone.x = nx;
				stone.y = ny;
				return;
			}
			king.x = nx;
			king.y = ny;
			return;
		case "L":
			nx = king.x + L[1];
			ny = king.y + L[0];
			if(nx > 8 || nx < 1 || ny > 8 || ny < 1) return;
			if(nx == stone.x && ny == stone.y){
				nx = stone.x + L[1];
				ny = stone.y + L[0];
				if(nx > 8 || nx < 1 || ny > 8 || ny < 1) return;
				king.x = stone.x;
				king.y = stone.y;
				stone.x = nx;
				stone.y = ny;
				return;
			}
			king.x = nx;
			king.y = ny;
			return;
		case "B":
			nx = king.x + B[1];
			ny = king.y + B[0];
			if(nx > 8 || nx < 1 || ny > 8 || ny < 1) return;
			if(nx == stone.x && ny == stone.y){
				nx = stone.x + B[1];
				ny = stone.y + B[0];
				if(nx > 8 || nx < 1 || ny > 8 || ny < 1) return;
				king.x = stone.x;
				king.y = stone.y;
				stone.x = nx;
				stone.y = ny;
				return;
			}
			king.x = nx;
			king.y = ny;
			return;
		case "T":
			nx = king.x + T[1];
			ny = king.y + T[0];
			if(nx > 8 || nx < 1 || ny > 8 || ny < 1) return;
			if(nx == stone.x && ny == stone.y){
				nx = stone.x + T[1];
				ny = stone.y + T[0];
				if(nx > 8 || nx < 1 || ny > 8 || ny < 1) return;
				king.x = stone.x;
				king.y = stone.y;
				stone.x = nx;
				stone.y = ny;
				return;
			}
			king.x = nx;
			king.y = ny;
			return;
		case "RT":
			nx = king.x + RT[1];
			ny = king.y + RT[0];
			if(nx > 8 || nx < 1 || ny > 8 || ny < 1) return;
			if(nx == stone.x && ny == stone.y){
				nx = stone.x + RT[1];
				ny = stone.y + RT[0];
				if(nx > 8 || nx < 1 || ny > 8 || ny < 1) return;
				king.x = stone.x;
				king.y = stone.y;
				stone.x = nx;
				stone.y = ny;
				return;
			}
			king.x = nx;
			king.y = ny;
			return;
		case "LT":
			nx = king.x + LT[1];
			ny = king.y + LT[0];
			if(nx > 8 || nx < 1 || ny > 8 || ny < 1) return;
			if(nx == stone.x && ny == stone.y){
				nx = stone.x + LT[1];
				ny = stone.y + LT[0];
				if(nx > 8 || nx < 1 || ny > 8 || ny < 1) return;
				king.x = stone.x;
				king.y = stone.y;
				stone.x = nx;
				stone.y = ny;
				return;
			}
			king.x = nx;
			king.y = ny;
			return;
		case "RB":
			nx = king.x + RB[1];
			ny = king.y + RB[0];
			if(nx > 8 || nx < 1 || ny > 8 || ny < 1) return;
			if(nx == stone.x && ny == stone.y){
				nx = stone.x + RB[1];
				ny = stone.y + RB[0];
				if(nx > 8 || nx < 1 || ny > 8 || ny < 1) return;
				king.x = stone.x;
				king.y = stone.y;
				stone.x = nx;
				stone.y = ny;
				return;
			}
			king.x = nx;
			king.y = ny;
			return;
		case "LB":
			nx = king.x + LB[1];
			ny = king.y + LB[0];
			if(nx > 8 || nx < 1 || ny > 8 || ny < 1) return;
			if(nx == stone.x && ny == stone.y){
				nx = stone.x + LB[1];
				ny = stone.y + LB[0];
				if(nx > 8 || nx < 1 || ny > 8 || ny < 1) return;
				king.x = stone.x;
				king.y = stone.y;
				stone.x = nx;
				stone.y = ny;
				return;
			}
			king.x = nx;
			king.y = ny;
			return;
		}
	}
}
