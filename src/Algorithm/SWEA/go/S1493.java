package Algorithm.SWEA.go;

import java.util.Scanner;

public class S1493 {

	static Point[] map;
	static int T, P, Q, x, y, ans;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		init();
		T = sc.nextInt();

		for (int t = 1; t <= T; ++t) {
			P = sc.nextInt();
			Q = sc.nextInt();
			x = map[P].x + map[Q].x;
			y = map[P].y + map[Q].y;
			ans = 0;

			for (int i = 1; i < 1000001; ++i) {
				if (map[i].x == x && map[i].y == y) {
					ans = i;
					break;
				}
			}
			System.out.println("#" + t + " " + ans);
		}

	}

	static void init() {
		map = new Point[1000001];
		map[1] = new Point(1, 1);
		int index = 2;
		int max = 2;
		
		while(true) {
			int x = 1;
			int y = max;
			while(true) {
				map[index] = new Point(x, y);
				if(index == 1000000) return;
				index++;
				if(x == max && y == 1) break;
				x++;
				y--;
			}
			max++;
		}
	}

	static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}