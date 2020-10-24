package BOJ;

import java.util.Scanner;

public class B16968_차량번호판 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		char[] template = sc.next().toCharArray();
		int result = 1;
		
		for(int i = 0 ; i < template.length ; ++i) {
			if(i == 0) {
				if(template[i] == 'c') result *= 26;
				else result *= 10;
			} else {
				if(template[i] == 'c') {
					if(template[i - 1] == 'c') {
						result *= 25;
					} else {
						result *= 26;
					}
				} else {
					if(template[i - 1] == 'd') {
						result *= 9;
					} else {
						result *= 10;
					}
				}
			}
		}
		System.out.println(result);
	}
}
