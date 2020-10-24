package CodingTest.NHN;

import java.util.HashMap;
import java.util.Scanner;

public class Solution1 {
	public static void main(String[] args) {
		String answer = "Y";
		int total = 0;
		int count = 0;
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		HashMap<String, Integer> map = new HashMap<>();
		
		String input = "";
		int cnt = 1;
		for(int i = 0 ; i < N ; ++i) {
			input = sc.next();
			if(map.containsKey(input)) cnt = map.get(input) + 1;
			else cnt = 1;
			map.put(input, cnt);
		}
		
		for(Integer i : map.values()) total += i;
		
		if(total % map.size() != 0) {
			if((total + 1) % map.size() == 0) total += 1;
			else answer = "N";
		}
		
		System.out.println(answer);
		System.out.println(total);
		System.out.println(map.size());
		
	}
}
