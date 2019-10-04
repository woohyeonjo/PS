package BOJ;


import java.util.Scanner;

public class B15685_드래곤커브 {
	
	static int[][] map;
	static int[][] dir = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
	static int[] dragon;
	static int N, ans;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		map = new int[101][101];
		ans = 0;
		
		for(int i = 0 ; i < N ; ++i){
			go(sc.nextInt(),
			   sc.nextInt(),
			   sc.nextInt(),
			   sc.nextInt());
		}
		
		for(int r = 0 ; r < 100 ; ++r){
			for(int c = 0 ; c < 100 ; ++c){
				if(map[r][c] > 0 &&
				   map[r + 1][c] > 0 &&
				   map[r][c + 1] > 0 &&
				   map[r + 1][c + 1] > 0){
					ans++;
				}
			}
		}
		System.out.println(ans);
	}

	private static void go(int r, int c, int direction, int generation) {
		makeDragon(direction, generation);
		drawDragon(c, r);
	}

	private static void drawDragon(int r, int c) {
		
		map[r][c]++;
		int nr = r, nc = c;
		for(int i = 1 ; i < dragon.length ; ++i) {
			nr += dir[dragon[i]][0];
			nc += dir[dragon[i]][1];
			map[nr][nc]++;
		}
	}

	private static void makeDragon(int direction, int generation) {
		dragon = new int[(int) (Math.pow(2, generation) + 1)];
		int index = 1;
		int index_reverse = 1;
		
		dragon[1] = direction;
		
		for(int g = 1 ; g <= generation ; ++g){
			for(int i = index_reverse ; i > 0 ; --i){
				dragon[++index] = (dragon[i] + 1) % 4;
			}
			index_reverse = index;
		}
	}
}
