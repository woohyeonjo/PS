package CodingTest.COSPro.Level1;

class Solution_2_9 {
    public boolean solution(String password) {
        int length = password.length();
        for(int i = 0; i < length - 2; ++i){
            int firstCheck = password.charAt(i + 1) - password.charAt(i);
            int secondCheck = password.charAt(i + 2) - password.charAt(i + 1);
            if(firstCheck == secondCheck && (firstCheck == 1 || firstCheck == -1))
                return false;
        }
        return true;
    }


    // 아래는 테스트케이스 출력을 해보기 위한 main 메소드입니다. main 메소드는 잘못된 부분이 없으니, solution 메소드만 수정하세요.
    public static void main(String[] args) {
    	Solution_2_9 sol = new Solution_2_9();
        String password1 = "cospro890";
        boolean ret1 = sol.solution(password1);

        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("solution 메소드의 반환 값은 " + ret1 + " 입니다.");


        String password2 = "cba323";
        boolean ret2 = sol.solution(password2);

        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("solution 메소드의 반환 값은 " + ret2+ " 입니다.");       
    }
}