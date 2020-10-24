package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B8982_수족관1 {
	
	static class Node {
		int r, c;
		
		Node(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	
	static final int MAX = 40001;
	static int[] surface, depth;
	static Node[] hole;
	static int N, K, ans, lastIdx;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = stoi(br.readLine());
		
		ans = 0;
		surface = new int[MAX];
		depth = new int[MAX];
		
		// 각 열에 대한 수족관 깊이를 입력한다.
		// 0, 0 제거 
		br.readLine();
		for(int i = 0 ; i < N / 2 - 1 ; ++i) {
			st = new StringTokenizer(br.readLine());
			int x1 = stoi(st.nextToken());
			int y1 = stoi(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			int x2 = stoi(st.nextToken());
			int y2 = stoi(st.nextToken());

			for(int j = x1 ; j <= x2 ; ++j) {
				depth[j] = y1;
			}
			
			lastIdx = x2 - 1;
		}
		// 마지막 제거 
		br.readLine();
		
		K = stoi(br.readLine());

		// 수족관 배수구 데이터 입력 
		hole = new Node[K];
		for(int i = 0 ; i < K ; ++i) {
			st = new StringTokenizer(br.readLine());
			int idx = stoi(st.nextToken());
			int dep = stoi(st.nextToken());
			hole[i] = new Node(dep, idx);
		}
		
		for(Node cur : hole) {
			int curDepth = cur.r;
			int col = cur.c;
			
			// 왼쪽으로 갱신 
			for(int i = col ; i >= 0 ; --i) {
				// 현재 깊이를 최소값으로 유지한다. 
				curDepth = curDepth > depth[i] ? depth[i] : curDepth;
				surface[i] = curDepth > surface[i] ? curDepth : surface[i];
			}
			
			curDepth = cur.r;
			col = cur.c;
			
			// 오른쪽으로 갱신 
			for(int i = col ; i <= lastIdx ; ++i) {
				// 현재 깊이를 최소값으로 유지한다. 
				curDepth = curDepth > depth[i] ? depth[i] : curDepth;
				surface[i] = curDepth > surface[i] ? curDepth : surface[i];
			}
			
		}
		
		for(int i = 0 ; i <= lastIdx ; ++i) {
			ans += depth[i] - surface[i];
		}
		
		System.out.println(ans);
	}
	
	private static void print() {
		for(int i = 0 ; i <= lastIdx ; ++i) {
			System.out.print(depth[i] + " ");
		}
		System.out.println();
		for(int i = 0 ; i <= lastIdx ; ++i) {
			System.out.print(surface[i] + " ");
		}
		System.out.println();
		System.out.println();
	}
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
