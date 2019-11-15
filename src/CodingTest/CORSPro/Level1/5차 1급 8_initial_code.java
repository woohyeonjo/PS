class Solution {
	public int func_a(int a, int b) {
		int mod = a % b;

		while(mod > 0) {
			a = b;
			b = mod;
			mod = a % b;
		}

		return b;
	}

	public int func_b(int n) {
		int answer = 0;

		for(int i = 1; i <= n; i++) {
			if(func_@@@(@@@))
				answer++;
		}

		return answer;
	}

	public boolean func_c(int p, int q) {
		if(p % q == 0)
			return true;
		else
			return false;
	}

	public int solution(int a, int b, int c) {
		int answer = 0;

		int gcd = func_@@@(func_@@@(@@@)@@@);
		answer = func_@@@(@@@);

		return answer;
	}

	// 아래는 테스트케이스 출력을 해보기 위한 main 메소드입니다.
	public static void main(String[] args) {
		Solution sol = new Solution();
		int a = 24;
		int b = 9;
		int c = 15;
		int ret = sol.solution(a, b, c);

		// [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
		System.out.println("solution 메소드의 반환 값은 " + ret + " 입니다.");
	}
}