package CodingTest.Kakao;

public class Solution5_1 {
	public static void main(String[] args) {
		int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
		Solution5_1 sol = new Solution5_1();
		System.out.println(sol.solution(stones, 3));
	}
	
	public int solution(int[] stones, int k) {
		int answer = 0;
		int len = stones.length;
		int min = Integer.MAX_VALUE;
	        
		for(int i = 0 ; i < len - k ; i++) {
			int max = 0;
			for(int j = i ; j < i + k ; j++) {
				max = Integer.max(max, stones[j]);
			}
			min = Integer.min(max, min);
		}
		answer = min;
	    return answer;
	}
}
