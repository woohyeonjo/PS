package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B16985_Maaaaaaaaaze {
	
	static class Node {
		int r, c, h;
		
		Node(int h, int r, int c){
			this.r = r;
			this.c = c;
			this.h = h;
		}
	}
	
	static Queue<Node> q;
	static int[][] dir = {{-1, 0, 0}, {1, 0, 0}, {0, -1, 0}, {0, 1, 0}, {0, 0, -1}, {0, 0, 1}};
	static int[][][] map; // 데이터를 입력받는 원본 맵 
	static int[] rotation; // 각 층의 회전 각도를 입력 
	static int[] stack; // 각 층에 쌓일 레이어를 입력 
	static boolean[] selected; // 레이어 쌓을 때 방문체크 
	static boolean[][][] visited; // BFS 미로탈출 시 방문체크 
	static int ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
	
		ans = Integer.MAX_VALUE;
		rotation = new int[5];
		stack = new int[5];
		selected = new boolean[5];
		map = new int[5][5][5];
		
		for(int h = 0 ; h < 5 ; ++h) {
			for(int r = 0 ; r < 5 ; ++r) {
				st = new StringTokenizer(br.readLine());
				for(int c = 0 ; c < 5 ; ++c) {
					map[h][r][c] = stoi(st.nextToken());
				}
			}
		}
		
		stackMap(0);
		
		if(ans == Integer.MAX_VALUE) ans = -1;
		System.out.println(ans);
	}

	// 맵의 레이어를 쌓는 함수 
	private static void	stackMap(int depth) {
		if(depth == 5) {
			// 현재 순열로 쌓는 맵 
			int[][][] stacked = new int[5][5][5];
			
			// 새로운 맵 stacked에 각 층에 레이어 삽입 
			for(int i = 0 ; i < 5 ; ++i) {
				stacked[i] = copy(map[stack[i]]);
			}
			
			// stacked를 넘겨 각 층을 돌린다. 
			rotateLayer(0, stacked);
			
			return;
		}
		
		for(int i = 0 ; i < 5 ; ++i) {
			if(!selected[i]) {
				selected[i] = true;
				stack[depth] = i;
				stackMap(depth + 1);
				selected[i] = false;
			}
		}
	}
	
	// 맵의 각 레이어를 돌리는 함수 
	private static void rotateLayer(int depth, int[][][] stacked) {
		if(depth == 5) {
			// 원본을 수정하지 않기 위해 돌릴 맵을 복사 
			int[][][] rotated = copy(stacked);
			
			// 복사된 맵의 각 층을 돌린다. 
			for(int i = 0 ; i < 5 ; ++i) {
				rotated[i] = rotate(rotated[i], rotation[i]);
			}
			
			// 진입구와 탈출구는 면을 공유하지 않기 때문에 대각선 양끝의 경우로 다음과 같다.
			go(0, 0, 0, 4, 4, 4, rotated);
			go(0, 4, 0, 4, 0, 4, rotated);
			go(0, 0, 4, 4, 4, 0, rotated);
			go(0, 4, 4, 4, 0, 0, rotated);
			
			return;
		}
		
		for(int i = 0 ; i < 4 ; ++i) {
			rotation[depth] = i;
			rotateLayer(depth + 1, stacked);
		}
	}

	private static void go(int sh, int sr, int sc, int fh, int fr, int fc, int[][][] rotated) {
		
		int cnt = 0;
		
		visited = new boolean[5][5][5];
		q = new LinkedList<>();
		
		if(rotated[sh][sr][sc] == 1) {
			q.offer(new Node(sh, sr, sc));
			visited[sh][sr][sc] = true;
		}

		while(!q.isEmpty()) {
			int size = q.size();
			cnt++;
			for(int i = 0 ; i < size ; ++i) {
				Node now = q.poll();
				
				if(now.h == fh && now.r == fr && now.c == fc) {
					ans = ans > cnt - 1 ? cnt - 1 : ans;
					return;
				}
				
				for(int d = 0 ; d < 6 ; ++d) {
					int nh = now.h + dir[d][0];
					int nr = now.r + dir[d][1];
					int nc = now.c + dir[d][2];
					
					if(nh >= 5 || nh < 0 || nr >= 5 || nr < 0 || nc >= 5 || nc < 0) continue;
					if(visited[nh][nr][nc] || rotated[nh][nr][nc] == 0) continue;
					
					q.offer(new Node(nh, nr, nc));
					visited[nh][nr][nc] = true;
				}
			}
		}
	}

	private static int[][] rotate(int[][] arr, int times) {
		for(int i = 0 ; i < times ; ++i) {
			arr = rotate90(arr);
		}
		return arr;
	}
	
	private static int[][] rotate90(int[][] arr) {
		int[][] result = new int[5][5];
		
		for(int r = 0 ; r < 5 ; ++r) {
			for(int c = 0 ; c < 5 ; ++c) {
				result[c][5 - 1 - r] = arr[r][c];
			}
		}
		
		return result;
	}
	
	private static int[][][] copy(int[][][] arr) {
		int[][][] result = new int[5][5][5];
		
		for(int h = 0 ; h < 5 ; ++h) {
			for(int r = 0 ; r < 5 ; ++r) {
				for(int c = 0 ; c < 5 ; ++c) {
					result[h][r][c] = arr[h][r][c];
				}
			}
		}
		
		return result;
	}
	
	private static int[][] copy(int[][] arr) {
		int[][] result = new int[5][5];
		
		for(int r = 0 ; r < 5 ; ++r) {
			for(int c = 0 ; c < 5 ; ++c) {
				result[r][c] = arr[r][c];
			}
		}
		
		return result;
	}
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
