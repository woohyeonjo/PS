package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2842_집배원한상덕 {

	static class Node {
		int r, c;
		
		Node(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	
	static char[][] map;
	static int[][] heightMap;
	static ArrayList<Integer> heightList;
	static int N, house;
	static Node start;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = stoi(br.readLine());
		
		house = 0;
		map = new char[N][N];
		heightMap = new int[N][N];
		heightList = new ArrayList<>();
		
		// 데이터 입력 
		for(int r = 0 ; r < N ; ++r) {
			char[] line = br.readLine().toCharArray();
			for(int c = 0 ; c < N ; ++c) {
				map[r][c] = line[c];
				if(map[r][c] == 'K') house++;
				if(map[r][c] == 'P') {
					start = new Node(r, c);
				}
			}
		}
		
		int idx = 0;
		for(int r = 0 ; r < N ; ++r) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0 ; c < N ; ++c) {
				heightMap[r][c] = stoi(st.nextToken());
				if(!heightList.contains(heightMap[r][c])) {
					heightList.add(heightMap[r][c]);
				}
			}
		}
		
		// 투 포인터를 사용하기 위해 오름차순 정렬 
		Collections.sort(heightList);
		
		System.out.println(solve());
	}
	
	private static int solve() {
		int min = 0;
		int max = 0;
		int ans = Integer.MAX_VALUE;
		
		while(min < heightList.size() && max < heightList.size()) {
			if(bfs(heightList.get(min), heightList.get(max))) {
				int gap = heightList.get(max) - heightList.get(min);
				ans = ans > gap ? gap : ans;
				
				// 탐색에 성공하면 min 값을 늘려 피로도를 줄여나간다. 
				min++;
			} else {
				// 탐색에 실패하면 max 값을 늘려 더 높은 피로도로 탐색할 수 있도록 한다.
				max++;
			}
		}
		
		return ans;
	}

	private static boolean bfs(int min, int max) {
		// 시작 지점이 min 보다 낮거나 max 보다 높으면 시작할 수 없다.
		int startH = heightMap[start.r][start.c];
		if(startH > max || startH < min) return false;
		
		int[][] dir = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
		Queue<Node> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		int cnt = 0;
	
		q.offer(start);
		visited[start.r][start.c] = true;
		
		while(!q.isEmpty()) {
			Node now = q.poll();
			
			if(map[now.r][now.c] == 'K') cnt++;
			
			if(cnt == house) return true;
			
			for(int d = 0 ; d < 8 ; ++d) {
				int nr = now.r + dir[d][0];
				int nc = now.c + dir[d][1];
				
				if(nr >= N || nr < 0 || nc >= N || nc < 0 || visited[nr][nc]) continue;
				
				if(heightMap[nr][nc] >= min && heightMap[nr][nc] <= max) {
					visited[nr][nc] = true;
					q.offer(new Node(nr, nc));
				}
			}
		}

		return false;
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
