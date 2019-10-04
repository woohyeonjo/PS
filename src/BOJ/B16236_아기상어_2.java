package BOJ;


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B16236_아기상어_2 {
	
	static class Fish implements Comparable<Fish> {
		int r, c, level, eat, distance;
		boolean isShark;

		public Fish(int r, int c, int level) {
			super();
			this.r = r;
			this.c = c;
			this.level = level;
			this.eat = 0;
			this.distance = 0;
		}
		
		public void eat(){
			this.eat++;
			if(eat == level){
				level++;
				eat = 0;
			}
		}

		@Override
		public int compareTo(Fish o) {
			if(this.distance == o.distance){
				if(this.r == o.r)return this.c - o.c;
				else return this.r - o.r;
			}
			else return this.distance - o.distance;
		}
		
		public String toString() {
			if(isShark) return this.level + "[*]";
			else return this.level + "[" + this.distance +"]";
		}
	}
	
	static ArrayList<Fish> fishList;
	static Queue<Fish> q;
	static boolean[][] visited;
	static Fish[][] fishMap;
	static Fish shark;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int N, ans;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		fishList = new ArrayList<>();
		q = new LinkedList<>();
		fishMap = new Fish[N][N];
		ans = 0;
		
		
		int level = 0;
		for(int r = 0 ; r < N ; ++r){
			for(int c = 0 ; c < N ; ++c){
				level = sc.nextInt();
				if(level == 9) {
					shark = fishMap[r][c] = new Fish(r, c, 2);
					shark.isShark = true;
				}
				else if (level == 0) fishMap[r][c] = new Fish(r, c, 0);
				else if (level > 0)fishMap[r][c] = new Fish(r, c, level);
			}
		}
		hunting();
		System.out.println(ans);
		
	}

	private static void hunting() {
		while(true){
			visited = new boolean[N][N];
			q.offer(shark);
			visited[shark.r][shark.c] = true;
			
			while(!q.isEmpty()){
				Fish fish = q.poll();
				
				int nr, nc;
				for(int i = 0 ; i < 4 ; ++i){
					nr = fish.r + dir[i][0];
					nc = fish.c + dir[i][1];
					if(nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc]) continue;
					if(fishMap[nr][nc].level == 0 || fishMap[nr][nc].level == shark.level){
						q.offer(fishMap[nr][nc]);
						fishMap[nr][nc].distance = fish.distance + 1;
						visited[nr][nc] = true;
						continue;
					}
					if(fishMap[nr][nc].level < shark.level){
						fishMap[nr][nc].distance = fish.distance + 1;
						fishList.add(fishMap[nr][nc]);
						visited[nr][nc] = true;
						continue;
					}
				}
			}
			
			if(fishList.size() == 0) return;
			else if(fishList.size() > 1) Collections.sort(fishList);
			
			Fish fish = fishList.get(0);
			
			fishMap[shark.r][shark.c] = new Fish(shark.r, shark.c, 0);
			shark.r = fish.r;
			shark.c = fish.c;
			shark.eat();
			fishMap[fish.r][fish.c] = shark;
			fishList.clear();
			ans += fish.distance;
		}
	}
}
