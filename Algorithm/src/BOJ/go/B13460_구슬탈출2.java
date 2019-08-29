package BOJ.go;

import java.util.Scanner;

public class B13460_구슬탈출2 {
	static char[][] map;
//	static boolean[][][] visited;
	static int N, M, ans;
	static boolean fail, success;
	static Ball pBall;
	static class Ball {
		int rr, rc, br, bc;

		public Ball() {}
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		map = new char[N][M];
//		visited = new boolean[N][M][2];
		pBall = new Ball();
		ans = 11;
		
		for(int r = 0 ; r < N ; ++r) {
			char[] line = sc.next().toCharArray();
			for(int c = 0 ; c < M; ++c) {
				map[r][c] = line[c];
				if(line[c] == 'R') {
					pBall.rr = r;
					pBall.rc = c;
				}
				if(line[c] == 'B') {
					pBall.br = r;
					pBall.bc = c;
				}
			}
		}
//		visited[pBall.rr][pBall.rc][0] = true;
//		visited[pBall.br][pBall.bc][1] = true;
		dfs(map, 1, pBall.rr, pBall.rc, pBall.br, pBall.bc);
		if(ans > 10) ans = -1;
		System.out.println(ans);
	}

	private static void dfs(char[][] cmap, int cnt, int beforeRR, int beforeRC, int beforeBR, int beforeBC) {
		
		if(cnt == 10) {
			return;
		}
		
		for(int i = 0 ; i < 4 ; ++i) {
			char[][] nmap = copyMap(cmap);
			moveBall(nmap, i, beforeRR, beforeRC, beforeBR, beforeBC);
			if(success) {
				success = false;
				if(fail) {
					fail = false;
					return;
				}
				
				pringMap(nmap);
				pBall.rr = beforeRR;
				pBall.rc = beforeRC;
				pBall.br = beforeBR;
				pBall.bc = beforeBC;
				nmap[pBall.rr][pBall.rc] = 'O';
				ans = ans > cnt ? cnt : ans;
				return;
			}
			if(fail) {
				fail = false;
				continue;
			}
//			if(!visited[pBall.rr][pBall.rc][0]) {
				if(beforeRR == pBall.rr && beforeRC == pBall.rc && beforeBR == pBall.br && beforeBC == pBall.bc) continue;
//				visited[pBall.rr][pBall.rc][0] = true;
//				visited[pBall.br][pBall.bc][1] = true;
				
				dfs(nmap, cnt + 1, pBall.rr, pBall.rc, pBall.br, pBall.bc);
//				visited[pBall.rr][pBall.rc][0] = false;
//				visited[pBall.br][pBall.bc][1] = false;
//			}
		}
		
	}

