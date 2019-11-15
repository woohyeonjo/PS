package CodingTest.COSPro.Level1;

class Solution_2_7 {
    public int solution(int money) {
        int coin[] = {10, 50, 100, 500, 1000, 5000, 10000, 50000};
        int counter = 0;
        int idx = coin.length - 1;
        while (money > 0){
            counter += money / coin[idx];
            money %= coin[idx];
            idx -= 1;
        }
        return counter;
    }

    // 아래는 테스트케이스 출력을 해보기 위한 main 메소드입니다.
    public static void main(String[] args) {
    	Solution_2_7 sol = new Solution_2_7();
        int money = 2760;
        int ret = sol.solution(money);
        
        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("solution 메소드의 반환 값은 " + ret + " 입니다.");
    }
}