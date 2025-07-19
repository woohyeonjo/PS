package SWEA;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 2천만�?
public class S4408 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int t = 1 ; t <= T ; ++t) {
			st = new StringTokenizer(in.readLine());
			int N = Integer.parseInt(st.nextToken());
			int[] corridor = new int[405];
			
			for(int n = 1 ; n <= N ; ++n) {
				st = new StringTokenizer(in.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				
				if(start > end) {
					int temp = end;
					end = start;
					start = temp;
				}
				
				if(start % 2 == 0) corridor[start - 1]++;
				for(int i = start; i <= end ; ++i) {
					corridor[i]++;
				}
			}
			
			int result = 0;
			for(int i = 0 ; i < 401 ; ++i) {
				if(corridor[i] > result) result = corridor[i];
			}
			
			System.out.println("#" + t + " " + result);
			
		}
		
	}
}