	private static void moveBall(char[][] cmap, int i, int beforeRR, int beforeRC, int beforeBR, int beforeBC) {
		int rr, rc, nrr = beforeRR, nrc = beforeRC;
		int br, bc, nbr = beforeBR, nbc = beforeBC;
		
		switch(i) {
		case 0:
			if(beforeRR > beforeBR) {
				// 파 빨
				while(true) {
					br = nbr;
					nbr -= 1;
					
					if(cmap[nbr][nbc] == '.') {
						continue;
					} else if (cmap[nbr][nbc] == '#') {
						cmap[beforeBR][beforeBC] = '.';
						cmap[br][nbc] = 'B';
						pBall.br = br;
						pBall.bc = beforeBC;
						break;
					} else if (cmap[nbr][nbc] == 'O') {
						pBall.br = nbr;
						pBall.bc = nbc;
						fail = true;
						return;
					}
				}
				while(true) {
					rr = nrr;
					nrr -= 1;
					
					if(cmap[nrr][nrc] == '.') {
						continue;
					} else if (cmap[nrr][nrc] == '#' || cmap[nrr][nrc] == 'B') {
						cmap[beforeRR][beforeRC] = '.';
						cmap[rr][nrc] = 'R';
						pBall.rr = rr;
						pBall.rc = beforeRC;
						break;
					} else if (cmap[nrr][nrc] == 'O') {
						pBall.rr = nrr;
						pBall.rc = nrc;
						success = true;
						return;
					}
				}
				
			} else {
				// 빨 파
				while(true) {
					rr = nrr;
					nrr -= 1;
					
					if(cmap[nrr][nrc] == '.') {
						continue;
					} else if (cmap[nrr][nrc] == '#') {
						cmap[beforeRR][beforeRC] = '.';
						cmap[rr][nrc] = 'R';
						pBall.rr = rr;
						pBall.rc = beforeRC;
						break;
					} else if (cmap[nrr][nrc] == 'O') {
						cmap[beforeRR][beforeRC] = '.';
						pBall.rr = nrr;
						pBall.rc = nrc;
						success = true;
					}
				}
				while(true) {
					br = nbr;
					nbr -= 1;
					
					if(cmap[nbr][nbc] == '.') {
						continue;
					} else if (cmap[nbr][nbc] == '#' || cmap[nbr][nbc] == 'R') {
						cmap[beforeBR][beforeBC] = '.';
						cmap[br][nbc] = 'B';
						pBall.br = br;
						pBall.bc = beforeBC;
						break;
					} else if (cmap[nbr][nbc] == 'O') {
						pBall.br = nbr;
						pBall.bc = nbc;
						fail = true;
						return;
					}
				}
			}
			break;
		case 1:
			if(beforeRR < beforeBR) {
				// 파 빨
				while(true) {
					br = nbr;
					nbr += 1;
					
					if(cmap[nbr][nbc] == '.') {
						continue;
					} else if (cmap[nbr][nbc] == '#') {
						cmap[beforeBR][beforeBC] = '.';
						cmap[br][nbc] = 'B';
						pBall.br = br;
						pBall.bc = beforeBC;
						break;
					} else if (cmap[nbr][nbc] == 'O') {
						pBall.br = nbr;
						pBall.bc = nbc;
						fail = true;
						return;
					}
				}
				while(true) {
					rr = nrr;
					nrr += 1;
					
					if(cmap[nrr][nrc] == '.') {
						continue;
					} else if (cmap[nrr][nrc] == '#' || cmap[nrr][nrc] == 'B') {
						cmap[beforeRR][beforeRC] = '.';
						cmap[rr][nrc] = 'R';
						pBall.rr = rr;
						pBall.rc = beforeRC;
						break;
					} else if (cmap[nrr][nrc] == 'O') {
						pBall.rr = nrr;
						pBall.rc = nrc;
						success = true;
						return;
					}
				}
				
			} else {
				// 빨 파
				while(true) {
					rr = nrr;
					nrr += 1;
					
					if(cmap[nrr][nrc] == '.') {
						continue;
					} else if (cmap[nrr][nrc] == '#') {
						cmap[beforeRR][beforeRC] = '.';
						cmap[rr][nrc] = 'R';
						pBall.rr = rr;
						pBall.rc = beforeRC;
						break;
					} else if (cmap[nrr][nrc] == 'O') {
						cmap[beforeRR][beforeRC] = '.';
						pBall.rr = nrr;
						pBall.rc = nrc;
						success = true;
					}
				}
				while(true) {
					br = nbr;
					nbr += 1;
					
					if(cmap[nbr][nbc] == '.') {
						continue;
					} else if (cmap[nbr][nbc] == '#' || cmap[nbr][nbc] == 'R') {
						cmap[beforeBR][beforeBC] = '.';
						cmap[br][nbc] = 'B';
						pBall.br = br;
						pBall.bc = beforeBC;
						break;
					} else if (cmap[nbr][nbc] == 'O') {
						pBall.br = nbr;
						pBall.bc = nbc;
						fail = true;
						return;
					}
				}
			}
			break;
		case 2:
			if(beforeRC > beforeBC) {
				// 파 빨
				while(true) {
					bc = nbc;
					nbc -= 1;
					
					if(cmap[nbr][nbc] == '.') {
						continue;
					} else if (cmap[nbr][nbc] == '#') {
						cmap[beforeBR][beforeBC] = '.';
						cmap[nbr][bc] = 'B';
						pBall.br = beforeBR;
						pBall.bc = bc;
						break;
					} else if (cmap[nbr][nbc] == 'O') {
						pBall.br = nbr;
						pBall.bc = nbc;
						fail = true;
						return;
					}
				}
				while(true) {
					rc = nrc;
					nrc -= 1;
					
					if(cmap[nrr][nrc] == '.') {
						continue;
					} else if (cmap[nrr][nrc] == '#' || cmap[nrr][nrc] == 'B') {
						cmap[beforeRR][beforeRC] = '.';
						cmap[nrr][rc] = 'R';
						pBall.rr = beforeRR;
						pBall.rc = rc;
						break;
					} else if (cmap[nrr][nrc] == 'O') {
						pBall.rr = nrr;
						pBall.rc = nrc;
						success = true;
						return;
					}
				}
				
			} else {
				// 빨 파
				while(true) {
					rc = nrc;
					nrc -= 1;
					
					if(cmap[nrr][nrc] == '.') {
						continue;
					} else if (cmap[nrr][nrc] == '#') {
						cmap[beforeRR][beforeRC] = '.';
						cmap[nrr][rc] = 'R';
						pBall.rr = beforeRR;
						pBall.rc = rc;
						break;
					} else if (cmap[nrr][nrc] == 'O') {
						cmap[beforeRR][beforeRC] = '.';
						pBall.rr = nrr;
						pBall.rc = nrc;
						success = true;
					}
				}
				while(true) {
					bc = nbc;
					nbc -= 1;
					
					if(cmap[nbr][nbc] == '.') {
						continue;
					} else if (cmap[nbr][nbc] == '#' || cmap[nbr][nbc] == 'R') {
						cmap[beforeBR][beforeBC] = '.';
						cmap[nbr][bc] = 'B';
						pBall.br = beforeBR;
						pBall.bc = bc;
						break;
					} else if (cmap[nbr][nbc] == 'O') {
						pBall.br = nbr;
						pBall.bc = nbc;
						fail = true;
						return;
					}
				}
			}
			break;
		case 3:
			if(beforeRC < beforeBC) {
				// 파 빨
				while(true) {
					bc = nbc;
					nbc += 1;
					
					if(cmap[nbr][nbc] == '.') {
						continue;
					} else if (cmap[nbr][nbc] == '#') {
						cmap[beforeBR][beforeBC] = '.';
						cmap[nbr][bc] = 'B';
						pBall.br = beforeBR;
						pBall.bc = bc;
						break;
					} else if (cmap[nbr][nbc] == 'O') {
						pBall.br = nbr;
						pBall.bc = nbc;
						fail = true;
						return;
					}
				}
				while(true) {
					rc = nrc;
					nrc += 1;
					
					if(cmap[nrr][nrc] == '.') {
						continue;
					} else if (cmap[nrr][nrc] == '#' || cmap[nrr][nrc] == 'B') {
						cmap[beforeRR][beforeRC] = '.';
						cmap[nrr][rc] = 'R';
						pBall.rr = beforeRR;
						pBall.rc = rc;
						break;
					} else if (cmap[nrr][nrc] == 'O') {
						pBall.rr = nrr;
						pBall.rc = nrc;
						success = true;
						return;
					}
				}
				
			} else {
				// 빨 파
				while(true) {
					rc = nrc;
					nrc += 1;
					
					if(cmap[nrr][nrc] == '.') {
						continue;
					} else if (cmap[nrr][nrc] == '#') {
						cmap[beforeRR][beforeRC] = '.';
						cmap[nrr][rc] = 'R';
						pBall.rr = beforeRR;
						pBall.rc = rc;
						break;
					} else if (cmap[nrr][nrc] == 'O') {
						cmap[beforeRR][beforeRC] = '.';
						pBall.rr = nrr;
						pBall.rc = nrc;
						success = true;
					}
				}
				while(true) {
					bc = nbc;
					nbc += 1;
					
					if(cmap[nbr][nbc] == '.') {
						continue;
					} else if (cmap[nbr][nbc] == '#' || cmap[nbr][nbc] == 'R') {
						cmap[beforeBR][beforeBC] = '.';
						cmap[nbr][bc] = 'B';
						pBall.br = beforeBR;
						pBall.bc = bc;
						break;
					} else if (cmap[nbr][nbc] == 'O') {
						pBall.br = nbr;
						pBall.bc = nbc;
						fail = true;
						return;
					}
				}
			}
			break;
		}
		
		
	}

	private static char[][] copyMap(char[][] cmap) {
		char[][] result = new char[N][M];
		
		for(int i = 0 ; i < N ; ++i) {
			result[i] = cmap[i].clone();
		}
		return result;
	}
	
	private static void pringMap(char[][] cmap) {
		for(int r = 0 ; r < N ; ++r) {
			System.out.println();
			for(int c = 0 ; c < M; ++c) {
				System.out.print(" " + cmap[r][c]);
			}
		}
		System.out.println();
	}
}
