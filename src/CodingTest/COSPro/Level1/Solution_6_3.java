package CodingTest.COSPro.Level1;

// 다음과 같이 import를 사용할 수 있습니다.
import java.util.*;

class Solution_6_3 {
	int answer;
	boolean[] visited;
	
	private void pick(int[] arr, int depth, int max_depth, int[] numbers) {
		if(depth == max_depth) {
			int[] copy = numbers.clone();
			Arrays.sort(copy);
			
			
			int gap = copy[max_depth - 1] - copy[0];
			answer = answer > gap ? gap : answer;
			
			return;
		}
		
		for(int i = 0 ; i < arr.length ; ++i) {
			if(!visited[i]) {
				visited[i] = true;
				numbers[depth] = arr[i];
				pick(arr, depth + 1, max_depth, numbers);
				numbers[depth] = 0;
				visited[i] = false;
			}
		}
	}
	
    public int solution(int[] arr, int K) {
    	// 여기에 코드를 작성해주세요.
    	visited = new boolean[arr.length];
    	answer = Integer.MAX_VALUE;
    	
    	pick(arr, 0, K, new int[K]);
    	
    	
        return answer;
    }




	// 아래는 테스트케이스 출력을 해보기 위한 main 메소드입니다.
    public static void main(String[] args) {
    	Solution_6_3 sol = new Solution_6_3();
    	int[] arr = {9, 11, 9, 6, 4, 19};
    	int K = 4;
    	int ret = sol.solution(arr, K);

    	// [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
    	System.out.println("solution 메소드의 반환 값은 " + ret + "입니다.");
    }
}