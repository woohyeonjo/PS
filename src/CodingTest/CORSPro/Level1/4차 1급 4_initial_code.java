import java.util.*;
import javafx.util.*;

class Solution {
    public static final int n = 4; 

    public ArrayList<Integer> func_a(int[][] matrix) {
        ArrayList<Integer> ret = new ArrayList<Integer>();
        boolean [] exist = new boolean[n * n + 1];
        Arrays.fill(exist, false);
        for (int i = 0; i < n; i ++)
            for (int j = 0; j < n; j++)
                    exist[matrix[i][j]] = true;
        for (int i = 1; i <= n * n; i++)
            if (exist[i] == false)
                ret.add(i);
        return ret;
    }
    
    public ArrayList<Pair<Integer, Integer> > func_b(int[][] matrix) {
        ArrayList<Pair<Integer, Integer> > ret = new ArrayList<Pair<Integer, Integer> >();
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (matrix[i][j] == 0)
                    ret.add( new Pair<Integer, Integer>(i, j) );
        return ret;
    }
    
    public boolean func_c(int[][] matrix) {
        int sum = 0;
        for (int i = 1; i <= n * n; i++)
            sum += i;
        sum = sum / n;
        for (int i = 0; i < n; i++) {
            int rowSum = 0;
            int colSum = 0;
            for (int j = 0; j < n; j++) {
                rowSum += matrix[i][j];
                colSum += matrix[j][i];
            }
            if (rowSum != sum || colSum != sum)
                return false;
        }
        int mainDiagonalSum = 0;
        int skewDiagonalSum = 0;
        for (int i = 0; i < n; i++) {
            mainDiagonalSum += matrix[i][i];
            skewDiagonalSum += matrix[i][n-1-i];
        }
        if (mainDiagonalSum != sum || skewDiagonalSum != sum)
            return false;
        return true;
    }
    
    public int[] solution(int[][] matrix) {
        int[] answer = new int[6];
        int ansIdx = 0;
        ArrayList<Pair<Integer, Integer> > coords = func_@@@(@@@);
        ArrayList<Integer> nums = func_@@@(@@@);
        
        matrix[coords.get(0).getKey()][coords.get(0).getValue()] = nums.get(0);
        matrix[coords.get(1).getKey()][coords.get(1).getValue()] = nums.get(1);
        if (func_@@@(@@@)) {
            for (int i = 0; i <= 1; i++) {
                answer[ansIdx++] = coords.get(i).getKey() + 1;
                answer[ansIdx++] = coords.get(i).getValue() + 1;
                answer[ansIdx++] = nums.get(i);
            }
        }
        else {
            matrix[coords.get(0).getKey()][coords.get(0).getValue()] = nums.get(1);
            matrix[coords.get(1).getKey()][coords.get(1).getValue()] = nums.get(0);
            for (int i = 0; i <= 1; i++) {
                answer[ansIdx++] = coords.get(1-i).getKey() + 1;
                answer[ansIdx++] = coords.get(1-i).getValue() + 1;
                answer[ansIdx++] = nums.get(i);
            }
        }
        return answer;
    }
    
    // 아래는 테스트케이스 출력을 해보기 위한 main 메소드입니다.    
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] matrix = {{16,2,3,13},{5,11,10,0},{9,7,6,12},{0,14,15,1}};
        int[] ret = sol.solution(matrix);
        
        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("solution 메소드의 반환 값은 " + Arrays.toString(ret) + " 입니다.");
    }    
}