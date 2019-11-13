package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B1786_찾기_라빈카프 {
	
	static final int MOD = 100000007;
	static final int D = 27;
	
	static String TEXT, PATTERN;
	static int hash_target, hash_now;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		TEXT = br.readLine();
		PATTERN = br.readLine();
		
		hash_target = toHash(PATTERN);
		System.out.println(hash_target);
		
	}
	
	private static int toHash(int index, int before) {
		int hashCode = 0;
		
//		if(index == 0) {
//			for(int i = index ; i < index + PATTERN.length() ; ++i) {
//				hashCode = mod(hashCode + mod(TEXT[i] * pow()))
//			}
//		} else {
//			hashCode = D * before + book[index + temp.length - 1] - (book[index - 1] * firstHashValue);
//			hashCode = mod(hashCode);
//		}
		
		return hashCode;
	}

	private static int toHash(String P) {
		int hashCode = 0;
		int l = P.length();
		char[] p = P.toCharArray();
		
		for(int i = 0 ; i < l ; ++i) {
			hashCode = mod(hashCode + mod(p[i] * pow(i + 1)));
		}
		
		return hashCode;
	}
	
	private static int pow(int value) {
		int result = 1;
		
		for(int i = 0 ; i < value ; ++i) result = mod(result * D);
		
		return result;
	}
	
	private static int mod(int value) {
		if(value > 0) return value % MOD;
		else return value + MOD;
	}
}
