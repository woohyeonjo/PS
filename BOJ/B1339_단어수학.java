package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class B1339_단어수학 {
	static char[][] input;
	static HashSet<Character> set;
	static HashMap<Character, Integer> map;
	static ArrayList<Character> alphabet;
	static boolean[] selected;

	static int N, max;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		max = Integer.MIN_VALUE;
		set = new HashSet<>();
		map = new HashMap<>();
		input = new char[N][];
		
		for(int i = 0 ; i < N ; ++i) {
			char[] line = br.readLine().toCharArray();
			// 원래 알파벳을 기억한다. 
			input[i] = line;
			// 주어진 알파벳을 set에 넣는다.(중복제거) 
			for(char c : line) set.add(c);
		}
		
		alphabet = new ArrayList<>(set);
		
		selected = new boolean[10];
		permutation(0);
		
		System.out.println(max);
	}
	
	private static void permutation(int depth) {
		if(depth == alphabet.size()) {
			int temp = calc();
			max = temp > max ? temp : max;
			return;
		}
		
		for(int i = 0 ; i < 10 ; ++i) {
			if(!selected[i]) {
				selected[i] = true;
				// 해쉬맵으로 문자 - 숫자를 매핑한다.
				map.put(alphabet.get(depth), i);
				permutation(depth + 1);
				selected[i] = false;
			}
		}
		
	}

	// 매핑된 문자 - 숫자를 바탕으로 합계 산출하기 
	private static int calc() {
		int result = 0;
		
		for(int i = 0 ; i < N ; ++i) {
			int temp = 0;
			// String을 직접 핸들링하는 것은 부담이 크다(연산, 파싱) 
			for(char c : input[i]) {
				temp *= 10;
				temp += map.get(c);
			}
			result += temp;
		}
		
		return result;
	}
}
