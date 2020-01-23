package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class B17299_오등큰수 {
	
	static int[] A, count, ans;
	static int N;
	static Stack<Integer> stack;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = stoi(br.readLine());
		A = new int[N];
		count = new int[10000000];
		ans = new int[N];
		Arrays.fill(ans, -1);
		stack = new Stack<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; ++i) {
			A[i] = stoi(st.nextToken());
			count[A[i]]++;
		}
		
		stack.push(0);
		
		for(int i = 0 ; i < N ; ++i) {
			if(stack.isEmpty()) stack.push(0);
			
			while(!stack.isEmpty() && count[A[i]] > count[A[stack.peek()]]) {
				ans[stack.pop()] = A[i];
			}
			
			stack.push(i);
		}
		
		for(int i = 0 ; i < N ; ++i) {
			 System.out.print(ans[i] + " ");
		}
		
		
	}
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
