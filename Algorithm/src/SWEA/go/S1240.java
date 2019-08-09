package SWEA.go;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class S1240 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		String[] codeTable = { "0001101", "0011001",
						  "0010011", "0111101",
						  "0100011", "0110001",
						  "0101111", "0111011",
						  "0110111", "0001011"};
		
		int T = sc.nextInt();
		sc.nextLine();
		
		for(int t = 1 ; t <= T ; ++t) {
			String line = "";
			String[] binaryCode = null;
			int[] code = new int[8];
			
			int N = sc.nextInt();
			int M = sc.nextInt();
			sc.nextLine();
			
			String[] arr = new String[N];
			
			// ?���? 코드 ?��줄씩 받아?�� ???��
			for(int n = 0 ; n < N ; ++n) {
				arr[n] = sc.nextLine();
			}
			
			// 코드�? ?��?��?��?�� �? 찾기
			for(int n = 0 ; n < N ; ++n) {
				line = arr[n];
				if(line.contains("1")) break;
			}
			
			String[] codeline = line.split("");
			
			// 56?���? 코드 추출
			for(int i = codeline.length - 1 ; i >= 0 ; --i) {
				if(codeline[i].equals("1")) {
					int lastIndex = i;
					binaryCode = Arrays.copyOfRange(codeline, lastIndex - 55, lastIndex + 1);
					break;
				}
			}
			
			line = String.join("", binaryCode);
			
			// 8?���? 코드 추출
			for(int i = 0 ; i < 8 ; ++i) {
				String codeSet = line.substring(0, 7);
				if(line.length() > 8) line = line.substring(7);
				
				for(int j = 0 ; j < 10 ; ++j) {
					if(codeSet.equals(codeTable[j])) code[i] = j;
				}
			}

			
			System.out.println("#" + t + " " + check(code));
		}
	}
	
	public static int check(int[] code) {
		int odd = (code[0] + code[2] + code[4] + code[6]) * 3;
		int even = code[1] + code[3] + code[5];
		int checkNum = code[7];
		
		if((odd + even + checkNum) % 10 == 0) {
			int sum = 0;
			for(int i = 0 ; i < code.length ; ++i) sum += code[i];
			return sum;
		}
		else return 0;
	}
}


