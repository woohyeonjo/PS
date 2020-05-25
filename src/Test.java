import java.util.Collections;
import java.util.PriorityQueue;

public class Test {
	public static void main(String[] args) {
		Solution sol = new Solution();
		
//		int total_sp = 121;
//		int[][] skills = {{1, 2}, {1, 3}, {3, 6}, {3, 4}, {3, 5}};
		
		System.out.println(sol.solution(4));
		
	}
	
	static class Solution {
	    public long solution(long n) {
	        long answer = 0;
	        int count = 0;
	        while (n > 0) {
	            if ((n & 0x1) == 0x1) {
	                answer += Math.pow(3, count);
	            }
	            count++;
	            n = (n >> 1);
	        }
	        return answer;
	    }
	}
}
