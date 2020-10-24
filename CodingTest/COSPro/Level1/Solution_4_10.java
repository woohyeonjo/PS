package CodingTest.COSPro.Level1;

// 다음과 같이 import를 사용할 수 있습니다.
import java.util.*;

class Solution_4_10 {
	boolean[] primes;
	
	private void getPrimes(int limit) {
		for(int i = 2 ; i <= Math.sqrt(limit) ; ++i) {
			if(primes[i]) continue;
			for(int j = i + i ; j <= limit ; j += i) {
				primes[j] = true;
			}
		}
	}
	
    public int solution(int a, int b) {
        // 여기에 코드를 작성해주세요.
        int answer = 0;
        
        primes = new boolean[b + 1];
        
        getPrimes(b);
        
        for(int i = 0 ; i <= b ; ++i) {
        	if(!primes[i]) {
        		int power2 = i * i;
        		int power3 = i * i * i;
        		
        		if(power2 >= a && power2 <= b) answer++;
        		if(power3 >= a && power3 <= b) answer++;
        		
        	}
        }
        
        
        return answer;
    }


	// 아래는 테스트케이스 출력을 해보기 위한 main 메소드입니다.
    public static void main(String[] args){
    	Solution_4_10 sol = new Solution_4_10();
        int a = 1;
        int b = 1000000000;
        int ret = sol.solution(a, b);

        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("solution 메소드의 반환 값은 " + ret + " 입니다.");
    }
}