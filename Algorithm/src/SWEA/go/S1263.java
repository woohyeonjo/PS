package SWEA.go;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class S1263 {
	
	static int[][] adj;
	static int[] shortest;
	static boolean[] isVisited;
	static int[] cc;
	static int T, N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		Queue<Integer> queue;
		
		T = Integer.parseInt(in.readLine());
		
		for(int t = 1 ; t <= T ; ++t) {
			
			st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			adj = new int[N + 1][N + 1];
			cc = new int[N + 1];
			
			for(int i = 1 ; i <= N ; ++i) {
				for(int j = 1 ; j <= N ; ++j) {
					adj[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i = 1 ; i <= N ; ++i) {
				shortest = new int[N + 1];
				isVisited = new boolean[N + 1];
				queue = new LinkedList<Integer>();
				int level = 0;
				
				queue.offer(i);
				isVisited[i] = true;
				
				while(!queue.isEmpty()) {
					int temp = queue.poll();
					for(int j = 1 ; j <= N ; ++j) {
						if(!isVisited[j] && adj[temp][j] == 1) {
							queue.offer(j);
							shortest[j] = shortest[temp] + 1;
							isVisited[j] = true;
						}
					}
				}
				for(int j = 1 ; j <= N ; ++j) {
					cc[i] += shortest[j];
				}
			}
			Arrays.sort(cc);
			
			System.out.println("#" + t + " " + cc[1]);
			
		}
	}
}
