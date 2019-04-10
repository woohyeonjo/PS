package Algorithm.BOJ.go;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B13460 {
	static char[][] map;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static boolean[][][] visited;
	static Queue<Ball> q;
	static int N, M, cnt;
	static boolean isGoal;
	static class Ball{
		int br, bc, rr, rc;

		public Ball() {}
		
		public Ball(int br, int bc, int rr, int rc) {
			super();
			this.br = br;
			this.bc = bc;
			this.rr = rr;
			this.rc = rc;
		}
	}
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		map = new char[N][M];
		visited = new boolean[N][M][2];
		q = new LinkedList<Ball>();
		cnt = 1;
		
		Ball start = new Ball();
		
		for(int r = 0 ; r < N ; ++r) {
			char[] line = sc.next().toCharArray();
			for(int c = 0 ; c < M; ++c) {
				map[r][c] = line[c];
				if(line[c] == 'R') {
					start.rr = r;
					start.rc = c;
				}
				if(line[c] == 'B') {
					start.br = r;
					start.bc = c;
				}
			}
		}
		visited[start.rr][start.rc][0] = true;
		visited[start.br][start.bc][1] = true;
		q.offer(start);
		
		bfs();
		System.out.println(cnt);
		
	}
	
	private static void redFirst(int i, Ball nextBall, int rnr, int rnc, int bnr, int bnc) {
		boolean stuck = false;
		// »¡°­¸ÕÀú ±¼¸®±â
		
		while(!stuck) {
			rnr = nextBall.rr + dir[i][0];
			rnc = nextBall.rc + dir[i][1];
			if(map[rnr][rnc] == '#') {
				stuck = true;
			} else if(map[rnr][rnc] == 'O') {
				isGoal = true;
				return;
			} else if(map[rnr][rnc] == '.') {
				nextBall.rr = rnr;
				nextBall.rc = rnc;
			} 
		}
		stuck = false;
		while(!stuck) {
			bnr = nextBall.br + dir[i][0];
			bnc = nextBall.bc + dir[i][1];
			if(map[bnr][bnc] == '#' || map[bnr][bnc] == 'R') {
				stuck = true;
			} else if(map[bnr][bnc] == 'O') {
				cnt = -1;
				return;
			} else if(map[bnr][bnc] == '.') {
				nextBall.br = bnr;
				nextBall.bc = bnc;
			} 
		}
	}
	
	private static void blueFirst(int i, Ball nextBall, int rnr, int rnc, int bnr, int bnc) {
		boolean stuck = false;
		// ÆÄ¶û¸ÕÀú ±¼¸®±â
		
		while(!stuck) {
			bnr = nextBall.br + dir[i][0];
			bnc = nextBall.bc + dir[i][1];
			if(map[bnr][bnc] == '#') {
				stuck = true;
			} else if(map[bnr][bnc] == 'O') {
				cnt = -1;
				return;
			} else if(map[bnr][bnc] == '.') {
				nextBall.br = bnr;
				nextBall.bc = bnc;
			} 
		}
		stuck = false;
		while(!stuck) {
			rnr = nextBall.rr + dir[i][0];
			rnc = nextBall.rc + dir[i][1];
			if(map[rnr][rnc] == '#' || map[rnr][rnc] == 'B') {
				stuck = true;
			} else if(map[rnr][rnc] == 'O') {
				isGoal = true;
				return;
			} else if(map[rnr][rnc] == '.') {
				nextBall.rr = rnr;
				nextBall.rc = rnc;
			} 
		}
	}
	
	
	private static void bfs() {
		
		int rnr = 0, rnc = 0, bnr = 0, bnc = 0;
		while(!q.isEmpty()) {
			Ball ball = q.poll();
			for(int i = 0 ; i < 4 ; ++i) {
				Ball nextBall = new Ball(ball.br, ball.bc, ball.rr, ball.rc);
				//À§
				if(i == 0) {
					if(ball.rr > ball.br) {
						blueFirst(i, nextBall, rnr, rnc, bnr, bnc);
					} else {
						redFirst(i, nextBall, rnr, rnc, bnr, bnc);
					}
					if(isGoal) return;
					if(ball.rr != nextBall.rr || ball.rc != nextBall.rc || ball.br != nextBall.br || ball.bc != nextBall.bc) {
						q.offer(nextBall);
						visited[start.rr][start.rc][0] = true;
						visited[start.br][start.bc][1] = true;
					}
				}
				
				// ¾Æ·¡
				if(i == 1) {
					if(ball.rr > ball.br) {
						blueFirst(i, nextBall, rnr, rnc, bnr, bnc);
					} else {
						redFirst(i, nextBall, rnr, rnc, bnr, bnc);
					}
					if(isGoal) return;
					if(ball.rr != nextBall.rr || ball.rc != nextBall.rc || ball.br != nextBall.br || ball.bc != nextBall.bc) {
						q.offer(nextBall);
					}
				}
				
				// ¿Þ
				if(i == 2) {
					if(ball.rc > ball.bc) {
						blueFirst(i, nextBall, rnr, rnc, bnr, bnc);
					} else {
						redFirst(i, nextBall, rnr, rnc, bnr, bnc);
					}
					if(isGoal) return;
					if(ball.rr != nextBall.rr || ball.rc != nextBall.rc || ball.br != nextBall.br || ball.bc != nextBall.bc) {
						q.offer(nextBall);
					}
				}
				
				// ¿À¸¥
				if(i == 3) {
					if(ball.rc < ball.bc) {
						blueFirst(i, nextBall, rnr, rnc, bnr, bnc);
					} else {
						redFirst(i, nextBall, rnr, rnc, bnr, bnc);
					}
					if(isGoal) return;
					if(ball.rr != nextBall.rr || ball.rc != nextBall.rc || ball.br != nextBall.br || ball.bc != nextBall.bc) {
						q.offer(nextBall);
					}
				}
			}
			cnt++;
			if(cnt >= 10) {
				cnt = -1;
				return;
			}
		}
	}
}
