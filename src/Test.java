import java.util.*;

public class Test {
	public static void main(String[] args) {
		Solution sol = new Solution();
		
		sol.solution();
	}
}

class Solution {
	public void solution() {
		HashMap<Integer, Integer> visited = new HashMap<>();
		int[][] map = new int[101][101];
		
		
//		for(int r1 = 0 ; r1 < 100 ; ++r1) {
//			for(int c1 = 0 ; c1 < 100 ; ++c1) {
//				int hash = toHash(r1, c1, r1 + 1, c1 + 1);
//				
//				if(visited.containsKey(hash)) {
//					visited.put(hash, visited.get(hash) + 1);
//				} else {
//					visited.put(hash, 1);
//				}
//			}
//		}
		
		for(int r = 0 ; r < 100 ; ++r) {
			for(int c = 0 ; c < 100 ; ++c) {
				int hash = toHash(r, c);
				
				if(visited.containsKey(hash)) {
					visited.put(hash, visited.get(hash) + 1);
				} else {
					visited.put(hash, 1);
				}
			}
		}
		
		for(int hash : visited.keySet()) {
			System.out.println(hash + "의 해시값을 가지는 경우는 " + visited.get(hash) + "개 입니다.");
		}
		
	}
	
	private int toHash(int r1, int c1, int r2, int c2) {
		return r1 * r2 + c1 * c2;
	}
	
	private int toHash(int r, int c) {
		return r * 1000 + c;
	}
}