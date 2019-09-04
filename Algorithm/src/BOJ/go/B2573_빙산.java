package BOJ.go;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B2573_빙산 {
	
	static class Cell {
		int r, c;
		
		public Cell(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	
	static Queue<Cell> q;
	static int[][] map;
	static int[][] temp;
	static int[][] map2;
	static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static boolean[][] visited;
	static int N, M, ans;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		map = new int[N][M];
		q = new LinkedList<>();
		ans = 0;
		
		for(int r = 0; r < N ; ++r) {
			for(int c = 0 ; c < M ; ++c) {
				map[r][c] = sc.nextInt();
			}
		}
		
		int pivot = 0;
		int time = 0;
		int current = 0;
		while(true) {
			time++;
			melt(pivot);
			if(pivot % 2 == 0) print(temp);
			else print(map);
			current = check(pivot);
			if(current > 1) {
				ans = time;
				break;
			} else if(current == 0) break;
			pivot++;
		}
		
		System.out.println(ans);
	}

	private static int check(int pivot) {
		int index = 0;
		map2 = new int[N][M];
		visited = new boolean[N][M];
		
		if(pivot % 2 == 0) {
			for(int r = 0 ; r < N ; ++r) {
				for(int c = 0 ; c < M ; ++c) {
					if(temp[r][c] > 0 && map2[r][c] == 0) {
						++index;
						q.offer(new Cell(r, c));
						map2[r][c] = index;
						visited[r][c] = true;
						bfs(temp, index);
					}
				}
			}
		} else {
			for(int r = 0 ; r < N ; ++r) {
				for(int c = 0 ; c < M ; ++c) {
					if(map[r][c] > 0 && map2[r][c] == 0) {
						++index;
						q.offer(new Cell(r, c));
						map2[r][c] = index;
						visited[r][c] = true;
						bfs(map, index);
					}
				}
			}
		}
		print(map2);
		
		return index;
	}

	private static void bfs(int[][] arr, int index) {
		int nr, nc;
		
		while(!q.isEmpty()) {
			Cell cell = q.poll();
			
			for(int i = 0 ; i < 4 ; ++i) {
				nr = cell.r + dir[i][0];
				nc = cell.c + dir[i][1];
				if(visited[nr][nc]) continue;
				if(arr[nr][nc] > 0) {
					map2[nr][nc] = index;
					visited[nr][nc] = true;
					q.offer(new Cell(nr, nc));
				}
			}
		}
		
	}

	private static void melt(int pivot) {
		int nr, nc;
		int cnt;
		
		if(pivot % 2 == 0) {
			temp = new int[N][M];
			for(int r = 0 ; r < N ; ++r) {
				for(int c = 0 ; c < M ; ++c) {
					if(map[r][c] > 0) {
						cnt = 0;
						for(int i = 0 ; i < 4 ; ++i) {
							nr = r + dir[i][0];
							nc = c + dir[i][1];
							if(map[nr][nc] == 0) cnt++;
						}
						temp[r][c] = map[r][c] - cnt;
						if(temp[r][c] < 0) temp[r][c] = 0;
					}
				}
			}
		} else {
			map = new int[N][M];
			for(int r = 0 ; r < N ; ++r) {
				for(int c = 0 ; c < M ; ++c) {
					if(temp[r][c] > 0) {
						cnt = 0;
						for(int i = 0 ; i < 4 ; ++i) {
							nr = r + dir[i][0];
							nc = c + dir[i][1];
							if(temp[nr][nc] == 0) cnt++;
						}
						map[r][c] = temp[r][c] - cnt;
						if(map[r][c] < 0) map[r][c] = 0;
					}
				}
			}
		}
		
		
	}

	private static void print(int[][] arr) {
		for(int r = 0 ; r < N ; ++r) {
			for(int c = 0 ; c < M ; ++c) {
				System.out.print(arr[r][c] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
}
