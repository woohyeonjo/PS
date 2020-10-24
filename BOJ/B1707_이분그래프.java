package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B1707_이분그래프 {

	static final int RED = 1;
	static final int BLUE = -1;
	static ArrayList<ArrayList<Integer>> adj;
	static int[] colors;
	static int K, V, E;
	static boolean ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		K = stoi(br.readLine());

		for (int k = 0; k < K; ++k) {
			st = new StringTokenizer(br.readLine());

			V = stoi(st.nextToken());
			E = stoi(st.nextToken());

			ans = true;
			colors = new int[V + 1];
			adj = new ArrayList<>();
			for (int i = 0; i < V + 1; ++i) {
				adj.add(new ArrayList<>());
			}

			// 인접리스트 초기화 
			for (int i = 0; i < E; ++i) {
				st = new StringTokenizer(br.readLine());
				int from = stoi(st.nextToken());
				int to = stoi(st.nextToken());

				// 무방향 그래프
				adj.get(from).add(to);
				adj.get(to).add(from);
			}

			for (int i = 1; i < V + 1; ++i) {
				if(colors[i] == 0) {
					if(dfs(i, RED)) break;
				}
			}
			System.out.println(ans ? "YES" : "NO");
		}
	}
	
	private static boolean dfs(int start, int color) {
		// 현재 정점 색칠하기
		colors[start] = color;

		for (Integer i : adj.get(start)) {
			// 인접 정점의 색이 같으면 이분 그래프가 아니며 함수를 바로 끝내도록 한다.
			if (colors[i] == color) {
				ans = false;
				return true;
			}

			if (colors[i] == 0) {
				// 아직 방문하지 않았던 정점에 대해서 현재 정점과 다른 색상을 칠한다.
				if (dfs(i, -color))
					return true;
			}
		}
		return false;
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
