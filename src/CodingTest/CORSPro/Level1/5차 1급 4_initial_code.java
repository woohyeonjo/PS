class Solution {
    public String solution(int number) {
        String answer = "";

        int[] numberCount = new int[10];
        while(number > 0) {
            numberCount[number % 10]++;
            number /= 10;
        }

        for(int i = 0; i < 10; i++)
            if(numberCount[i] != 0)
                answer += (String.valueOf(i) + String.valueOf(numberCount[i]));
        
        return answer;
    }

    // 아래는 테스트케이스 출력을 해보기 위한 main 메소드입니다. 아래에는 잘못된 부분이 없으니, 위의 코드만 수정하세요.
    public static void main(String[] args) {
    	Solution sol = new Solution();
    	int number1 = 2433;
    	String ret1 = sol.solution(number1);

    	// [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
    	System.out.println("solution 메소드의 반환 값은 " + ret1 + " 입니다.");

    	int number2 = 662244;
    	String ret2 = sol.solution(number2);

    	// [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
    	System.out.println("solution 메소드의 반환 값은 " + ret2 + " 입니다.");
    }
}