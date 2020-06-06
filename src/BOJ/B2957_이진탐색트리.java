package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

public class B2957_이진탐색트리 {
	
	static TreeMap<Integer, Integer> map;
	static int N;
	static long ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		map = new TreeMap<>();
	
		for(int i = 0 ; i < N ; ++i) {
			int x = Integer.parseInt(br.readLine());
			
			if(i == 0) {
				map.put(x, 0);
				ans = 0;
			} else {
				Integer upperKey = map.higherKey(x);
				Integer lowerKey = map.lowerKey(x);
				
				int depth = 0;
				if(upperKey == null) {
					depth = map.get(lowerKey) + 1;
					map.put(x, depth);
				} else if(lowerKey == null) {
					depth = map.get(upperKey) + 1;
					map.put(x, depth);
				} else {
					int upper = map.get(upperKey);
					int lower = map.get(lowerKey);
					
					depth = upper > lower ? upper + 1 : lower + 1;
					map.put(x, depth);
				}
				ans += depth;
			}
			sb.append(ans + "\n");
		}
		
		System.out.println(sb);
	}
}
