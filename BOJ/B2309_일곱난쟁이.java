package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B2309_일곱난쟁이 {
	
	static int[] selected;
	static int[] dwarfs;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		selected = new int[7];
		dwarfs = new int[9];
		
		for(int i = 0 ; i < 9 ; ++i) {
			dwarfs[i] = stoi(br.readLine());
		}
		
		Arrays.sort(dwarfs);
		
		select(0, 0, 0);
	
	}
	
	private static boolean select(int cnt, int idx, int sum) {
		if(cnt == 7) {
			if(sum == 100) {
				for(int i = 0 ; i < 7 ; ++i) {
					System.out.println(selected[i]);
				}
				return true;
			} else return false;
		}
		
		for(int i = idx ; i < 9 ; ++i) {
			selected[cnt] = dwarfs[i];
			if(select(cnt + 1, i + 1, sum + dwarfs[i])) return true;
		}
		
		return false;
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
