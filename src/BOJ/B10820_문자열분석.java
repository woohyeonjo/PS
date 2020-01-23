package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B10820_문자열분석 {
	// 공백 32
	// a 97 z 122
	// A 65 Z 90
	// 0 48 9 57
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		
		while((input = br.readLine())!= null) {
			analyze(input.toCharArray());
		}
	}
	
	private static void analyze(char[] input) {
		int space = 0;
		int lower = 0;
		int upper = 0;
		int number = 0;
		
		for(int i = 0 ; i < input.length ; ++i) {
			int ascii = input[i];
			
			if(ascii == 32) space++;
			if(ascii >= 97 && ascii <= 122) lower++;
			if(ascii >= 65 && ascii <= 90) upper++;
			if(ascii >= 48 && ascii <= 57) number++;
		}
		
		System.out.println(lower + " " + upper + " " + number + " " + space);
	}
}
