package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B17135_캐슬디펜스_3 {
	
	static class Enemy implements Comparable<Enemy> {
		int r, c;
		int range;
		
		public Enemy(int r, int c, int range) {
			this.r = r;
			this.c = c;
			this.range = range;
		}

		@Override
		public int compareTo(Enemy o) {
			if(this.range == o.range) return this.c - o.c; // c에 대해서 오름차순 
			else return this.range - o.range; // range에 대해서 오름차순 
		}
	}
	
	static PriorityQueue<Enemy> pq;
	static int[][] map;
	static int[] archors;
	static int N, M, D;
	static int ans, kill;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		D = stoi(st.nextToken());
		
		map = new int[N][M];
		archors = new int[3];
		pq = new PriorityQueue<>();
		ans = Integer.MIN_VALUE;
		
		for(int r = 0 ; r < N ; ++r) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0 ; c < M ; ++c) {
				map[r][c] = stoi(st.nextToken());
			}
		}
		
		setArchors(0, 0);
		
		System.out.println(ans);
	}
	
	// 조합을 통해 궁수를 위치시킨다. 
	private static void setArchors(int start, int idx) {
		if(idx == 3) {
			kill = 0;
			// 조합의 경우 마다 시뮬레이션을 수행해야 하므로 복사된 맵을 이용한다.
			game(copyMap());
			
//			for(int i = 0 ; i < 3 ; ++i) {
//				System.out.print(archors[i] + " ");
//			}
//			System.out.println();
			return;
		}
		
		for(int c = start ; c < M ; ++c) {
			archors[idx] = c;
			setArchors(c + 1, idx + 1);
		}
	}

	private static void game(int[][] copy) {
		do {
			for(int a = 0 ; a < 3 ; ++a) {
				find(a, copy);
				shot(copy);
			}
		} while(cleanAndforward(copy));
		
		ans = kill > ans ? kill : ans;
	}

	// 화살에 맞은 적을 지우고 살아있는 적을 앞으로 한 칸씩 이동시킨다.
	private static boolean cleanAndforward(int[][] copy) {
		boolean flag = false;
		
		for(int r = N - 1 ; r >= 0 ; --r) {
			for(int c = 0 ; c < M ; ++c) {
				if(copy[r][c] > 1) {
					kill++;
					copy[r][c] = 0;
				} else if(copy[r][c] == 1) {
					if(r + 1 < N) {
						copy[r + 1][c] = 1;
						flag = true;
					}
					copy[r][c] = 0;
				}
			}
		}
		// 적을 이동시키지 않았다는 것은 맵 위에 적이 없다는 
		return flag;
	}

	// 우선순위 큐에서 나온 첫 번째 객체는 가장 가까운 거리에 가장 왼쪽에 위치한 적이다.
	private static void shot(int[][] copy) {
		if(pq.isEmpty()) return;
		
		Enemy e = pq.poll();
		
		copy[e.r][e.c]++;
		pq.clear();
	}

	// 사정거리 안에 있는 모든 적을 우선순위 큐에 넣는다.
	private static void find(int spot, int[][] copy) {
		for(int r = N - 1 ; r >= 0 ; --r) {
			for(int c = 0 ; c < M ; ++c) {
				if(copy[r][c] > 0) {
					int range = calRange(N, archors[spot], r, c);
					
					if(range <= D) {
						pq.offer(new Enemy(r, c, range));
					}
				}
			}
		}
	}

	private static int calRange(int r1, int c1, int r2, int c2) {
		return Math.abs(r1 - r2) + Math.abs(c1 - c2);
	}
	
	private static int[][] copyMap(){
		int[][] arr = new int[N][M];
		
		for(int r = 0 ; r < N ; ++r) {
			for(int c = 0 ; c < M ; ++c) {
				arr[r][c] = map[r][c];
			}
		}
		
		return arr;
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}


