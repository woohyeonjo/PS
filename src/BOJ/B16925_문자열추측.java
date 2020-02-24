package BOJ;

import java.util.HashSet;
import java.util.Scanner;

public class B16925_문자열추측 {
	
	static String[] origin;
	static String[] input;
	static HashSet<String> set;
	static StringBuilder sb;
	static int N;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		set = new HashSet<>();
		origin = new String[2];
		input = new String[2 * N - 2];
		
		// s, p로 이름지었지만 사실 어떤 것이 접두사인지 접미사인지 모른다.
		String s = null, p = null;
		for(int i = 0 ; i < 2 * N - 2 ; ++i) {
			input[i] = sc.next();
			if(input[i].length() == N - 1) {
				// 가장 긴 접두사와 접미사를 찾는다.
				if(s == null) {
					s = input[i];
				} else p = input[i];
			}
		}
		
		// 각각 앞에 오는 경우로 원본 문자열을 만든다. 
		origin[0] = s + p.charAt(p.length() - 1);
		origin[1] = p + s.charAt(s.length() - 1);
		
		for(String ori : origin) {
			// 각 문자열에 대한 접두사이므로 set은 반드시 비워준다.
			set.clear();
			sb = new StringBuilder();
			for(String fix : input) {
				// 0이면 접두사로 사용할 수 있다. 
				if(ori.indexOf(fix) == 0) {
					// 접두사와 접미사 양쪽에 속할 수 있는 경우를 고려한다. 
					if(!set.contains(fix)) {
						set.add(fix);
						sb.append("P");
					} else sb.append("S");
				} else {
					// 마지막 문자 비교로 접미사인지 확인한다. 
					if(fix.charAt(fix.length() - 1) == ori.charAt(ori.length() - 1)) {
						sb.append("S");
					}
				}
			}
			// 빌더의 길이가 접두사, 접미사의 총 개수와 같아야한다.
			if(sb.length() == 2 * N - 2) {
				System.out.println(ori);
				System.out.println(sb.toString());
				return;
			}
		}
		System.out.println(origin[1]);
		System.out.println(sb.toString());
	}
}
