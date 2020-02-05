package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2529_부등호 {
	
	static int K;
	static char[] objects;
	static boolean[] visited;
	static long min, max;
	static String min_s, max_s;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		K = Integer.parseInt(br.readLine());
		
		objects = new char[K + 1];
		visited = new boolean[10];
		// min, max 또한 Long의 최대 최소값으로 초기화 해야한다. 
		min = Long.MAX_VALUE;
		max = Long.MIN_VALUE;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1 ; i <= K ; ++i) {
			objects[i] = st.nextToken().charAt(0);
		}
		
		permutation(0, "");
		
		System.out.println(max_s);
		System.out.println(min_s);
	}
	
	private static void permutation(int depth, String number) {
		if(depth == K + 1) {
			if(check(number)) {
				// 0 ~ 9 의 수를 모두 이어붙이면 Integer의 범위를 넘어선다. 
				long num = Long.parseLong(number);
				
				if(min > num) {
					min = num;
					min_s = number;
				}
				if(max < num) {
					max = num;
					max_s = number;
				}
			}
			return;
		}
		
		for(int i = 0; i < 10 ; ++i) {
			if(!visited[i]) {
				visited[i] = true;
				permutation(depth + 1, number + i);
				visited[i] = false;
			}
		}
	}

	private static boolean check(String number) {
		// 만들어진 숫자 배열이 주어진 부등호를 만족하는지 체크한다. 
		for(int i = 1 ; i <= K ; ++i) {
			char cur = objects[i];
			int first = number.charAt(i - 1) - '0';
			int second = number.charAt(i) - '0';
			
			if(cur == '>') {
				if(!(first > second)) return false;
			} else if(cur == '<') {
				if(!(first < second)) return false;
			}
		}
		return true;
	}
}
