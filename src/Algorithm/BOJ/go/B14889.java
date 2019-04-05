package Algorithm.BOJ.go;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class B14889 {
	static int[][] map;
	static ArrayList<Integer> start;
	static ArrayList<Integer> link;
	static int gap;
	static int N, ans;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String[] line;
		N = Integer.parseInt(in.readLine());
		map = new int[N][N];
		start = new ArrayList<Integer>();
		link = new ArrayList<Integer>();
		gap = 0; ans = Integer.MAX_VALUE;
		
		for(int r = 0; r < N ; ++r) {
			line = in.readLine().split(" ");
			for(int c = 0 ; c < N ; ++c) {
				map[r][c] = Integer.parseInt(line[c]);
			}
		}
		
		dfs(0);
		
		System.out.println(ans);
	}
	
	private static void dfs(int player) {
		
		if(player == N / 2) {
			gap = calc();
			ans = ans > gap ? gap : ans;
			return;
		}
		
		for(int i = 1; i < N ; ++i) {
			start.add(i);
			for(int j = 1 ; j < N ; ++j) {
				if(start.contains(j)) continue;
				link.add(j);
				dfs(player + 1);
				link.remove(player);
			}
			start.remove(player);
		}
	}

	private static int calc() {
		int startSum = 0;
		int linkSum = 0;
		
		for(int i = 0 ; i < N / 2 ; ++i) {
			for(int j = i + 1 ; j < N / 2 ; ++j) {
				startSum += map[start.get(i)][start.get(j)] + map[start.get(j)][start.get(i)];
				linkSum += map[link.get(i)][link.get(j)] + map[link.get(j)][link.get(i)];
			}
		}
		return Math.abs(startSum - linkSum);
	}
}
