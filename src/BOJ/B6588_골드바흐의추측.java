package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class B6588_골드바흐의추측 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		ArrayList<Integer> numbers = new ArrayList<>();
		boolean[] primes = null;
		
		int max = 0;
		int input = 0;

		while(true) {
			input = stoi(br.readLine());
			if(input == 0) break;
			
			max = input > max ? input : max;
			numbers.add(input);
		}
		
		primes = new boolean[max + 1];
		
		getPrimes(primes, max);
		
		OUTER: for(int i = 0 ; i < numbers.size() ; ++i) {
			int value = numbers.get(i);
			for(int j = 2 ; j < value ; ++j) {
				int lower = j;
				int upper = value - j;
				if(!primes[lower] && !primes[upper]) {
					sb.append(value + " = " + lower + " + " + upper + "\n");
					continue OUTER;
				}
			}
			sb.append("Goldbach's conjecture is wrong.\n");
		}
		System.out.println(sb.toString());
	}
	
	private static void getPrimes(boolean[] primes, int max) {
		for(int i = 2 ; i <= max ; ++i) {
			if(primes[i]) continue;
			for(int j = i + i ; j <= max ; j += i) {
				primes[j] = true;
			}
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
