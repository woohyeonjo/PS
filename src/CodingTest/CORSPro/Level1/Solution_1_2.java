package CodingTest.CORSPro.Level1;

// You may use import as below.
//import java.util.*;

class Solution_1_2 {
    public int solution(int n) {
        // Write code here.
        int answer = 0;
        int num = 1, max = n * n, d = 0;
        int[][] arr = new int[n][n];
        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        
        int r = 0, c = -1;
        int nr = 0, nc = 0;
        while(num <= max) {
        	nr = r + dir[d][0];
        	nc = c + dir[d][1];
        	if(nr < n && nr >= 0 && nc < n && nc >= 0 && arr[nr][nc] == 0) {
        		r = nr;
        		c = nc;
        		arr[r][c] = num;
        		num++;
        	} else d = (d + 1) % 4;
        }
        
        for(int i = 0 ; i < n ; ++i) answer += arr[i][i];
        
        return answer;
    }

    // The following is main method to output testcase.
    public static void main(String[] args) {
    	Solution_1_2 sol = new Solution_1_2();
        int n1 = 3;
        int ret1 = sol.solution(n1);

        
        // Press Run button to receive output. 
        System.out.println("Solution: return value of the method is " + ret1 + " .");
        
        int n2 = 2;
        int ret2 = sol.solution(n2);
        
        // Press Run button to receive output. 
        System.out.println("Solution: return value of the method is " + ret2 + " .");
    }
}