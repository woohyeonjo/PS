package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B4991_로봇청소기 {

	static class Node {
		int r, c;

		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static ArrayList<Node> list;
	static int[][] dis;
	static boolean[] selected;
	static char[][] map;
	static int R, C, ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		while (true) {
			// 데이터 입력받기 
			st = new StringTokenizer(br.readLine());
			C = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());

			// 0, 0을 입력받는 경우 프로그램을 종료한다. 
			if (R == 0 && C == 0)
				break;

			map = new char[R][C];
			list = new ArrayList<>();

			// 더러운 곳 또는 로봇 청소기의 위치를 리스트에 저장한다. 
			for (int i = 0; i < R; ++i) {
				char[] line = br.readLine().toCharArray();
				for (int j = 0; j < C; ++j) {
					map[i][j] = line[j];
					if (map[i][j] == 'o') {
						list.add(0, new Node(i, j));
					} else if (map[i][j] == '*') {
						list.add(new Node(i, j));
					}
				}
			}

			// 0번째는 로봇 청소기, 나머지는 더러운 곳  
			int size = list.size();
			dis = new int[size][size];
			selected = new boolean[size];

			// 모든 지점 사이의 거리를 구한다. (로봇청소기, 더러운 곳)  
			for (int idx = 0; idx < size; ++idx) {
				// 닿지 못하는 더러운 곳이 있다면 바로 종료
				if (bfs(idx) == -1) {
					ans = -1;
					break;
				}
			}
			
			if(ans == -1) {
				System.out.println(ans);
				continue; // 이번 테스트케이스를 종료하고 다음으로 넘어간다. 
			} else {
				// 모든 경로중에 최소값을 찾는다. 
				ans = Integer.MAX_VALUE;
				selected[0] = true;
				dfs(0, 0, 0);
				System.out.println(ans);
			}
		}
	}

	private static void dfs(int depth, int dist, int from) {
		// 모든 지점을 방문했으면 최소값을 갱신하고 종료한다. 
		if (depth == selected.length - 1) {
			ans = ans > dist ? dist : ans;
			return;
		}

		for (int to = 1; to < selected.length; ++to) {
			if (!selected[to]) {
				selected[to] = true;
				// 거리인접행렬에서 값을 꺼내 더하고 다음 지점으로 향한다. 
				dfs(depth + 1, dist + dis[from][to], to);
				selected[to] = false;
			}
		}
	}

	private static int bfs(int from) {
		// 로봇청소기가 지나갔던 길을 다시 갈 수 있지만
		// 각 지점 사이의 최소거리는 방문체크를 했을 때 더 빠르게 찾는다.
		boolean[][] visited = new boolean[R][C];
		int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
		Queue<Node> q = new LinkedList<>();

		int dust = 0;
		int cnt = 0;
		Node start = list.get(from);
		visited[start.r][start.c] = true;
		q.offer(start);

		while (!q.isEmpty()) {

			int size = q.size();
			cnt++;

			for (int i = 0; i < size; ++i) {
				Node cur = q.poll();

				for (int d = 0; d < 4; ++d) {
					int nr = cur.r + dir[d][0];
					int nc = cur.c + dir[d][1];

					if (nr >= R || nr < 0 || nc >= C || nc < 0 || visited[nr][nc] || map[nr][nc] == 'x')
						continue;
					if (map[nr][nc] == 'o' || map[nr][nc] == '*') {
						// 현재 도착한 지점이 몇번 인덱스 지점인지 찾는다. 
						for (int to = 0; to < list.size(); ++to) {
							Node end = list.get(to);
							if (end.r == nr && end.c == nc) {
								// 거리배열을 갱신한다. 
								dis[from][to] = cnt;
								dis[to][from] = cnt;
								dust++;
							}
						}
					}
					visited[nr][nc] = true;
					q.offer(new Node(nr, nc));
				}
			}
		}
		// 방문한 더러운 곳이 전체 더러운 곳 보다 작으면 닿지못하는 지점이 있는 것이다. 
		if (dust != list.size() - 1)
			return -1;
		else
			return 0;
	}
}
