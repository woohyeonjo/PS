package SWEA;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class S1256 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		
		int T = Integer.parseInt(in.readLine());
		
		for(int t = 1 ; t <= T ; ++t) {
			int K = Integer.parseInt(in.readLine());
			String[] input = in.readLine().split("");
			str = new String[input.length];
			
			for(int i = 0 ; i < input.length ; i++ ) {
				String[] temp = Arrays.copyOfRange(input, i, input.length);
				String tempStr = "";
				for(int j = 0 ; j < temp.length ; ++j) {
					tempStr += temp[j];
				}
				str[i] = tempStr;
			}
			Arrays.sort(str);
			System.out.println("#" + t + " " + str[K - 1]);
		}
	}
}
