package go.woohyeon;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.TreeSet;

public class B15650 {
	
	static int index;
	static int N, M;
	static TreeSet<Integer> set;
	static boolean[] selected;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		set = new TreeSet<Integer>();
		selected = new boolean[N + 1];
	
		dfs(0);
	}

	private static void dfs(int index) {
		if(set.size() == M) {
			System.out.println(set.toString());
			return;
		}
		
		for(int i = 1 ; i <= N ; ++i) {
			if(!selected[i]) {
				set.add(i);
				selected[i] = true;
				dfs(set.size());
				selected[i] = false;
				set.remove(set.size() - 1);
			}
		}
	}
}
