package CodingTest.COSPro.Level1;

// 다음과 같이 import를 사용할 수 있습니다.
import java.util.*;

class Solution_3_3 {
    public int solution(String[] bishops) {
        // 여기에 코드를 작성해주세요.
        int answer = 0;
        boolean[][] board = new boolean[8][8];
        int[][] dir = {{1, -1}, {1, 1}, {-1, -1}, {-1, 1}};
        
        int row, col;
        for(int i = 0 ; i < bishops.length ; ++i) {
        	row = -(bishops[i].charAt(1) - 48 - 8);
        	col = bishops[i].charAt(0) - 65;
        	
        	for(int d = 0 ; d < 4 ; ++d) {
        		int nr = row, nc = col;
        		while(nr < 8 && nr >= 0 && nc < 8 && nc >= 0) {
        			board[nr][nc] = true;
        			nr += dir[d][0];
        			nc += dir[d][1];
        		}
        	}
        	
        }
        
        for(int r = 0 ; r < 8 ; ++r) {
        	for(int c = 0 ; c < 8 ; ++c) {
        		if(!board[r][c]) answer++;
        	}
        }
        
        return answer;
    }

    // 아래는 테스트케이스 출력을 해보기 위한 main 메소드입니다.
    public static void main(String[] args) {
    	Solution_3_3 sol = new Solution_3_3();
        String[] bishops1 = {new String("D5")};
        int ret1 = sol.solution(bishops1);
        
        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("solution 메소드의 반환 값은 " + ret1 + " 입니다.");

        String[] bishops2 = {new String("D5"), new String("E8"), new String("G2")};
        int ret2 = sol.solution(bishops2);
        
        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("solution 메소드의 반환 값은 " + ret2 + " 입니다.");
    }
}