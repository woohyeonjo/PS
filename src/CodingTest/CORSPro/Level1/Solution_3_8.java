package CodingTest.CORSPro.Level1;

class Solution_3_8 {
    public int solution(int k, int[] student) {
        int answer = 0;
        for(int i = 0; i < student.length; i++){
            student[i] -= 4*k;
            if(student[i] <= 0)
                continue;
            answer += (student[i] + k - 1) / k;
        }
        return answer;
    }
    
    // 아래는 테스트케이스 출력을 해보기 위한 main 메소드입니다. main 메소드는 잘못된 부분이 없으니, solution 메소드만 수정하세요.
    public static void main(String[] args) {
    	Solution_3_8 sol = new Solution_3_8();
        int k1 = 1;
        int[] student1 = {4, 4, 4, 4};
        int ret1 = sol.solution(k1, student1);
        
        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("solution 메소드의 반환 값은 " + ret1 + " 입니다.");
        
        int k2 = 3;
        int[] student2 = {15, 17, 19, 10, 23};
        int ret2 = sol.solution(k2, student2);
        
        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("solution 메소드의 반환 값은 " + ret2 + " 입니다.");
    }
}