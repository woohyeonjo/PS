package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B15653_구슬탈출4 {

	static class Node {
		int r, c, time;
		char type;

		Node(int r, int c, char type, int time) {
			this.r = r;
			this.c = c;
			this.type = type;
			this.time = time;
		}
	}

	static Queue<Node> q;
	static boolean[][][][] visited;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static char[][] map;
	static int blueR, blueC, redR, redC;
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	
		q = new LinkedList<>();
		visited = new boolean[N][M][N][M];
		map = new char[N][M];
		
		for(int r = 0 ; r < N ; ++r) {
			char[] line = br.readLine().toCharArray();
			for(int c = 0 ; c < M ; ++c) {
				if(line[c] == 'R') {
					map[r][c] = '.';
					redR = r;
					redC= c;
					q.offer(new Node(r, c, line[c], 0));
				} else if(line[c] == 'B') {
					map[r][c] = '.';
					blueR = r;
					blueC = c;
					q.offer(new Node(r, c, line[c], 0));
				}else {
					map[r][c] = line[c];
				}
			}
		}

		visited[redR][redC][blueR][blueC] = true;
		
		System.out.println(bfs());
	}

	private static int bfs() {
		while(!q.isEmpty()) {
			Node blue, red;
			if(q.peek().type == 'B') {
				blue = q.poll();
				red = q.poll();
			} else {
				red = q.poll();
				blue = q.poll();
			}
			
			boolean blueInHole, redInHole, afterRed;
			int bcr, bcc, rcr, rcc;
			int bnr, bnc, rnr, rnc;
			for(int d = 0 ; d < 4 ; ++d) {
				blueInHole = redInHole = afterRed = false;
				bcr = blue.r;
				bcc = blue.c;
				rcr = red.r;
				rcc = red.c;
				
				// 파랑 구슬 이동 
				while(true) {
					bnr = bcr + dir[d][0];
					bnc = bcc + dir[d][1];
					if(bnr == rcr && bnc == rcc) afterRed = true;
					if(map[bnr][bnc] == '#') break;
					if(map[bnr][bnc] == 'O') {
						blueInHole = true;
						break;
					}
					bcr = bnr;
					bcc = bnc;
				}
				
				// 빨강 구슬 이동 
				while(true) {
					rnr = rcr + dir[d][0];
					rnc = rcc + dir[d][1];
					if(map[rnr][rnc] == '#') break;
					if(map[rnr][rnc] == 'O') {
						redInHole = true;
						break;
					}
					rcr = rnr;
					rcc = rnc;
				}
				
				// 파란구슬이 들어갔을 때 
				if(blueInHole) continue;
				// 빨간구슬이 들어갔을 때 
				if(redInHole) return red.time + 1;
				
				// 구슬이 겹쳤을 때 
				if(bcr == rcr && bcc == rcc) {
					// 빨간구슬 다음에 파란구슬이 올 때 
					if(afterRed) {
						bcr -= dir[d][0];
						bcc -= dir[d][1];
					// 파란구슬 다음에 빨간구슬이 올 때 
					} else {
						rcr -= dir[d][0];
						rcc -= dir[d][1];
					}
				}
				
				if(visited[rcr][rcc][bcr][bcc]) continue;
				visited[rcr][rcc][bcr][bcc] = true;
				
				q.offer(new Node(bcr, bcc, 'B', blue.time + 1));
				q.offer(new Node(rcr, rcc, 'R', red.time + 1));
			}
		}
		
		return -1;
	}
}
