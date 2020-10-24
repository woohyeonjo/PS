package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class B17298_오큰수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		Stack<Integer> s = new Stack<>();
		
		int N = stoi(br.readLine());
		int[] arr = new int[N];
		int[] ans = new int[N];
		
		Arrays.fill(ans, -1);
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0 ; i < N ; ++i) {
			arr[i] = stoi(st.nextToken());
		}
		
		s.push(0);
		
		for(int i = 0 ; i < N ; ++i) {
			if(s.isEmpty()) s.push(i);
			
			while(!s.isEmpty() && arr[s.peek()] < arr[i]) {
				ans[s.pop()] = arr[i];
			}
			s.push(i);
		}
		
		for(int i = 0 ; i < N ; ++i) {
			 System.out.print(ans[i] + " ");
		}
	}
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
