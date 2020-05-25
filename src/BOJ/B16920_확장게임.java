package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B16920_확장게임 {
	
	static class Node {
		int r, c;
		
		Node(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	
	static Queue<Node>[] qs;
	static char[][] map;
	static int[] castle;
	static int[] distance;
	static int N, M, P;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		P = stoi(st.nextToken());
		
		map = new char[N][M];
		castle = new int[P + 1];
		distance = new int[P + 1];
		qs = new LinkedList[P + 1];
		
		for(int i = 1 ; i <= P ; ++i) qs[i] = new LinkedList<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1 ; i <= P ; ++i) distance[i] = stoi(st.nextToken());
		
		for(int i = 0 ; i < N ; ++i) {
			String line = br.readLine();
			for(int j = 0 ; j < M ; ++j) {
				char ch = line.charAt(j);
				
				if(ch >= '0' && ch <= '9') {
					int player = ch - '0';
		
					castle[player]++;
					qs[player].offer(new Node(i, j));
				}
				map[i][j] = ch;
			}
		}
		
		int player = 1;
		int round = 0;
		
		while(true) {
			int maxDist = distance[player];
			int cnt = expendCastle(qs[player], maxDist, player);
			
			castle[player] += cnt;
			round += cnt;
			
			player++;
			if(player == P + 1) {
				if(round == 0) break;
				round = 0;
				player = 1;
			}
		}
		
		for(int i = 1 ; i <= P ; ++i) System.out.print(castle[i] + " ");
	}

	private static int expendCastle(Queue<Node> q, int maxDist, int player) {
		int cnt = 0;
		int dist = 1;
		
		while(!q.isEmpty()) {
			int size = q.size();
			
			for(int i = 0 ; i < size ; ++i) {
				Node cur = q.poll();
				
				for(int d = 0 ; d < 4 ; ++d) {
					int nr = cur.r + dir[d][0];
					int nc = cur.c + dir[d][1];
					if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
					if(map[nr][nc] == '.') {
						q.offer(new Node(nr, nc));
						map[nr][nc] = (char)(player + '0');
						cnt++;
					}
				}
			}
			dist++;
			if(dist > maxDist) break;
		}

		return cnt;
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
