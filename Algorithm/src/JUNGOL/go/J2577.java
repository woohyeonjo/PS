package JUNGOL.go;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class J2577 {
	static int[] table;
	static int[] counts;
	static int cnt;
	static int N, D, K, C, ans;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] line = in.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		D = Integer.parseInt(line[1]);
		K = Integer.parseInt(line[2]);
		C = Integer.parseInt(line[3]);
		
		table = new int[N];
		counts = new int[D + 1];
		
		for(int i = 0 ; i < N ; ++i) {
			table[i] = Integer.parseInt(in.readLine());
		}
		
		cnt = 1;
		counts[C]++;
		for(int i = 0 ; i < K ; ++i) {
			if(counts[table[i]]++ == 0) cnt++;
		}
		ans = cnt;
		
		for(int i = K ; i < N + K ; ++i) {
			if(--counts[table[i - K]] == 0) cnt--;
			if(counts[table[i % N]]++ == 0) cnt++;
			if(cnt > ans)ans = cnt;
		}
		System.out.println(ans);
	}
}
