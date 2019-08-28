package BOJ.go;

import java.util.Scanner;

public class B15683_감시 {
	
	static class Cam {
		int r, c;
		int type;
		
	}
	
	static int[][] map;
	static int[][] copy;
	static Cam[] camList;
	static int[] selected;
	static int N, M, camCnt, ans, current;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		map = new int[N][M];
		camCnt = 0;
		ans = 0;
		
		int input = 0;
		for(int r = 0 ; r < N ; ++r){
			for(int c = 0 ; c < M ; ++c){
				input = sc.nextInt();
				map[r][c] = input;
				if(input > 0) {
					camList[camCnt++] = new Cam(r, c, );
				}
			}
		}
		
		camList = new Cam[camCnt];
		selected = new int[camCnt];
		
		go(0);
		System.out.println(ans);
	}

	private static void go(int index) {
		if(index == camCnt){
			copy = copyMap();
			current = watch();
			ans = ans > current ? current : ans;
			return;
		}
		
		for(int i = 0 ; i < 4 ; ++i){
			selected[index] = i;
			go(index + 1);
		}
		
	}

	private static int watch() {
		
	}

	private static int[][] copyMap() {
		int[][] result = new int[N][M];
		
		for(int r = 0 ; r < N ; ++r){
			for(int c = 0 ; c < M ; ++c){
				result[r][c] = map[r][c];
			}
		}
		return result;
	}
}
