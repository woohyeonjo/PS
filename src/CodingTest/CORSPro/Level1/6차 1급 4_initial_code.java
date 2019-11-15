class Solution {
    public int solution(int n, int mix, int k) {
        int answer = 0;

        int[] card = new int[n];
        for(int i = 0; i < n; i++)
            card[i] = i+1;

        while((mix--) != 0) {
            int[] cardA = new int[n/2];
            int[] cardB = new int[n/2];

            for(int i = 0; i < n; i++) {
                if(i < n/2)
                    cardA[i] = card[i];
                else
                    cardB[i] = card[i];
            }

            for(int i = 0; i < n; i++) {
                if(i % 2 == 0)
                    card[i] = cardA[i/2];
                else
                    card[i] = cardB[i/2];
            }
        }

        answer = card[k-1];

        return answer;
    }

    // 아래는 테스트케이스 출력을 해보기 위한 main 메소드입니다. 아래에는 잘못된 부분이 없으니 위의 코드만 수정하세요.
    public static void main(String[] args) {
    	Solution sol = new Solution();
    	int n = 6;
    	int mix = 3;
    	int k = 3;
    	int ret = sol.solution(n, mix, k);

        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
    	System.out.println("solution 메소의 반환 값은 " + ret + " 입니다.");
    }
}