package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2422_한윤정이이탈리아에가서아이스크림을사먹는데 {

	static boolean[][] anti;
	static int N, M, ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		
		anti = new boolean[N + 1][N + 1];
		
		for(int i = 0 ; i < M ; ++i) {
			st = new StringTokenizer(br.readLine());
			int a = stoi(st.nextToken());
			int b = stoi(st.nextToken());
			
			anti[a][b] = anti[b][a] = true;
		}
		
		for(int i = 1 ; i <= N - 2 ; ++i) {
			for(int j = i + 1 ; j <= N - 1 ; ++j) {
				if(anti[i][j] || anti[j][i]) continue;
				for(int k = j + 1 ; k <= N ; ++k) {
					if(anti[i][k] || anti[j][k]) continue;
					ans++;
				}
			}
		}
		System.out.println(ans);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
