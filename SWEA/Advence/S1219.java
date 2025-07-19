package SWEA;


import java.util.LinkedList;
import java.util.Scanner;

// 1�? 2�? = 2?���?

public class S1219 {
	
	static LinkedList<Integer>[] ad;
	static boolean isHave;
	
	private static void go(int from, int to) {
		
		if(isHave) return;
		if(to == 99) {
			isHave = true;
			return;
		}
		
		if(ad[to] == null) {
			return;
		}
		
		for(int i = 0 ; i < ad[to].size() ; ++i) {
			if(!isHave) {
				go(to, ad[to].get(i));
			}
		}
	}
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		
		for(int t = 1 ; t <= 10 ; ++t) {
			
			ad = new LinkedList[100];
			
			int T = Integer.parseInt(sc.next());
			int count = Integer.parseInt(sc.next());
			int from, to;
			
			for(int c = 0 ; c < count ; ++c) {
				from = Integer.parseInt(sc.next());
				to = Integer.parseInt(sc.next());
				if(ad[from] == null) ad[from] = new LinkedList<Integer>();
				ad[from].add(to);
			}
			isHave = false;
			go(0, 0);
			
			System.out.println("#" + t + " " + (isHave ? 1 : 0));
		}
	}
}
