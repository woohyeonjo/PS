class Solution {
    public long solution(int oneDayPrice, int multiDay, int multiDayPrice, long n){
        if(oneDayPrice * multiDay <= multiDayPrice)
            return n * oneDayPrice;
        else
            return (n % multiDay) * multiDayPrice + (n / multiDay) * oneDayPrice;
    }
    
    // 아래는 테스트케이스 출력을 해보기 위한 코드입니다. 아래에는 잘못된 부분이 없으니, 위의 코드만 수정하세요.
    public static void main(String[] args) {        
        Solution sol = new Solution();
 
        int oneDayPrice1 = 3;
        int multiDay1 = 5;
        int multiDayPrice1 = 14;
        long n1 = 6;
        long ret1 = sol.solution(oneDayPrice1, multiDay1, multiDayPrice1, n1);

        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("solution 메소드의 반환 값은 " + ret1 + " 입니다.");

        int oneDayPrice2 = 2;
        int multiDay2 = 3;
        int multiDayPrice2 = 5;
        long n2 = 11;
        long ret2 = sol.solution(oneDayPrice2, multiDay2, multiDayPrice2, n2);

        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("solution 메소드의 반환 값은 " + ret2 + " 입니다.");
    }
}