package CodingTest.COSPro.Level1;

// 다음과 같이 import를 사용할 수 있습니다.
import java.util.*;

class Solution_6_2 {
    public int solution(int K, String[] words) {
        // 여기에 코드를 작성해주세요.
        int answer = 1;
        
        int line = words[0].length();
        for(int i = 1 ; i < words.length ; ++i) {
        	if(line + words[i].length() + 1 > K) {
        		line = words[i].length();	
        		answer++;
        	} else if (line + words[i].length() + 1 == K) {
        		line = 0;
        		answer++;
        	} else {
        		line += words[i].length() + 1;
        	}
        }
        return answer;
    }

    // 아래는 테스트케이스 출력을 해보기 위한 main 메소드입니다.
    public static void main(String[] args) {
    	Solution_6_2 sol = new Solution_6_2();
        int K = 10;
        String[] words = {new String("nice"), new String("happy"), new String("hello"), new String("world"), new String("hi")};
        int ret = sol.solution(K, words);

        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("solution 메소의 반환 값은 " + ret + " 입니다.");
    }
}