package BOJ.go;

import java.io.*;
import java.util.*;

public class B9376_탈옥 {
	private static class Node implements Comparable<Node> {
		int x, y, door;

		Node(int x, int y, int door) {
			this.x = x;
			this.y = y;
			this.door = door;
		}

		@Override
		public int compareTo(Node o) {
			return this.door - o.door;
		}
	}

	static int r, c, result;
	static int[] runAway = new int[2];
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static char[][] map;
	static int[][][] visit;
	static PriorityQueue<Node> pq;
	static Queue<Node> temp = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int tnum = stoi(br.readLine());

		for (int t = 1; t <= tnum; t++) {
			st = new StringTokenizer(br.readLine());

			r = stoi(st.nextToken());
			c = stoi(st.nextToken());

			init();

			for (int i = 1; i <= r; i++) {
				char[] input = br.readLine().toCharArray();
				for (int j = 1; j <= c; j++) {
					map[i][j] = input[j - 1];
					if (map[i][j] == '$') {
						temp.add(new Node(i, j, 0));
					}
				}
			}
			bfs();
			calc();
			System.out.println(result);
		}
	}

	private static void bfs() {

		for (int k = 0; k < 3; k++) {
			pq.add(temp.poll());
			visit[pq.peek().x][pq.peek().y][k] = 0;

			while (!pq.isEmpty()) {
				Node n = pq.poll();
				for (int i = 0; i < 4; i++) {
					int nx = n.x + dx[i];
					int ny = n.y + dy[i];

					if (nx >= 0 && ny >= 0 && nx <= r + 1 && ny <= c + 1) {
						if (visit[nx][ny][k] >= 0 || map[nx][ny] == '*')
							continue;
						if (map[nx][ny] == '#') {
							visit[nx][ny][k] = visit[n.x][n.y][k] + 1;
							pq.add(new Node(nx, ny, n.door + 1));
						} else {
							visit[nx][ny][k] = visit[n.x][n.y][k];
							pq.add(new Node(nx, ny, n.door));
						}
					}
				}
			}
		}

	}

	private static void calc() {
		for (int i = 0; i < r + 2; i++) {
			for (int j = 0; j < c + 2; j++) {
				if (map[i][j] != '*') {
					int k = visit[i][j][0] + visit[i][j][1] + visit[i][j][2];
					result = Math.min(result, map[i][j] == '#' ? k - 2 : k);
				}
			}
		}
	}

	private static void init() {
		map = new char[r + 2][c + 2];
		visit = new int[r + 2][c + 2][3];
		for (int i = 0; i < r + 2; i++) {
			for (int j = 0; j < c + 2; j++) {
				for (int k = 0; k < 3; k++) {
					visit[i][j][k] = -1;
				}
			}
		}
		pq = new PriorityQueue<Node>();
		result = 987654321;
		temp.add(new Node(0, 0, 0));
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}

}

