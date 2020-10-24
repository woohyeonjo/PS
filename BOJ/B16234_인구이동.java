package BOJ;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B16234_인구이동 {
	
	static class Nation {
		int r, c;
		int union;
		int population;
		ArrayList<int[]> border;
		
		public Nation(int r, int c, int population) {
			super();
			this.r = r;
			this.c = c;
			this.union = 0;
			this.population = population;
			this.border = new ArrayList<>();
		}
		
		@Override
		public String toString() {
			return this.population + "[" + this.union +"]";
		}
	}
	
	static final int[] UP = {-1, 0};
	static final int[] DOWN = {1, 0};
	static final int[] LEFT = {0, -1};
	static final int[] RIGHT = {0, 1};
	
	static int[][] dir = {UP, DOWN, LEFT, RIGHT};
	static Nation[][] map;
	static Queue<Nation> q;
	static HashMap<Integer, int[]> unions;
	static int N, L, R, ans;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		L = sc.nextInt();
		R = sc.nextInt();
		
		map = new Nation[N][N];
		q = new LinkedList<>();
		unions = new HashMap<>();
		
		ans = 0;
		
		for(int r = 0 ; r < N ; ++r){
			for(int c = 0 ; c < N ; ++c){
				map[r][c] = new Nation(r, c, sc.nextInt());
			}
		}
		
		go();
		System.out.println(ans);
	}

	private static void go() {
		while(openning()){
			makeUnion();
			if(!migration()) return;
			else ans++;
			
			disUnion();
		}
	}

	private static void disUnion() {
		for(int r = 0 ; r < N ; ++r){
			for(int c = 0 ; c < N ; ++c){
				map[r][c].union = 0;
				map[r][c].border.clear();
			}
		}
	}

	private static void makeUnion() {
		int union = 1;
		int nr, nc;
		
		for(int r = 0 ; r < N ; ++r){
			for(int c = 0 ; c < N ; ++c){
				if(map[r][c].union > 0) continue;
				unions.put(union, new int[3]);
				unions.get(union)[0] = 1;
				unions.get(union)[1] = map[r][c].population;
				map[r][c].union = union;
				q.offer(map[r][c]);
				bfs();
				unions.get(union)[2] = (int) unions.get(union)[1] / unions.get(union)[0];
				union++;
			}
		}
	}

	private static void bfs() {
		while(!q.isEmpty()){
			Nation nation = q.poll();
			
			int nr, nc;
			for(int i = 0 ; i < nation.border.size() ; ++i){
				nr = nation.r + nation.border.get(i)[0];
				nc = nation.c + nation.border.get(i)[1];
				if(nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc].union > 0) continue;
				map[nr][nc].union = nation.union;
				
				unions.get(nation.union)[0] += 1;
				unions.get(nation.union)[1] += map[nr][nc].population;
				
				q.offer(map[nr][nc]);
			}
		}
	}

	private static boolean migration() {
		
		boolean flag = false;
		
		for(int r = 0 ; r < N ; ++r){
			for(int c = 0 ; c < N ; ++c){
				if(map[r][c].population != unions.get(map[r][c].union)[2]) {
					flag = true;
					map[r][c].population = unions.get(map[r][c].union)[2];
				}
			}
		}
		return flag;
	}

	private static boolean openning() {
		boolean isOpen = false;
		
		int populationGap = 0;
		int nr, nc;
		for(int r = 0 ; r < N ; ++r){
			for(int c = 0 ; c < N ; ++c){
				for(int i = 0 ; i < 4 ; ++i){
					nr = r + dir[i][0];
					nc = c + dir[i][1];
					if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
					populationGap = Math.abs(map[r][c].population - map[nr][nc].population);
					if(populationGap <= R && populationGap >= L){
						isOpen = true;
						map[r][c].border.add(dir[i]);
					}
				}
			}
		}
		
		return isOpen;
	}
}
