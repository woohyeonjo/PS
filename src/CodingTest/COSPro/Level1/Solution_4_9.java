package CodingTest.COSPro.Level1;

// 다음과 같이 import를 사용할 수 있습니다.
import java.util.*;

class Solution_4_9 {
    public String solution(int hour, int minute) {
        // 여기에 코드를 작성해주세요.
        String answer = "";
        double hour_angle = (hour * 30 + minute * 0.5) % 360;
        double minute_angle = (minute * 6) % 360;
        
        if(hour_angle > minute_angle) {
        	answer = hour_angle - minute_angle + "";
        } else {
        	answer = minute_angle - hour_angle + "";
        }
        
        
        return answer;
    }

    // 아래는 테스트케이스 출력을 해보기 위한 main 메소드입니다.
    public static void main(String[] args) {
    	Solution_4_9 sol = new Solution_4_9();
        int hour = 3;
        int minute = 0;
        String ret = sol.solution(hour, minute);

        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("solution 메소드의 반환 값은 " + ret + " 입니다.");
    }
}