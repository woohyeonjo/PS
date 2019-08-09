package SWEA.go;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Scanner;

public class S5672 {
	static String[] newLine;
	static ArrayList<String> oldLine;
	static Deque<String> DQ;
	
	static int T, N, idx;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for(int t = 1 ; t <= T ; ++t) {
			N = sc.nextInt();
			
			newLine = new String[N];
			oldLine = new ArrayList<String>();
			DQ = new ArrayDeque<String>();
			idx = 0;
			
			String input = "";
			for(int n = 0 ; n < N ; ++n) {
				input = sc.next();
				DQ.offer(input);
				oldLine.add(input);
			}
			
			while(!DQ.isEmpty()) {
				if(DQ.size() > 1) newLine[idx] = nextBird(0, oldLine.size() - 1);
				else if(DQ.size() == 1) newLine[idx] = DQ.poll();
				idx++;
			}
			
			System.out.print("#" + t + " ");
			for(int i = 0 ; i < newLine.length ; ++i) {
				System.out.print(newLine[i]);
			}
			System.out.println();
			
		}
	}

	private static String nextBird(int sIdx, int eIdx) {
		if(sIdx >= eIdx) {
			oldLine.remove(oldLine.size() - 1);
			return DQ.poll();
		}
		
		if(oldLine.get(sIdx).compareTo(oldLine.get(eIdx)) > 0) {
			oldLine.remove(oldLine.size() - 1);
			return DQ.pollLast();
		} else if(oldLine.get(sIdx).compareTo(oldLine.get(eIdx)) < 0) {
			oldLine.remove(0);
			return DQ.pollFirst();
		} else {
			return nextBird(sIdx + 1, eIdx -1);
		}
	}
}
