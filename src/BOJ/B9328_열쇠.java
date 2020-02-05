package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B9328_열쇠 {
	
	static class Node {
		int r, c;
		
		Node(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	
	static Queue<Node> q;
	static HashMap<Character, Character> keys;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static char[][] map;
	static boolean[][] visited;
	static int T, H, W;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = stoi(br.readLine());
		
		for(int t = 0 ; t < T ; ++t) {
			st = new StringTokenizer(br.readLine());
			H = stoi(st.nextToken());
			W = stoi(st.nextToken());
			
			// 건물 밖으로 나갈 수 있기 때문에 map에 패딩을 준다. 
			map = new char[H + 2][W + 2];
			q = new LinkedList<>();
			keys = new HashMap<>();
			
			char[] line;
			for(int r = 1 ; r <= H ; ++r) {
				line = br.readLine().toCharArray();
				for(int c = 1 ; c <= W ; ++c) {
					map[r][c] = line[c - 1];
				}
			}
			
			for(int r = 0 ; r < H + 2 ; ++r) {
				for(int c = 0 ; c < W + 2 ; ++c) {
					if(r == 0 || r == H + 1 || c == 0 || c == W + 1) {
						map[r][c] = '.';
					}
				}
			}
			
			line = br.readLine().toCharArray();
			
			for(int i = 0 ; i < line.length ; ++i) {
				if(line[i] == '0') break;
				// 소문자 -> 대문자 : -32
				// 대문자 -> 소문자 : +32
				keys.put((char)(line[i] - 32), line[i]);
			}
			
			q.offer(new Node(0, 0));
			
			System.out.println(bfs());
		}
	}
	
	private static int bfs() {
		visited = new boolean[H + 2][W + 2];
		visited[0][0] = true;
		int doc = 0;
		boolean flag = false;
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			
			for(int d = 0 ; d < 4 ; ++d) {
				int nr = cur.r + dir[d][0];
				int nc = cur.c + dir[d][1];
				
				if(nr >= H + 2 || nr < 0 || nc >= W + 2 || nc < 0 ||
						visited[nr][nc] || map[nr][nc] == '*') continue;
				
				switch(map[nr][nc]) {
					// 빈 공간 
					case '.':
						visited[nr][nc] = true;
						q.offer(new Node(nr, nc));
						break;
					// 문서 
					case '$':
						doc++;
						// 먹은 문서 없애기  
						map[nr][nc] = '.';
						visited[nr][nc] = true;
						q.offer(new Node(nr, nc));
						break;
					// 열쇠 또는 문 
					default:
						// 열쇠 
						if(map[nr][nc] >= 'a' && map[nr][nc] <= 'z') {
							// 이미 소유한 키는 신경쓰지 않는다. 
							if(!keys.containsKey(map[nr][nc])) {
								// 키 습득 
								keys.put((char)(map[nr][nc] - 32), map[nr][nc]);
								// 다시 시작 
								flag = true;
							}
							// 먹은 열쇠 없애기 
							map[nr][nc] = '.';
							visited[nr][nc] = true;
							q.offer(new Node(nr, nc));
							
						// 문 
						} else {
							if(keys.containsKey(map[nr][nc])) {
								// 한번 통과한 문은 없애기 
								map[nr][nc] = '.';
								visited[nr][nc] = true;
								q.offer(new Node(nr, nc));
							}
						}
				}
			}
		}
		if(flag) {
			q.offer(new Node(0, 0));
			doc += bfs();
		}
		
		return doc;
	}


	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	private static void print() {
		for(int i = 0 ; i < map.length ; ++i) {
			for(int j = 0 ; j < map[i].length ; ++j) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
}
