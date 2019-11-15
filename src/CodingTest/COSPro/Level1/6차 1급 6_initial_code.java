class Solution {
    public int solution(int[][] grid) {
        int answer = 0;
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++)
                for(int k = j + 1; k < 4; k += 2)
                    answer = Math.max(answer, Math.max(grid[i][j] + grid[j][k], grid[j][k] + grid[k][i]));
        return answer;
    }

    // 아래는 테스트케이스 출력을 해보기 위한 main 메소드입니다. 아래에는 잘못된 부분이 없으니 위의 코드만 수정하세요.
    public static void main(String[] args) {
    	Solution sol = new Solution();
    	int[][] grid = {{1, 4, 16, 1}, {20, 5, 15, 8}, {6, 13, 36, 14}, {20, 7, 19, 15}};
    	int ret = sol.solution(grid);

    	// [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
    	System.out.println("solution 메소드의 반환 값은 " + ret + " 입니다.");
    }
}