package BOJ;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B2931_가스관 {
	
	static class Node {
		int x, y;
		int type;
		
		public Node(int r, int c, int type) {
			this.x = r;
			this.y = c;
			this.type = type;
		}
		
		@Override
		public String toString() {
			return "[" + x + ", " + y + ", " + types[type] + "]";
		}
	}
	
	static char[] types = {'.', '1', '2', '3', '4', '|', '-', '+', 'M', 'Z'};
	static int[][] map;
	static boolean[][] visited;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static Queue<Node> q;
	static Node M, Z, target;
	static int R, C, ans;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;



	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		R = sc.nextInt();
		C = sc.nextInt();
		sc.nextLine();
		
		map = new int[R + 2][C + 2];
		visited = new boolean[R + 1][C + 1];
		q = new LinkedList<>();
		
		char[] line;
		for(int r = 1 ; r <= R ; ++r){
			line = sc.nextLine().toCharArray();
			for(int c = 1 ; c <= C ; ++c){
				switch(line[c - 1]){
					default :
						map[r][c] = line[c - 1] - '0';
						break;
					case '.':
						map[r][c] = 0;
						break;
					case '-':
						map[r][c] = 6;
						break;
					case '|':
						map[r][c] = 5;
						break;
					case '+':
						map[r][c] = 7;
						break;
					case 'M':
						M = new Node(r, c, 8);
						q.offer(M);
						map[r][c] = 8;
						visited[r][c] = true;
						break;
					case 'Z':
						Z = new Node(r, c, 9);
						map[r][c] = 9;
						break;
				}
			}
		}
		find();
//		answer();
		guessShape();
	}

	private static void answer() {
		boolean[] select = new boolean[8];
		Node top = new Node(target.x - 1, target.y, map[target.x - 1][target.y]);
		Node bottom = new Node(target.x + 1, target.y, map[target.x + 1][target.y]);
		Node left = new Node(target.x, target.y - 1, map[target.x][target.y - 1]);
		Node right = new Node(target.x, target.y + 1, map[target.x][target.y + 1]);
		
		// 위쪽 경우
		if(top.type == 0 || top.type == 2 || top.type == 3 || top.type == 6){
			select[2] = true;
			select[3] = true;
			select[5] = true;
			select[7] = true;
		}
		
		// 아래쪽 경우
		if(bottom.type == 0 || bottom.type == 1 || bottom.type == 4 || bottom.type == 6){
			select[1] = true;
			select[4] = true;
			select[5] = true;
			select[7] = true;
		}
		
		// 왼쪽 경우
		if(left.type == 0 || left.type == 3 || left.type == 4 || left.type == 5){
			select[3] = true;
			select[4] = true;
			select[6] = true;
			select[7] = true;
		}
		
		// 오른쪽 경우
		if(right.type == 0 || right.type == 1 || right.type == 2 || right.type == 5){
			select[1] = true;
			select[2] = true;
			select[6] = true;
			select[7] = true;
		}
		
		for(int i = 7 ; i > 0 ; --i){
			if(!select[i]) {
				System.out.println(target.x + " " + target.y + " " + types[i]);
				return;
			}
		}
	}

	private static void find() {
		int nr, nc;
		
		while(!q.isEmpty()){
			Node b = q.poll();
			
			for(int i = 0 ; i < 4 ; ++i){
				nr = b.x + dir[i][0];
				nc = b.y + dir[i][1];
				if(nr > R || nr < 1 || nc > C || nc < 1 || visited[nr][nc]) continue;
				if(b.type == 8 && map[nr][nc] != 0) {
					q.offer(new Node(nr, nc, map[nr][nc]));
					visited[nr][nc] = true;
					continue;
				}
				if(b.type == 0){
					target = new Node(b.x, b.y, 0);
					return;
				}
				switch(b.type){
				case 1:
					if(i == 1 || i == 3) {
						q.offer(new Node(nr, nc, map[nr][nc]));
						visited[nr][nc] = true;
					}
					break;
				case 2:
					if(i == 0 || i == 3) {
						q.offer(new Node(nr, nc, map[nr][nc]));
						visited[nr][nc] = true;
					}
					break;
				case 3:
					if(i == 0 || i == 2) {
						q.offer(new Node(nr, nc, map[nr][nc]));
						visited[nr][nc] = true;
					}
					break;
				case 4:
					if(i == 1 || i == 2) {
						q.offer(new Node(nr, nc, map[nr][nc]));
						visited[nr][nc] = true;
					}
					break;
				case 5:
					if(i == 0 || i == 1) {
						q.offer(new Node(nr, nc, map[nr][nc]));
						visited[nr][nc] = true;
					}
					break;
				case 6:
					if(i == 2 || i == 3) {
						q.offer(new Node(nr, nc, map[nr][nc]));
						visited[nr][nc] = true;
					}
					break;
				case 7:
					q.offer(new Node(nr, nc, map[nr][nc]));
					visited[nr][nc] = true;
					break;
				}
				
			}
		}
		
	}
	
	private static void guessShape() {
		
		Node n = new Node(target.x, target.y, 0);
//		System.out.println(n.x + "," + n.y + " = " + map[n.x][n.y]);
		boolean[] temp = new boolean[4];

		for (int i = 0; i < 4; i++) {
			int nx = n.x + dx[i];
			int ny = n.y + dy[i];

			if (nx >= 1 && ny >= 1 && nx <= R && ny <= C) {
//				System.out.println(map[nx][ny]);
				switch (map[nx][ny]) {
				case 6:
					if (i == LEFT || i == RIGHT) {
						temp[i] = true;
					}
					break;
				case 5:
					if (i == UP || i == DOWN) {
						temp[i] = true;
					}
					break;
				case 7:
					temp[i] = true;
					break;
				case 1:
					if (i == LEFT || i == UP) {
						temp[i] = true;
					}
					break;
				case 2:
					if (i == LEFT || i == DOWN) {
						temp[i] = true;
					}
					break;
				case 3:
					if (i == RIGHT || i == DOWN) {
						temp[i] = true;
					}
					break;
				case 4:
					if (i == RIGHT || i == UP) {
						temp[i] = true;
					}
					break;
				}
			}
		}
		
//		for(int i = 0; i < 4; i++) {
//			System.out.print(temp[i]?"1":"0");
//		}
//		System.out.println();

		System.out.print((n.x) + " " + (n.y) + " ");
		if (temp[UP] && temp[DOWN] && temp[LEFT] && temp[RIGHT]) {
			System.out.println('+');
		} else if (temp[UP] && temp[DOWN]) {
			System.out.println('|');
		} else if (temp[LEFT] && temp[RIGHT]) {
			System.out.println('-');
		} else if (temp[RIGHT] && temp[DOWN]) {
			System.out.println('1');
		} else if (temp[RIGHT] && temp[UP]) {
			System.out.println('2');
		} else if (temp[LEFT] && temp[UP]) {
			System.out.println('3');
		} else if (temp[LEFT] && temp[DOWN]) {
			System.out.println('4');
		}

	}



	private static void print() {
		
		for(int r = 1 ; r <= R ; ++r){
			for(int c = 1 ; c <= C ; ++c){
				System.out.print(types[map[r][c]]);
			}
			System.out.println();
		}
		System.out.println();
	}
}
