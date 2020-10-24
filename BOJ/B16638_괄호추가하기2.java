package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B16638_괄호추가하기2 {
	
	static boolean[] braket;
	static String[] input;
	static int N;
	static long ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = stoi(br.readLine());
		
		ans = Integer.MIN_VALUE;
		braket = new boolean[N];
		input = new String[N];
		
		input = br.readLine().split("");
		
		// 백트래킹으로 괄호를 넣을 수 있는 모든 위치에 괄호를 넣어본다. 
		backtracking(1);
		System.out.println(ans);
	}
	
	private static void backtracking(int idx) {
		
		if(idx >= N) {
			// 스트링 배열에 계속 연산할 것이므로 입력받은 배열을 훼손하지 않기 위해 복사한다. 
			long result = calc(copy(input));
			
			ans = result > ans ? result : ans;
			return;
		}
		
		// 두 번째 연산자 위치부터는 직전에 괄호가 없어야 괄호를 넣을 수 있기 때문에
		// 첫 번째 연산자 위치와 나머지 위치를 구분하여 수행한다.
		if(idx == 1) {
			braket[idx] = true;
			backtracking(idx + 2);
			braket[idx] = false;
			backtracking(idx + 2);
		} else {
			if(!braket[idx - 2]) {
				braket[idx] = true;
				backtracking(idx + 2);
				braket[idx] = false;
			}
			backtracking(idx + 2);
		}
	}
	
	private static long calc(char op, int num, long result) {
		switch(op) {
		case '+':
			result += num;
			break;
		case '-':
			result -= num;
			break;
		case '*':
			result *= num;
			break;
		}
		
		return result;
	}
	
	private static long calc(String[] copy) {
		// 괄호 > 곱셈 > 덧셈, 뺄셈 순으로 진행한다.
		
		long result = 0;
		copy = calcBraket(copy);
		copy = calcMulti(copy);
		result = calcPlusMinus(copy);
		
		return result;
	}

	private static long calcPlusMinus(String[] copy) {
		for(int i = 1 ; i < N ; i += 2) {
			// 앞선 연산으로 연산자 자리에 숫자가 있는 경우가 있기 때문에 다음과 같이 조건을 걸어야한다. 
			if(copy[i] != null && (copy[i].equals("+") || copy[i].equals("-"))) {
				int left = i - 1;
				int right = i + 1;
				
				while(copy[left] == null) left--;
				while(copy[right] == null) right++;
				
				copy[i] = calc(copy[i].charAt(0), stoi(copy[right]), stol(copy[left])) + "";
				copy[left] = null;
				copy[right] = null;
			}
		}
		
		// 마지막에 남아있는 숫자 하나가 연산의 결과다. 
		for(int i = 0 ; i < N ; ++i) {
			if(copy[i] != null) {
				return stol(copy[i]);
			}
		}
		
		return 0;
	}

	private static String[] calcMulti(String[] copy) {
		for(int i = 1 ; i < N ; i += 2) {
			// 연산자를 찾고 가장 가까운 피연산자를 찾는다. 
			if(copy[i] != null && copy[i].charAt(0) == '*') {
				int left = i - 1;
				int right = i + 1;
				
				while(copy[left] == null) left--;
				while(copy[right] == null) right++;
				
				copy[i] = calc('*', stoi(copy[right]), stol(copy[left])) + "";
				copy[left] = null;
				copy[right] = null;
			}
		}
		return copy;
	}

	private static String[] calcBraket(String[] copy) {
		// 연산자를 찾고 양 옆의 피연산자를 계산한다. 
		for(int i = 1 ; i < N ; i += 2) {
			if(braket[i]) {
				copy[i] = calc(copy[i].charAt(0), stoi(copy[i + 1]), stoi(copy[i - 1])) + "";
				copy[i - 1] = null;
				copy[i + 1] = null;
			}
		}
		return copy;
	}

	private static String[] copy(String[] origin) {
		String[] result = new String[N];
		
		for(int i = 0 ; i < N ; ++i) {
			result[i] = origin[i];
		}
		
		return result;
	}
	
	private static long stol(String s) {
		return Long.parseLong(s);
	}
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
