package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class S5658_모의보물상자비밀번호 {
	
	
	static int N, K, vertex;
	static String[] lock;
	static TreeSet<Integer> numbers;
	static StringTokenizer st;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = null;
		
		int T = stoi(br.readLine());
		
		for(int t = 1 ; t <= T ; ++t) {
			st = new StringTokenizer(br.readLine());
			N = stoi(st.nextToken());
			K = stoi(st.nextToken());
			
			vertex = N / 4;
			lock = new String[N + 1];
			numbers = new TreeSet<>();
			
			char[] chars = br.readLine().toCharArray();

			for(int i = 1 ; i <= N ; ++i) {
				sb = new StringBuilder();
				sb.append(chars[i - 1]);
				lock[i] = sb.toString();
			}
			
			for(int i = 0 ; i < N - 1 ; ++i) {
//				System.out.println("==== " + i + "회전 =====");
				getNumbers();
				rotate();
			}
			
			Object[] arr = numbers.toArray();
			
			
			System.out.println("#" + t + " " + arr[arr.length - K]);
		}
		
	}
	
	private static void rotate() {
		String temp = lock[N];
		
		for(int i = N ; i > 1 ; --i) {
			lock[i] = lock[i - 1];
		}
		
		lock[1] = temp;
	}
	
	private static void getNumbers() {
		sb = new StringBuilder();
		
		for(int i = 1 ; i <= N ; ++i) {
			sb.append(lock[i]); 
			if(i % vertex == 0) {
//				System.out.println(sb.toString());
				numbers.add(hexToDec(sb.toString()));
				sb = new StringBuilder();
			}
		}
	}
	
	private static int hexToDec(String hex) {
		long dec = Long.parseLong(hex, 16);
		
		return (int) dec;
	}
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
