package SWEA;


import java.util.ArrayList;
import java.util.Scanner;

public class S1228 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for(int t = 1 ; t <= 10 ; ++t) {
			int length = sc.nextInt();
			ArrayList<Integer> paper = new ArrayList<Integer>();
			
			for(int i = 0 ; i < length ; ++i) {
				paper.add(sc.nextInt());
			}
			
			int commandCnt = sc.nextInt();
			for(int i = 0 ; i < commandCnt ; ++i) {
				String command = sc.next();
				int x = sc.nextInt();
				int y = sc.nextInt();
				for(int j = 0 ; j < y ; ++j) {
					paper.add(x++, sc.nextInt());
				}
			}
			
			System.out.print("#" + t + " ");
			for(int i = 0 ; i < 10 ; ++i) {
				System.out.print(paper.get(i) + " ");
			}
			System.out.println();
		}
	}
}

