package BOJ;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B3197_백조의호수 {
	
	static class Node {
		int r, c;
		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static Queue<Node> q;
	static Queue<Node> waterQ;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static char[][] map;
	static boolean[][] visited;
	static Node[] swan;
	static int R, C;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		waterQ = new LinkedList<>();
		q = new LinkedList<>();
		swan = new Node[2];
		map = new char[R][C];
		visited = new boolean[R][C];
		
		// 데이터 입력
		int swanIdx = 0;
		for(int r = 0 ; r < R ; ++r) {
			char[] line = br.readLine().toCharArray();
			for(int c = 0 ; c < C ; ++c) {
				map[r][c] = line[c];
				if(map[r][c] == 'L') {
					swan[swanIdx++] = new Node(r, c);
				}
				if(map[r][c] != 'X') {
					waterQ.offer(new Node(r, c));
				}
			}
		}
		
		// 출발점이 되는 백조 
		q.offer(swan[0]);
		visited[swan[0].r][swan[0].c] = true;
		
		int day = 0;
		boolean meet = false;
		
		while(true) {
			Queue<Node> nextQ = new LinkedList<>();
			while(!q.isEmpty()) {
				Node now = q.poll();
				
				// 백조를 만날시 종료 
				if(now.r == swan[1].r && now.c == swan[1].c) {
					meet = true;
					break;
				}
				
				for(int d = 0 ; d < 4 ; ++d) {
					int nr = now.r + dir[d][0];
					int nc = now.c + dir[d][1];
					
					if(nr >= R || nr < 0 || nc >= C || nc < 0 || visited[nr][nc]) continue;
					
					visited[nr][nc] = true;
					
					// 물에 인접한 얼음으로 다음 날 백조가 탐색할 지역 
					if(map[nr][nc] == 'X') {
						nextQ.offer(new Node(nr, nc));
						continue;
					}
					// 현재 탐색 가능 지역
					q.offer(new Node(nr, nc));
				}
			}
			
			// 백조가 만났으면 종료 
			if(meet) break;
			// q를 다음날 탐색할 지역이 담긴 nextQ로 바꾼다. 
			q = nextQ;
			
			// 얼음을 녹인다.
			int size = waterQ.size();
			
			for(int i = 0 ; i < size ; ++i) {
				Node now = waterQ.poll();
				
				for(int d = 0 ; d < 4 ; ++d) {
					int nr = now.r + dir[d][0];
					int nc = now.c + dir[d][1];
					
					if(nr >= R || nr < 0 || nc >= C || nc < 0) continue;
					
					// 물에 인접한 얼음을 발견하면 녹이고 다시 큐에 넣는다. 
					if(map[nr][nc] == 'X') {
						map[nr][nc] = '.';
						waterQ.offer(new Node(nr, nc));
					}
				}
			}
			day++;
		}
		
		System.out.println(day);
	}
}
