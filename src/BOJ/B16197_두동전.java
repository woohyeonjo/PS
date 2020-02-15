package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B16197_두동전 {
	
	static char[][] map;
	static boolean[][][] visited;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int N, M, ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		ans = Integer.MAX_VALUE;
		map = new char[N][M];
		
		int r1, r2, c1, c2;
		r1 = r2 = c1 = c2 = -1;
		for(int i = 0 ; i < N ; ++i) {
			char[] line = br.readLine().toCharArray();
			for(int j = 0 ; j < M ; ++j) {
				map[i][j] = line[j];
				if(map[i][j] == 'o') {
					if(r1 == -1) {
						r1 = i;
						c1 = j;
					} else {
						r2 = i;
						c2 = j;
					}
				}
			}
		}
		
		solve(0, r1, c1, r2, c2);
		
		if(ans == Integer.MAX_VALUE) ans = -1;
		
		System.out.println(ans);
	}
	private static void solve(int depth, int ar, int ac, int br, int bc) {
		if( depth >= ans || depth >= 10) {
			return;
		}
		
		int anr, anc, bnr, bnc;
		boolean adrop, bdrop;
		for(int d = 0 ; d < 4 ; ++d) {
			adrop = bdrop = false;
			// 첫 번째 동전 이동
			anr = ar + dir[d][0];
			anc = ac + dir[d][1];
			// 두 번째 동전 이동
			bnr = br + dir[d][0];
			bnc = bc + dir[d][1];
			
			if(anr >= N || anr < 0 || anc >= M || anc < 0) {
				adrop = true;
			}
			if(bnr >= N || bnr < 0 || bnc >= M || bnc < 0) {
				bdrop = true;
			}
			
			// 둘 다 떨어진 경우 
			if(adrop && bdrop) continue;
			// 하나 떨어진 경우
			if(adrop || bdrop) {
				ans = ans > depth + 1 ? depth + 1 : ans;
				return;
			}
			
			// 다음 위치가 벽이면 움직이지 않는다. 
			if(!adrop && map[anr][anc] == '#') {
				anr = ar;
				anc = ac;
			}
			if(!bdrop && map[bnr][bnc] == '#') {
				bnr = br;
				bnc = bc;
			}
			
			// 두 동전이 겹친 경우
			if ((anr == bnr) && (anc == bnc)) continue;
			
			// 하나도 안 떨어진 경우 
			solve(depth + 1, anr, anc, bnr, bnc);
		}
	}
}
