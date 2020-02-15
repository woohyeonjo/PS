package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B3184_양 {
	static class Node implements Comparable<Node> {
		int r, c;
		int area;
		
		Node(int r, int c){
			this.r = r;
			this.c = c;
		}

		@Override
		public int compareTo(Node o) {
			return this.area - o.area;
		}
	}
	
	static char[][] map;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static Queue<Node> q;
	static ArrayList<Node> wolves, sheep;
	static int[][] population;
	static int R, C, wolfCnt, sheepCnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		q = new LinkedList<>();
		wolves = new ArrayList<>();
		sheep = new ArrayList<>();
		map = new char[R][C];
		
		// 데이터 입력받기 
		for(int r = 0 ; r < R ; ++r) {
			char[] line = br.readLine().toCharArray();
			for(int c = 0 ; c < C ; ++c) {
				char item = line[c];
				if(item == 'v') {
					wolfCnt++;
					wolves.add(new Node(r, c));
					map[r][c] = '.';
				} else if(item == 'o') {
					sheepCnt++;
					sheep.add(new Node(r, c));
					map[r][c] = '.';
				} else {
					map[r][c] = item;
				}
			}
		}
		
		// 영역 라벨링 
		int area = 1;
		for(int r = 0 ; r < R ; ++r) {
			for(int c = 0 ; c < C ; ++c) {
				if(map[r][c] == '.') {
					q.offer(new Node(r, c));
					map[r][c] = (char) (area + '0');
					labeling(area);
					area++;
				}
			}
		}
		
		// 양과 늑대의 영역 정보 업데이트 
		for(Node n : wolves) {
			n.area = map[n.r][n.c] - '0';
		}
		for(Node n : sheep) {
			n.area = map[n.r][n.c] - '0';
		}
		
		// 영역별 수 연산 
		population = new int[2][area];
		for(Node n : wolves) {
			population[0][n.area]++;
		}
		for(Node n : sheep) {
			population[1][n.area]++;
		}
		
		for(int i = 1 ; i < area ; ++i) {
			if(population[0][i] < population[1][i]) {
				wolfCnt -= population[0][i];
			} else {
				sheepCnt -= population[1][i];
			}
		}
			
		System.out.println(sheepCnt + " " + wolfCnt);
	}

	private static void labeling(int area) {
		while(!q.isEmpty()) {
			Node now = q.poll();
			for(int i = 0 ; i < 4 ; ++i) {
				int nr = now.r + dir[i][0];
				int nc = now.c + dir[i][1];
				if(nr >= R || nr < 0 || nc >= C || nc < 0) continue;
				if(map[nr][nc] == '.') {
					map[nr][nc] = (char) (area + '0');
					q.offer(new Node(nr, nc));
				}
			}
		}
	}
	
	private static void print() {
		for(int r = 0 ; r < R ; ++r) {
			for(int c = 0 ; c < C ; ++c) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
	}
}
