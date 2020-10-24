package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B3568_iSharp {

	// int& a*[]&, b, c*;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		String mainType = st.nextToken();
		
		while(st.hasMoreTokens()) {
			// 1. 메인타입을 붙인다. 
			sb.append(mainType);
			
			String token = st.nextToken();
			// , 또는 ; 삭제 
			token = token.substring(0, token.length() - 1);
			
			String name = "";
			for(int i = token.length() - 1 ; i >= 0 ; --i) {
				char cur = token.charAt(i);
				if((cur >= 'a' && cur <= 'z') || (cur >= 'A' && cur <= 'Z')) {
					name = cur + name;
				} else if (cur == ']') {
					// 배열 선언일 경우 괄호를 맞춘다.
					sb.append(token.charAt(i - 1));
					sb.append(cur);
					i--;
				} else {
					// 2. 서브타입을 뒤에서 부터 하나씩 붙인다. 
					sb.append(cur);
				}
			}
			sb.append(" ");
			sb.append(name);
			sb.append(";\n");
		}
		System.out.println(sb.toString());
			
	}
}
