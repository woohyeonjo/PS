package BOJ;


import java.util.Scanner;

public class B17135_캐슬디펜스_2 {
	
	static int[][] map;
	static int[][] map_copied;
	static int[] archor;
	static int N, M, D, ENEMY;
	static int ans, current, enemy;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		D = sc.nextInt();
		
		ans = 0;
		ENEMY = 0;
		map = new int[N][M];
		archor = new int[3];
		
		for(int r = 0 ; r < N ; ++r) {
			for(int c = 0 ; c < M ; ++c) {
				map[r][c] = sc.nextInt();
				if(map[r][c] == 1) ENEMY++;
			}
		}
		
		for(int first = 0 ; first < M ; ++first) {
			for(int second = first + 1 ; second < M ; ++second) {
				for(int third = second + 1 ; third < M ; ++third) {
					copyMap();
					enemy = ENEMY;
					archor[0] = first;
					archor[1] = second;
					archor[2] = third;
					current = go();
					ans = current > ans ? current : ans;
				}
			}
		}
		
		System.out.println(ans);
		
	}

	private static int go() {
		int kill = 0;
		
		while(enemy > 0) {
			shot();
			kill += clean();
			forward();
		}
		
		return kill;
	}

	private static void forward() {
		for(int r = N - 1 ; r >= 0 ; --r) {
			for(int c = 0 ; c < M ; ++c) {
				if( r == (N - 1) && map_copied[r][c] == 1) {
					map_copied[r][c] = 0;
					enemy--;
					continue;
				}
				if( map_copied[r][c] == 1) {
					map_copied[r][c] = 0;
					map_copied[r + 1][c] = 1;
				}
			}
		}		
	}

	private static int clean() {
		int kill = 0;
		
		for(int r = 0 ; r < N ; ++r) {
			for(int c = 0 ; c < M ; ++c) {
				if(map_copied[r][c] > 1) {
					map_copied[r][c] = 0;
					kill++;
					enemy--;
				}
			}
		}
		return kill;
	}

	private static void shot() {
		int min_distance;
		int target_r;
		int target_c;
	OUTER:	for(int a = 0 ; a < 3 ; ++a) {
		min_distance = Integer.MAX_VALUE;
		target_r = 0;
		target_c = 0;
			for(int r = N - 1 ; r >= 0 ; --r) {
				for(int c = 0 ; c < M ; ++c) {
					int distance = Math.abs(N - r) + Math.abs(archor[a] - c);
					
					if( distance <= D && map_copied[r][c] > 0 ) {
						if(distance < min_distance) {
							min_distance = distance;
							target_r = r;
							target_c = c;
						} else if (distance == min_distance) {
							if(target_c > c) {
								target_r = r;
								target_c = c;
							}
						}
					}
				}
			}
			if(min_distance != Integer.MAX_VALUE) map_copied[target_r][target_c]++;
		}
	}

	private static void copyMap() {
		map_copied = new int[N][M];
		
		for(int r = 0 ; r < N ; ++r) {
			for(int c = 0 ; c < M ; ++c) {
				map_copied[r][c] = map[r][c];
			}
		}
	}
	
}
