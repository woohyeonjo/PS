package BOJ;


import java.util.Scanner;

public class B17143_낚시왕 {
	
	static class Shark {
		int r, c, speed, direction, size;

		public Shark(int r, int c, int speed, int direction, int size) {
			super();
			this.r = r;
			this.c = c;
			this.speed = speed;
			this.direction = direction;
			this.size = size;
		}
		
		@Override
		public String toString() {
			return this.size + "";
		}
	}
	
	static int[] dir = {0, -1, 1, 1, -1}; 
	static int R, C, M, ans;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		R = sc.nextInt();
		C = sc.nextInt();
		M = sc.nextInt();
		
		Shark[][] map1 = new Shark[R + 1][C + 1];
		Shark[][] map2 = new Shark[R + 1][C + 1];
		ans = 0;
		
		int r, c, s, d, z;
		for(int m = 0 ; m < M ; ++m){
			r = sc.nextInt();
			c = sc.nextInt();
			s = sc.nextInt();
			d = sc.nextInt();
			z = sc.nextInt();
			
			Shark shark = new Shark(r, c, s, d, z);
			map1[r][c] = shark;
		}
		
		fishing(map1, map2);
		System.out.println(ans);
	}

	private static void fishing(Shark[][] other, Shark[][] another) {
		for(int step = 1 ; step <= C ; ++step){
			
			if(step % 2 == 0){
				// another
				hook(another, step);
				move(another, other, step);
			} else {
				// other
				hook(other, step);
				move(other, another, step);
			}
		}
	}

	private static void hook(Shark[][] from, int step) {
		for(int r = 1 ; r <= R ; ++r){
			if(from[r][step] != null){
				ans += from[r][step].size;
				from[r][step] = null;
				return;
			}
		}
	}

	private static void move(Shark[][] from, Shark[][] to, int step) {
		for(int r = 1 ; r <= R ; ++r){
			for(int c = 1 ; c <= C ; ++c){
				if(from[r][c] == null) continue;
				Shark shark = from[r][c];
				from[r][c] = null;
				
				// shark update
				swim(shark);
				
				if(to[shark.r][shark.c] == null) {
					to[shark.r][shark.c] = shark;
				} else {
					if(to[shark.r][shark.c].size < shark.size){
						to[shark.r][shark.c] = shark;
					}
				}
			}
		}
	}

	private static void swim(Shark shark) {
		if(shark.direction > 2){
			for(int i = 0 ; i < shark.speed ; ++i){
				if(shark.c == C)shark.direction = 4;
				else if(shark.c == 1)shark.direction = 3;
				
				shark.c += dir[shark.direction];
			}
			
		} else {
			for(int i = 0 ; i < shark.speed ; ++i){
				if(shark.r == R)shark.direction = 1;
				else if(shark.r == 1)shark.direction = 2;
				
				shark.r += dir[shark.direction];
			}
		}
	}
}
