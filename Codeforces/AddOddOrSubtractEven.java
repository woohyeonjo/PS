package CodeForces;

import java.util.Scanner;

public class AddOddOrSubtractEven {

	static int T, start, end;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		
		for(int i = 0 ; i < T ; ++i) {
			start = sc.nextInt();
			end = sc.nextInt();
			
			int move = 0;
			int gap = 0;
			if(start == end) {
				System.out.println(move);
				continue;
			} else if(start > end) {
				gap = start - end;
				
				if(gap % 2 == 0) {
					move++;
				} else {
					move += 2;
				}
			} else {
				gap = end - start;
				
				if(gap % 2 == 0) {
					move += 2;
				} else {
					move++;
				}
			}
			
			System.out.println(move);
		}
	}
}
