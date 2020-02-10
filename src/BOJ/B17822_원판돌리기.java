package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B17822_원판돌리기 {
	
	static class Node {
		int r, c;
		
		Node(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	
	static final int CLOCKWISE = 0;
	static final int ANTICLOCKWISE = 1;

	static Queue<Node> q;
	static ArrayList<Node> same;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int[][] map;
	static boolean[][] visited;
	static int N, M, T;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		T = stoi(st.nextToken());
		
		map = new int[N + 1][M + 1];
		q = new LinkedList<>();
		
		for(int r = 1 ; r <= N ; ++r) {
			st = new StringTokenizer(br.readLine());
			for(int c = 1 ; c <= M ; ++c) { 
				map[r][c] = stoi(st.nextToken());
			}
		}
		
		for(int i = 0 ; i < T ; ++i) {
			st = new StringTokenizer(br.readLine());
			
			int disk = stoi(st.nextToken());
			int clock = stoi(st.nextToken());
			int times = stoi(st.nextToken());
			
			// 원판 돌리기 
			spin(disk, clock, times);
			
			// 후 처리
			if(!removeSameNum()) {
				// 지울게 없을 때만 수행
				actionByAvg();
			}
		}
		System.out.println(sum());
	}
	
	private static int sum() {
		int sum = 0;
		
		for(int r = 1 ; r <= N ; ++r) {
			for(int c = 1 ; c <= M ; ++c) {
				sum += map[r][c];
			}
		}
		
		return sum;
	}

	private static void actionByAvg() {
		// 평균값이 소숫점까지 나와야지 제대로 작동함
		double sum = 0;
		double cnt = 0;
		double avg = 0;
		
		for(int r = 1 ; r <= N ; ++r) {
			for(int c = 1 ; c <= M ; ++c) {
				if(map[r][c] > 0) {
					sum += map[r][c];
					cnt++;
				}
			}
		}
		
		if(cnt == 0) return;
		avg = sum / cnt;
		
		for(int r = 1 ; r <= N ; ++r) {
			for(int c = 1 ; c <= M ; ++c) {
				if(map[r][c] == 0) continue;
				if(map[r][c] < avg) map[r][c] += 1;
				else if(map[r][c] > avg) map[r][c] -= 1;
			}
		}
	}

	private static boolean removeSameNum() {
		boolean flag = false;
		visited = new boolean[N + 1][M + 1];
		same = new ArrayList<>();
		
		// BFS 사방탐색을 통해서 숫자가 같은 부분을 모두 찾아냄 
		for(int r = 1 ; r <= N ; ++r) {
			for(int c = 1 ; c <= M ; ++c) {
				if(map[r][c] == 0 || visited[r][c]) continue;
				Node now = new Node(r, c);
				same.add(now);
				q.offer(now);
				bfs(map[r][c]);
				
				if(same.size() > 1) {
					flag = true;
					for(Node n : same) {
						map[n.r][n.c] = 0;
					}
				}
				same.clear();
			}
		}
		return flag;
	}

	private static void bfs(int num) {
		while(!q.isEmpty()) {
			Node now = q.poll();
			
			for(int i = 0 ; i < 4 ; ++i) {
				int nr = now.r + dir[i][0];
				int nc = now.c + dir[i][1];
				
				// 한 행에서 양쪽 끝도 원판이기 때문에 붙어있음 
				if(nc > M) nc = 1;
				else if(nc < 1) nc = M;
				
				if(nr > N || nr < 1 || visited[nr][nc]) continue;
				
				if(map[nr][nc] == num) {
					visited[nr][nc] = true;
					Node next = new Node(nr, nc);
					q.offer(next);
					same.add(next);
				}
			}
		}
		
	}

	private static void spin(int disk, int clock, int times) {
		int current = disk;
		
		while(current <= N) {
			for(int i = 0 ; i < times ; ++i) {
				rotate(current, clock);
			}
			current += disk;
		}
	}

	private static void rotate(int disk, int clock) {
		if(clock == CLOCKWISE) {
			int temp = map[disk][M];
			
			for(int i = M ; i > 1 ; --i) {
				map[disk][i] = map[disk][i - 1];
			}
			
			map[disk][1] = temp;
		} else {
			int temp = map[disk][1];
			
			for(int i = 1 ; i < M ; ++i) {
				map[disk][i] = map[disk][i + 1];
			}
			
			map[disk][M] = temp;
		}
	}

	private static void print() {
		for(int r = 1 ; r <= N ; ++r) {
			for(int c = 1 ; c <= M ; ++c) { 
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
