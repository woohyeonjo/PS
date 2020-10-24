package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

public class B11656_접미사배열 {
	
	static TreeSet<String> set;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String line = br.readLine();
		int length = line.length();
		set = new TreeSet<>();
		
		for(int i = 0 ; i < length ; ++i) {
			set.add(line.substring(i, length));
		}
		
		for(String s : set) {
			System.out.println(s);
		}
	}
}
