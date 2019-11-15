package CodingTest.CORSPro.Level1;

// 다음과 같이 import를 사용할 수 있습니다.
import java.util.*;

class Solution_3_4 {
    public int solution(String s1, String s2) {
        // 여기에 코드를 작성해주세요.
        int answer = s1.length() + s2.length();
        int max = 0;
        int dup = 0;
        
        for(int i = 0 ; i < s1.length() ; ++i) {
        	int left = i;
        	int right = 0;
        	while(left < s1.length()) {
        		if(s1.charAt(left) == s2.charAt(right)) {
        			left++;
        			right++;
        			dup++;
        		} else {
        			dup = 0;
        			break;
        		}
        	}
        	max = dup > max ? dup : max;
        }
        
        for(int i = 0 ; i < s2.length() ; ++i) {
        	int left = i;
        	int right = 0;
        	while(left < s2.length()) {
        		if(s2.charAt(left) == s1.charAt(right)) {
        			left++;
        			right++;
        			dup++;
        		} else {
        			dup = 0;
        			break;
        		}
        	}
        	max = dup > max ? dup : max;
        }
        
        return answer - max;
    }
    
    // 아래는 테스트케이스 출력을 해보기 위한 main 메소드입니다.
    public static void main(String[] args) {
    	Solution_3_4 sol = new Solution_3_4();
        String s1 = new String("ababc");
        String s2 = new String("abcdab");
        int ret = sol.solution(s1, s2);
        
        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("solution 메소드의 반환 값은 " + ret + " 입니다.");
    }
}