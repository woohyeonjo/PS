package Algorithm.BOJ.go;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B3190 {
	static int[][] map;
	static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static LinkedList<Cell> snake;
	static Queue<Cmd> q;
	static int N, K, L, ans;
	static class Cell{
		int r, c;

		public Cell(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	
	static class Cmd{
		int time;
		char dir;
		
		public Cmd(int time, char dir) {
			super();
			this.time = time;
			this.dir = dir;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		init(sc);
		move();
		System.out.println(ans);
	}
	
	private static void move() {
		int arrow = 1, time = 0;
		int nr, nc;
		while(!q.isEmpty()) {
			Cmd cmd = q.poll();
			
			while(time < cmd.time) {
				time++;
				nr = snake.peekFirst().r + dir[arrow][0];
				nc = snake.peekFirst().c + dir[arrow][1];
				
				// 벽에 부딫혔을 때, 뱀 몸을 만났을 때
				if(nr >= N || nr < 0 || nc >= N || nc < 0 || map[nr][nc] == 2) {
					ans = time;
					return;
				}
				
				if(map[nr][nc] == 1) {
					// 사과를 만났을 때
					snake.addFirst(new Cell(nr, nc));
					drawSnake();
				} else if (map[nr][nc] == 0) {
					// 빈칸을 만났을 때
					snake.addFirst(new Cell(nr, nc));
					map[snake.peekLast().r][snake.peekLast().c] = 0;
					snake.removeLast();
					drawSnake();
				}
			}
			// 방향전환
			if(cmd.dir == 'L') arrow = arrow - 1 >= 0 ? arrow - 1 : arrow + 3; 
			else if(cmd.dir == 'D') arrow = (arrow + 1) % 4;
		}
		ans = time;
	}


	private static void drawSnake() {
		for(Cell cell : snake) {
			map[cell.r][cell.c] = 2; 
		}
	}

	private static void init(Scanner sc) {
		N = sc.nextInt();
		K = sc.nextInt();
		
		map = new int[N][N];
		snake = new LinkedList<Cell>();
		q = new LinkedList<Cmd>();
		
		for(int k = 0 ; k < K ; ++k) {
			map[sc.nextInt() - 1][sc.nextInt() - 1] = 1;
		}
		
		L = sc.nextInt();
		for(int l = 0 ; l < L ; ++l) {
			q.offer(new Cmd(sc.nextInt(), sc.next().charAt(0)));
		}
		q.offer(new Cmd(10000, 'E'));
		snake.add(new Cell(0, 0));
		drawSnake();
	}
	
}
