package Algorithm.JUNGOL.go;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class J2634 {
	
	static int[] sX;
	static int tX, tY;
	static int M, N, L, cnt;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		sX = new int[M];
		
		st = new StringTokenizer(in.readLine());
		for(int i = 0 ; i < M ; ++i) {
			sX[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(sX);
		
		cnt = 0;
		tX = 0;
		tY = 0;
		
		for(int i = 0, j = 0; i < N ; i++) {
			st = new StringTokenizer(in.readLine());
			tX = Integer.parseInt(st.nextToken());
			tY = Integer.parseInt(st.nextToken());
			
			j = Arrays.binarySearch(sX, tX);
			j = (j >= 0) ? j : Math.abs(j) - 1;
			
			if(j > 0 && tX - sX[j - 1] + tY <= L) {
				cnt++;
				continue;
			}
			if(j < M && sX[j] - tX + tY <= L) cnt++;
		}
		System.out.println(cnt);
	}
}
