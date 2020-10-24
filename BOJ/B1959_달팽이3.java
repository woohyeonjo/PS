package BOJ;


import java.util.Scanner;

public class B1959_달팽이3 {
	
	static int[][] map;
	static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	static int N, M;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		map = new int[N + 1][M + 1];
		
		int nr = 0;
		int nc = 0;
		int r = 1;
		int c = 1;
		map[r][c] = 1;
		int cnt = 1;
		int d = 0;
		boolean end = false;
		while(true){
			nr = r + dir[d][0];
			nc = c + dir[d][1];
			
			if(nr > N || nr < 1 || nc > M || nc < 1 || map[nr][nc] == 1){
				if(end){
					System.out.println(cnt - 2);
					return;
				}
				d = (d + 1) % 4;
				cnt++;
				end = true;
				continue;
			}
			end = false;
			
			map[nr][nc] = 1;
			r = nr;
			c = nc;
		}
	}
}
