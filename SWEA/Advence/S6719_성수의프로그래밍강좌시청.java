package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class S6719_성수의프로그래밍강좌시청 {
	
	static int T, N, K;
	static double[] lecture;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = stoi(br.readLine());
		
		for(int t = 1 ; t <= T ; ++t) {
			st = new StringTokenizer(br.readLine());
			N = stoi(st.nextToken());
			K = stoi(st.nextToken());
			
			lecture = new double[N + 1];
			st = new StringTokenizer(br.readLine());
			for(int i = 1 ; i <= N ; ++i) {
				lecture[i] = stod(st.nextToken());
			}
			
			Arrays.sort(lecture);
			
			double ans = 0;
			
			for(int i = N - K + 1 ; i <= N ; ++i) {
				ans = (ans + lecture[i]) / 2;
			}
			
			System.out.println("#" + t + " " + ans);
		}
		
	}
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	private static double stod(String s) {
		return Double.parseDouble(s);
	}
}
