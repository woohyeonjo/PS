package Algorithm.JUNGOL.go;

import java.util.ArrayList;
import java.util.Scanner;

public class S5986 {
	
	static ArrayList<Integer> prime;
	static int ans;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		prime = new ArrayList<Integer>();
		
		prime.add(2);
		for(int n = 2 ; n <= 999 ; ++n	) {
			for(int p = 0 ; p < prime.size() ; ++p) {
				if(n % prime.get(p) == 0) break;
				else if(p + 1 == prime.size()) prime.add(n);
			}
		}
		
		int T = sc.nextInt();
		for (int t = 1 ; t <= T ; ++t) {
			int N = sc.nextInt();
			int size = prime.size();
			int count = 0;
			
			for(int i = 0 ; i < size ; ++i) {
				int temp1 = N - prime.get(i);
				if(temp1 < 4) break;
				for(int j = i ; j < size ; ++j) {
					int temp2 = temp1 - prime.get(j);
					if(temp2 < 2) break;
					for(int k = j ; k < size ; ++k) {
						int temp3 = temp2 - prime.get(k);
						if(temp3 == 0) {
							count++;
							break;
						}
					}
				}
			}
			System.out.println("#" + t + " " + count);
		}
	}
}
