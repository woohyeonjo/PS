package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B11655_ROT13 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		char[] line = br.readLine().toCharArray();

		for(int i = 0 ; i < line.length ; ++i) {
			int ch = line[i];
			
			// 소문자 97 ~ 122
			// 대문자 65 ~ 90
			if(ch >= 65 && ch <= 90) {
				ch += 13;
				if(ch > 90) {
					int temp = ch - 90;
					ch = 64 + temp;
				}
				sb.append((char) ch);
				continue;
			}
			
			if(ch >= 97 && ch <= 122) {
				ch += 13;
				if(ch > 122) {
					int temp = ch - 122;
					ch = 96 + temp;
				}
				sb.append((char) ch);
				continue;
			}
			
			sb.append(line[i]);
		}
		
		System.out.println(sb.toString());
	}
	
	
}
