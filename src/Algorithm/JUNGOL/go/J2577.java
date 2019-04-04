package Algorithm.JUNGOL.go;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class J2577 {
	static int[] table;
	static TreeSet<Integer> set;
	static ArrayList<Integer> cIdx;
	static int N, D, K, C, ans;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		D = sc.nextInt();
		K = sc.nextInt();
		C = sc.nextInt();
		
		table = new int[N];
		cIdx = new ArrayList<Integer>();
		set = new TreeSet<Integer>();
		ans = 0;
		
		for(int i = 0 ; i < N ; ++i) {
			table[i] = sc.nextInt();
			if(table[i] == C) cIdx.add(i);
		}
		
		int idx = N - 1;
		int idx2 = 1;
		for(int c = 0 ; c < cIdx.size() ; ++c) {
			while(K > 0) {
				if(cIdx.get(c) - idx2 < 0) {
					set.add(table[idx]);
					idx--;
					K--;
				} else {
					set.add(table[cIdx.get(c) - idx2]);
					idx2++;
					K--;
				}
			}
			System.out.println(set);
			if(set.contains(C)) ans = Math.max(ans, set.size());
			else ans = Math.max(ans, set.size() + 1);
			set.clear();
		}
		System.out.println(ans);
	}
}
