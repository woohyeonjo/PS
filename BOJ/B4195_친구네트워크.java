package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class B4195_친구네트워크 {

	static HashMap<String, Integer> map;
	static int[] parent;
	static int[] cnt;
	static int T, F;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		for(int t = 0 ; t < T ; ++t) {
			map = new HashMap<>();
			
			// 관계 수가 최대 10만개이므로 모든 관계가 모두 다른 사람이라고 하면 최대 20만명이 존재할 수 있다.
			parent = new int[200001];
			cnt = new int[200001];
			
			for(int i = 1 ; i <= 200000 ; ++i) {
				parent[i] = i;
				cnt[i] = 1;
			}
			
			F = Integer.parseInt(br.readLine());
			
			int idx = 1;
			for(int i = 0 ; i < F ; ++i) {
				st = new StringTokenizer(br.readLine());
				String a = st.nextToken();
				String b = st.nextToken();
				
				if(!map.containsKey(a)) map.put(a, idx++);
				if(!map.containsKey(b)) map.put(b, idx++);
				
				int aIdx = map.get(a);
				int bIdx = map.get(b);
				
				union(aIdx, bIdx);
			}
		}
	}

	private static void union(int aIdx, int bIdx) {
		int aRoot = find(aIdx);
		int bRoot = find(bIdx);
		
		if(aRoot == bRoot) {
			System.out.println(cnt[aRoot]);
			return;
		}
		
		// B를 A밑으로 연결
		parent[bRoot] = aRoot;
		// B의 관계수를 A에 더 해준다.
		cnt[aRoot] += cnt[bRoot];
		System.out.println(cnt[aRoot]);
	}

	private static int find(int a) {
		if(parent[a] == a) return a;
		return parent[a] = find(parent[a]);
	}
}
