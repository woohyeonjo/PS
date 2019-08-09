package BOJ.go;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B16236 {
	static class Shark {
		int r, c, level, eat;

		public Shark() {}
		public Shark(int r, int c, int level) {
			super();
			this.r = r;
			this.c = c;
			this.level = level;
			this.eat = 0;
		}
		
		@Override
		public String toString() {
			return "S" + this.level;
		}
		
	}
	
	static class Fish implements Comparable<Fish>{
		int r, c, level, distance;

		public Fish(int r, int c, int distance) {
			this.r = r;
			this.c = c;
			this.distance = distance;
		}
		public Fish(int r, int c, int level, int distance) {
			this.r = r;
			this.c = c;
			this.level = level;
			this.distance = distance;
		}
		
		@Override
		public String toString() {
			return this.level + "/" + this.distance;
		}

		@Override
		public int compareTo(Fish o) {
			if(this.distance - o.distance == 0 ) {
				if(this.r - o.r == 0) {
					return this.c - o.c;
				} else return this.r - o.r;
			} else return this.distance - o.distance;
		}
		
	}
	
	static Fish[][] map;
	static boolean[][] visited;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static Queue<Fish> q;
	static ArrayList<Fish> fishList;
	static Shark shark;
	static int size;
	static int N, ans;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		map = new Fish[N][N];
		fishList = new ArrayList<Fish>();
		q = new LinkedList<Fish>();
		size = 0;
		ans = 0;
		
		int input;
		for(int r = 0 ; r < N ; ++r) {
			for(int c = 0 ; c < N ; ++c) {
				input = sc.nextInt();
				if(input == 9) {
					shark = new Shark(r, c, 2);
					map[r][c] = new Fish(r, c, 2, 0);
				}
				else if(input > 0) {
					Fish fish = new Fish(r, c, input, 99);
					fishList.add(fish);
					map[r][c] = fish;
					size++;
				} else map[r][c] = new Fish(r, c, 0, 99);
			}
		}
		
		
		while(true) {
			if(size == 0) break;
			calc();
			Collections.sort(fishList);
			if(!eat()) break;
		}
		
		System.out.println(ans);
	}

	private static boolean eat() {
		Fish food = null;
		for(int i = 0 ; i < size ; ++i) {
			food = fishList.get(i);
			if(food.distance == 99) return false;
			if(food.level < shark.level) {
				fishList.remove(i);
				break;
			}
			food = null;
		}
		if(food == null) return false;
		map[shark.r][shark.c].level = 0;
		shark.r = food.r;
		shark.c = food.c;
		shark.eat++;
		if(shark.eat == shark.level) {
			shark.level++;
			shark.eat = 0;
		}
		ans += food.distance;
		map[food.r][food.c].level = shark.level;
		map[food.r][food.c].distance = 0;
		
		size--;
		return true;
	}

	private static void calc() {
		visited = new boolean[N][N];
		int nr, nc;
		
		q.offer(map[shark.r][shark.c]);
		visited[shark.r][shark.c] = true; 
		while(!q.isEmpty()) {
			Fish f = q.poll();
			for(int i = 0 ; i < 4 ; ++i) {
				nr = f.r + dir[i][0];
				nc = f.c + dir[i][1];
				if(nr >= N || nr < 0 || nc >= N || nc < 0 || visited[nr][nc] || map[nr][nc].level > shark.level) continue;
				map[nr][nc].distance = f.distance + 1;
				visited[nr][nc] = true;
				q.offer(map[nr][nc]);
			}
		}
		
	}
	

}
