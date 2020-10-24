package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2933_미네랄 {
	
	
	// 실패한 코드 입니다.
	// 공중에 떠 있는 클러스터를 찾는 부분에 문제가 있는거 같습니다.

	
	static class Node {
		int r, c;
		
		Node(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	
	static ArrayList<Node> paticle;
	static ArrayList<Node> cluster; 
	static char[][] map;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int R, C, N;
	static int[] stick;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = stoi(st.nextToken());
		C = stoi(st.nextToken());
		
		// 맵 입력받기  
		map = new char[R][C];
		for(int i = 0 ; i < R ; ++i) {
			char[] line = br.readLine().toCharArray();
			for(int j = 0 ; j < C ; ++j) {
				map[i][j] = line[j];
			}
		}
		
		// stick 던지는 위치 입력받기 
		N = stoi(br.readLine());
		stick = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; ++i) {
			stick[i] = stoi(st.nextToken());
		}
		
		// 막대 던지기 
		for(int i = 0 ; i < N ; ++i) {
			// 부딪힌 미네랄 주위의 미네랄을 저장하는 리스트 초기화 
			paticle = null;
			
			// 던지는 높이 
			int r = R - stick[i];
			// 왼쪽에서 던질 때 
			if(i % 2 == 0) {
				for(int c = 0 ; c < C ; ++c) {
					if(map[r][c] == 'x') {
						getPaticle(r, c);
						break;
					}
				}
			// 오른쪽에서 던질 때 
			} else {
				for(int c = C - 1 ; c >= 0 ; --c) {
					if(map[r][c] == 'x') {
						getPaticle(r, c);
						break;
					}
				}
			}
			
			if(paticle == null) continue;
			
			for(int p = 0 ; p < paticle.size() ; ++p) {
				if(bfs(paticle.get(p))) continue;
				dropMineral();
			}
		}
		print();
	}
	
	private static void dropMineral() {
		int cnt = 0;
		
		// 현재 떨어질 클러스터를 모두 지운다. 
		for(Node n : cluster) {
			map[n.r][n.c] = '.';
		}
		
		// 현재 떨어질 클러스터가 몇칸이나 내려올 수 있는지 체크한다. 
		OUTER: for(int i = 1 ; i < R ; ++i) {
			for(Node n : cluster) {
				if(n.r + i >= R || map[n.r + i][n.c] == 'x') {
					break OUTER;
				}
			}
			cnt = i;
		}
		
		// 계산된 칸 만큼 이동시킨 클러스터를 새로 그린다. 
		for(Node n : cluster) {
			map[n.r + cnt][n.c] = 'x';
		}
	}

	private static boolean bfs(Node start) {
		// 시작 지점이 바닥에 닿아있으면 바로 리턴한다. 
		if(start.r == R - 1) return true;
		
		cluster = new ArrayList<>();
		boolean[][] visited = new boolean[R][C];
		Queue<Node> q = new LinkedList<>();
		visited[start.r][start.c] = true;
		cluster.add(start);
		q.offer(start);
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			for(int d = 0 ; d < 4 ; ++d) {
				int nr = cur.r + dir[d][0];
				int nc = cur.c + dir[d][1];
				if(nr >= R || nr < 0 || nc >= C || nc < 0 ||
				   visited[nr][nc] || map[nr][nc] == '.') continue;
				
				// 이 클러스터가 바닥에 닿아있다면 리턴한다. 
				if(nr == R - 1) return true;
				
				Node next = new Node(nr, nc);
				visited[nr][nc] = true;
				cluster.add(next);
				q.offer(next);
			}
		}
		
		return false;
	}

	private static void getPaticle(int r, int c) {
		paticle = new ArrayList<>();
		
		// 막대가 날아가다 최초로 부딪히는 미네랄의 사방에 있는 미네랄을 저장한다. 
		for(int d = 0 ; d < 4 ; ++d) {
			int nr = r + dir[d][0];
			int nc = c + dir[d][1];
			if(nr >= R || nr < 0 || nc >= C || nc < 0 ||
				map[nr][nc] == '.') continue;
			paticle.add(new Node(nr, nc));
		}
		// 막대에 부딧힌 미네랄은 제거한다. 
		map[r][c] = '.';
	}
	
	private static void print() {
		for(int i = 0 ; i < R ; ++i) {
			for(int j = 0 ; j < C ; ++j) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
