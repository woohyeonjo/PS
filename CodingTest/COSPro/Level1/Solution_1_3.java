package CodingTest.COSPro.Level1;

// You may use import as below.
//import java.util.*;

class Solution_1_3 {
    public int solution(String pos) {
        // Write code here.
        int answer = 0;
        int[][] dir = {{-1, -2}, {-2, -1},
        			   {-2, 1}, {-1, 2},
        			   {1, 2}, {2, 1},
        			   {2, -1}, {1, -2}};
        
        int row = -(pos.charAt(1) - 48 - 8);
        int col = pos.charAt(0) - 65;
        
        int nr = 0, nc = 0;
        for(int i = 0 ; i < 8 ; ++i) {
        	nr = row + dir[i][0];
        	nc = col + dir[i][1];
        	if(nr < 8 && nr >= 0 && nc < 8 && nc >= 0) answer++;
        }
        
        return answer;
    }

    // The following is main method to output testcase.
    public static void main(String[] args) {
    	Solution_1_3 sol = new Solution_1_3();
        String pos = "A7";
        int ret = sol.solution(pos);

        // Press Run button to receive output. 
        System.out.println("Solution: return value of the method is " + ret + " .");
    }
}