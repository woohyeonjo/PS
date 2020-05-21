import java.util.ArrayList;

public class Test {
	public static void main(String[] args) {
		Solution sol = new Solution();
		
		sol.solution(4);
		
	}
	
	static class Solution {
	    
	    static boolean[][] board;
	    static int answer;
	    
	    public int solution(int n) {
	        answer = 0;
	        board = new boolean[n][n];
	        backtracking(n, 0);
	        
	        return answer;
	    }
	    
	    private void backtracking(int max, int col){
	        if(col == max){
	            answer++;
	            return;
	        }
	        
	        for(int row = 0 ; row < max ; ++row){
	            if(board[row][col]) continue;
	            setQueen(row, col, max, true);
	            System.out.println(row + ", " + col + " 에 놓기 ");
	            
	            backtracking(max, col + 1);
	            System.out.println(row + ", " + col + " 에 제거 ");
	            setQueen(row, col, max, false);
	        }
	    }
	    
	    private void setQueen(int row, int col, int max, boolean type){
	        // 가로, 세로
	        for(int r = 0 ; r < max ; ++r) board[r][col] = type;
	        for(int c = 0 ; c < max ; ++c) board[row][c] = type;
	        
	        // 대각선
	        int[][] dir = {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
	        for(int d = 0 ; d < 4 ; ++d){
	            int nr = row, nc = col;
	            while(nr >= 0 && nr < max && nc >= 0 && nc < max){
	                board[nr][nc] = type;
	                nr += dir[d][0];
	                nc += dir[d][1];
	            }
	        }
	    }
	    
	    private void print(){
	        for(int i = 0 ; i < 4 ; ++i){
	            for(int j = 0 ; j < 4 ; ++j){
	                if(board[i][j]) System.out.print(1);
	                else System.out.print(0);
	            }
	            System.out.println();
	        }
	        System.out.println();
	    }
	}
}
