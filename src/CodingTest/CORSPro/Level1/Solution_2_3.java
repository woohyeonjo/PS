package CodingTest.CORSPro.Level1;

class Solution_2_3 {
    public int func_a(int n){
        int ret = 1;
        while(n > 0){
            ret *= 10;
            n--;
        }
        return ret;
    }

    int func_b(int n){
        int ret = 0;
        while(n > 0){
            ret++;
            n /= 10;
        }
        return ret;
    }
    
    int func_c(int n){
        int ret = 0;
        while(n > 0){
            ret += n%10;
            n /= 10;
        }
        return ret;
    }
    
	public int solution(int num) {
        int nextNum = num;
        while(true){
            nextNum++;
            int length = func_b(nextNum);
            if(length % 2 != 0)
                continue;

            int divisor = func_a(length / 2);
            int front = nextNum / divisor;
            int back = nextNum % divisor;
            
            int frontSum = func_c(front);
            int backSum = func_c(back);
            if(frontSum == backSum)
                break;
        }
        return nextNum - num;
    }

    // 아래는 테스트케이스 출력을 해보기 위한 main 메소드입니다.
    public static void main(String[] args) {
    	Solution_2_3 sol = new Solution_2_3();
        int num1 = 1;
        int ret1 = sol.solution(num1);

        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("solution 메소드의 반환 값은 " + ret1 + " 입니다.");

        int num2 = 235386;
        int ret2 = sol.solution(num2);

        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("solution 메소드의 반환 값은 " + ret2 + " 입니다.");
    }
}