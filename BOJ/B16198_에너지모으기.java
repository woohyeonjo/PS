package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B16198_에너지모으기 {
	
	static ArrayList<Integer> W;
	static int N, ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = stoi(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		ans = Integer.MIN_VALUE;
		W = new ArrayList<>();
		
		for(int i = 0 ; i < N ; ++i) {
			W.add(stoi(st.nextToken()));
		}
		
		backtracking(0);
		
		System.out.println(ans);
	}
	
	private static void backtracking(int energy) {
		if(W.size() == 2) {
			ans = energy > ans ? energy : ans;
			return;
		}
		
		for(int i = 1 ; i < W.size() - 1 ; ++i) {
			int remove = W.get(i);
			int add = W.get(i - 1) * W.get(i + 1);
			W.remove(i);
			backtracking(energy + add);
			W.add(i, remove);
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
}
