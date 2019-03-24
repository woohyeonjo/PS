package Algorithm.SWEA.go;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.TreeSet;

public class S1257 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		TreeSet<String> str;
		
		
		int T = Integer.parseInt(in.readLine());
		
		for(int t = 1 ; t <= T ; ++t) {
			str = new TreeSet<String>();
			int K = Integer.parseInt(in.readLine());
			String[] input = in.readLine().split("");
			
			for(int i = 1 ; i <= input.length ; ++i) {
				for(int j = 0 ;  j < input.length ; ++j) {
					if(j + i <= input.length) {
						str.add(subset(input, j, i));
					}
				}
			}
			int count = 1;
			
			for(String s : str) {
				if(count == K) {
					System.out.println("#" + t + " " + s);
					break;
				}
				count++;
			}
		}
	}
	
	private static String subset(String[] origin, int sIndex, int length) {
		String[] temp = Arrays.copyOfRange(origin, sIndex, sIndex + length);
		String result = "";
		for(int i = 0 ; i < temp.length ; ++i) {
			result += temp[i];
		}
		
		return result;
	}
}
