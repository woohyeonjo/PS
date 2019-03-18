package Algorithm.BOJ.go;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.LinkedHashSet;

public class B15665 {
	static StringBuilder sb = new StringBuilder();
	static LinkedHashSet<String> set = new LinkedHashSet<String>();
	static int[] inputs;
	static int N, M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		inputs = new int[N];
		
		st = new StringTokenizer(in.readLine());
		for(int i = 0 ; i < N ; ++i) {
			inputs[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(inputs);;
		permutaion(0, "");
		
		for(String s : set) {
			sb.append(s + "\n");
		}
		System.out.println(sb.toString());
	}

	private static void permutaion(int index, String str) {
		if(index == M) {
			set.add(str);
			return;
		}
		
		for(int i = 0 ; i < N ; ++i) {
			permutaion(index + 1, str + inputs[i] + " ");
		}
	}
}
