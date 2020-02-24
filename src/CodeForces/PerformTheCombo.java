package CodeForces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PerformTheCombo {
	
	static char[] combo;
	static int[] alphabet, count, mistake;
	static int T, N, M;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		T = stoi(br.readLine());
		for(int t = 0 ; t < T ; ++t) {
			st = new StringTokenizer(br.readLine());
			N = stoi(st.nextToken());
			M = stoi(st.nextToken());
			
			mistake = new int[N];
			count = new int[N];
			alphabet = new int[26];
			combo = br.readLine().toCharArray();
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < M ; ++i) {
				mistake[stoi(st.nextToken())]++;
			}
 
			int cnt = 1;
			for(int i = N - 1 ; i >= 0 ; --i) {
				count[i] = cnt;
				cnt += mistake[i];
			}
			
			for(int i = 0 ; i < N ; ++i) {
				alphabet[combo[i] - 'a'] += count[i];
			}
			
			for(int i = 0 ; i < 26 ; ++i) {
				sb.append(alphabet[i] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
