package CodingTest.CORSPro.Level1;

// You may use import as below.
import java.util.*;

public class Solution_1_1 {
    public long solution(long num) {
        // Write code here.
        long answer = 0;
        String textTypeNumber = num + 1 + "";
        
        textTypeNumber = textTypeNumber.replace("0","1");
        answer = Long.parseLong(textTypeNumber);
        
        return answer;
    }

    // The following is main method to output testcase.
    public static void main(String[] args) {
    	Solution_1_1 sol = new Solution_1_1();
        long num = 90101010;
        long ret = sol.solution(num);

        // Press Run button to receive output. 
        System.out.println("Solution: return value of the method is " + ret + " .");
    }
}