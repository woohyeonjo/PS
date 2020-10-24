package CodingTest.Kakao_winterinternship_19;

public class Solution5_1 {
	public static void main(String[] args) {
		int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
		Solution5_1 sol = new Solution5_1();
		System.out.println(sol.solution(stones, 3));
	}
	
	public int solution(int[] stones, int k) {
		int answer = Integer.MAX_VALUE;
        int len = stones.length;
        
        for(int i = 0 ; i < len - k + 1 ; ++i){
            int max = 0;
            for(int j = i ; j < i + k ; ++j){
                max = stones[j] > max ? stones[j] : max;
            }
            answer = answer > max ? max : answer;
        }

        return answer;
	}
}
