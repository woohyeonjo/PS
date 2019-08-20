package BOJ.go;

import java.util.Scanner;

public class B17135 {
	public static class Cell {
		int r, c;
		int type;
		
		public Cell(int r, int c, int type){
			this.r = r;
			this.c = c;
			this.type = type;
		}
	}
	
	static Cell[][] map, tempMap;
	static Cell[] archer;
	static int N, M, D, ENEMY, enemy, kill, ans;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		D = sc.nextInt();
		
		map = new Cell[N][M];
		archer = new Cell[3];
		
		for(int r = 0 ; r < N ; ++r){
			for(int c = 0 ; c < M ; ++c){
				map[r][c] = new Cell(r, c, sc.nextInt());
				if(map[r][c].type == 1) ENEMY++;
			}
		}
		
		for(int first = 0 ; first < M ; ++first){
			archer[0] = new Cell(N, first, 1);
			for(int second = first + 1 ; second < M ; ++second){
				archer[1] = new Cell(N, second, 1);
				for(int third = second + 1 ; third < M ; ++third){
					archer[2] = new Cell(N, third, 1);
					go();
				}
			}
		}
		System.out.println(ans);
	}

	private static void go(){
		tempMap = copy();
		kill = 0;
		enemy = ENEMY;
		
		while(true){
			if(enemy == 0) break;
			fire();
			forward();
		}
		ans = (kill > ans ? kill : ans);
	}
	
	private static void forward() {
		for(int r = N - 1 ; r >= 0 ; --r){
			for(int c = 0 ; c < M ; ++c){
				if(tempMap[r][c].type == 1){
					tempMap[r][c].type = 0;
					if(r == N - 1) {
						enemy--;
						continue;
					}
					tempMap[r + 1][c].type = 1;
				}
			}
		}
	}

	private static void fire() {
		Cell[] targets = new Cell[3];
		double distance, distance_min;
		int limit = (D + 1 > N ? N : D + 1);
		
		for(int i = 0 ; i < 3 ; ++i){
			distance = 0;
			distance_min = 9999;
			
			for(int r = N - 1 ; r > N - limit ; --r){
				for(int c = 0 ; c < M ; ++c){
					if(tempMap[r][c].type == 0) continue;
					distance = getDistance(archer[i], tempMap[r][c]);
					if(distance > D) continue;
					if(distance == distance_min) targets[i] = (c < targets[i].c ? tempMap[r][c] : targets[i]); 
					else if(distance < distance_min){
						targets[i] = tempMap[r][c];
						distance_min = distance;
					}
				}
			}
		}
		
		for(Cell target : targets){
			if(target == null || tempMap[target.r][target.c].type == 0) continue;
			tempMap[target.r][target.c].type = 0;
			kill++;
			enemy--;
		}
	}

	private static double getDistance(Cell cell1, Cell cell2){
		return Math.abs(cell1.r - cell2.r) + Math.abs(cell1.c - cell2.c);
	}
	
	private static Cell[][] copy() {
		Cell[][] result = new Cell[N][M];
		for(int r = 0 ; r < N ; ++r){
			for(int c = 0 ; c < M ; ++c){
				result[r][c] = new Cell(map[r][c].r, map[r][c].c, map[r][c].type);
			}
		}
		
		return result;
	}
}
