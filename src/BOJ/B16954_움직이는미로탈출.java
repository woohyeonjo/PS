package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class B16954_움직이는미로탈출 {
	
	static class Node {
		int r, c;
		
		Node(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	
	static int[][] dir = {{0, 0}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
	static Queue<Node> q;
	static char[][] map;
	static boolean[][] visited;
	static int ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		q = new LinkedList<>();
		map = new char[8][8];
		visited = new boolean[8][8];
		ans = 0;
		
		for(int r = 0 ; r < 8 ; ++r) {
			char[] line = br.readLine().toCharArray(); 
			for(int c = 0 ; c < 8 ; ++c) {
				map[r][c] = line[c];
			}
		}
		
		bfs();
		
		System.out.println(ans);
	}

	private static void bfs() {
		q.offer(new Node(7, 0));
		
		while(!q.isEmpty()) {
			int size = q.size();
			visited = new boolean[8][8];
            
			for(int i = 0 ; i < size ; ++i) {
				Node cur = q.poll();
                
				if(map[cur.r][cur.c] == '#') continue;
                if(cur.r == 0) {
                    ans = 1;
                    return;
                }
				
				for(int d = 0 ; d < 9 ; ++d) {
					int nr = cur.r + dir[d][0];
					int nc = cur.c + dir[d][1];
					
					if(nr < 0 || nr >= 8 || nc < 0 || nc >= 8) continue;
					if(visited[nr][nc] || map[nr][nc] == '#') continue;

                    q.offer(new Node(nr, nc));
					visited[nr][nc] = true;
				}
			}
			moveWall();
		}
	}
    
	private static void moveWall() {
		for(int r = 7 ; r >= 0 ; --r) {
			for(int c = 0 ; c < 8 ; ++c) {
				if(r == 0) map[r][c] = '.';
				else map[r][c] = map[r - 1][c];
			}
		}
	}
}
