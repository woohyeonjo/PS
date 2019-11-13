package CodingTest.Kakao;

import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
        Stack<Integer> stack = new Stack<>();
        int answer = 0;
        
        int picked = 0;
        for(int i = 0 ; i < moves.length ; ++i){
            picked = pick(board, moves[i] - 1);
            if(picked == 0) continue;
            if(boom(stack, picked)) answer += 2;
        }
        return answer;
    }
    
    private boolean boom(Stack<Integer> stack, int picked){
        if(stack.isEmpty()) {
            stack.push(picked);
        } else {
            int top = stack.peek();
            if(top == picked){
                stack.pop();
                return true;
            } else {
                stack.push(picked);
            }
        }
        return false;
    }

    private int pick(int[][] board, int col){
        int picked = 0;
        
        if(board[board.length - 1][col] == 0) return picked;
        
        for(int i = 0 ; i < board.length ; ++i){
            if(board[i][col] != 0) {
                picked = board[i][col];
                board[i][col] = 0;
                return picked;
            }
        }
        return 0;
    }
}
