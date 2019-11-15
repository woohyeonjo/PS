package CodingTest.COSPro.Level1;

// 다음과 같이 import를 사용할 수 있습니다.
import java.util.*;

class Solution_6_1 {
	
	class Cell {
		int r, c;
		int day;
		
		public Cell (int r, int c, int day) {
			this.r = r;
			this.c = c;
			this.day = day;
		}
	}
	
    public int solution(int n, int[][] garden) {
        // 여기에 코드를 작성해주세요.
        int answer = 0;
        
        int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<Cell> q = new LinkedList<>();
        
        for(int r = 0 ; r < n ; ++r) {
        	for(int c = 0 ; c < n ; ++c) {
        		if(garden[r][c] == 1) {
        			q.offer(new Cell(r, c, 0));
        		}
        	}
        }
        
        while(!q.isEmpty()) {
        	Cell cell = q.poll();
        	
        	answer = cell.day > answer ? cell.day : answer;
        	
        	int nr, nc;
        	for(int i = 0 ; i < 4 ; ++i) {
        		nr = cell.r + dir[i][0];
        		nc = cell.c + dir[i][1];
        		if(nr >= 0 && nr < n && nc >= 0 && nc < n && garden[nr][nc] == 0) {
        			garden[nr][nc] = 1;
        			q.offer(new Cell(nr, nc, cell.day + 1));
        		}
        	}
        	
        }
        
        
        return answer;
    }
    
    // 아래는 테스트케이스 출력을 해보기 위한 main 메소드입니다.
    public static void main(String[] args) {
    	Solution_6_1 sol = new Solution_6_1();
        int n1 = 3;
        int[][] garden1 = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        int ret1 = sol.solution(n1, garden1);
        
        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("solution 메소드의 반환 값은 " + ret1 + " 입니다.");
        
        int n2 = 2;
        int[][] garden2 = {{1, 1}, {1, 1}};
        int ret2 = sol.solution(n2, garden2);
        
        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("solution 메소드의 반환 값은 " + ret2 + " 입니다.");
        
    }    
}