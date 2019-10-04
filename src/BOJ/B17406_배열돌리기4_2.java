package BOJ;


import java.util.Scanner;

public class B17406_배열돌리기4_2 {
	
	static class Rotation {
		int r, c, s;

		public Rotation(int r, int c, int s) {
			this.r = r;
			this.c = c;
			this.s = s;
		}
		
		@Override
		public String toString() {
			return "[" + r + ", " + c + ", " + s + "]";
		}
	}
	
	static Rotation[] rotations;
	static int[] rotationSet;
	static boolean[] selected; 
	static int[][] map;
	static int[][] temp;
	static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	static int N, M, K, ans, current;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		
		map = new int[N + 1][M + 1];
		rotations = new Rotation[K];
		rotationSet = new int[K];
		selected = new boolean[K];
		ans = Integer.MAX_VALUE;
		
		for(int r = 1 ; r <= N ; ++r) {
			for(int c = 1 ; c <= M ; ++c) {
				map[r][c] = sc.nextInt();
			}
		}
		
		for(int i = 0 ; i < K ; ++i) {
			rotations[i] = new Rotation(sc.nextInt(), sc.nextInt(), sc.nextInt());
		}
		
//		copy();
//		rotate(rotations[0]);
//		print();
		
		permu(0);
		
		System.out.println(ans);
	}

	private static void permu(int index) {
		
		if(index == K) {
			copy();
			for(int i = 0 ; i < K ; ++i) {
				rotate(rotations[rotationSet[i]]);
			}
			current = findMin();
			ans = ans > current ? current : ans;
			return;
		}
		
		for(int i = 0 ; i < K ; ++i) {
			if(selected[i]) continue;
			rotationSet[index] = i;
			selected[i] = true;
			permu(index + 1);
			selected[i] = false;
		}
	}

	private static void rotate(Rotation rotation) {
		int len = rotation.s * 2 + 1;
		int depth = len / 2;
		int startNum;
		int sr = rotation.r - rotation.s;
		int sc = rotation.c - rotation.s;
		int r, c, nr, nc, d = 0;
		
		for(int i = 0 ; i < depth ; ++i) {
			r = sr;
			c = sc;
			startNum = temp[r][c];
			while(d < 4) {
				nr = r + dir[d][0];
				nc = c + dir[d][1];
				if(nr >= sr && nr < sr + len - i * 2 && nc >= sc && nc < sc + len - i * 2) {
					temp[r][c] = temp[nr][nc];
					r = nr;
					c = nc;
				} else d++;
			}
			temp[r][c + 1] = startNum;
			d = 0;
			sr ++;
			sc ++;
		}
	}

	private static int findMin() {
		int result = Integer.MAX_VALUE;
		int sum;
		
		for(int r = 1 ; r <= N ; ++r) {
			sum = 0;
			for(int c = 1 ; c <= M ; ++c) sum += temp[r][c];
			result = result > sum ? sum : result;
		}
		
		return result;
	}

	private static void copy() {
		temp = new int[N + 1][M + 1];
		
		for(int r = 1 ; r <= N ; ++r) {
			for(int c = 1 ; c <= M ; ++c) {
				temp[r][c] = map[r][c];
			}
		}
	}
	
	private static void print() {
		for(int r = 1 ; r <= N ; ++r) {
			for(int c = 1 ; c <= M ; ++c) {
				System.out.print(temp[r][c] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
