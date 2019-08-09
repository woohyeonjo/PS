package JUNGOL.go;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class J1828 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		ArrayList<Item> r = new ArrayList<Item>();
		
		for(int n = 1; n <= N ; ++n) {
			int row = sc.nextInt() + 270;
			int high = sc.nextInt() + 270;
			
			r.add(new Item(row, high));
		}
		Collections.sort(r);
		
		int high_now = r.get(0).high;
		int count = 1;
		
		for(int i = 1; i < r.size() ; ++i) {
			if(high_now < r.get(i).row) {
				count++;
				high_now = r.get(i).high;
			}
		}
		
		System.out.println(count);
	}
	
	static class Item implements Comparable<Item>{
		int row, high;

		public Item(int row, int high) {
			super();
			this.row = row;
			this.high = high;
		}

		@Override
		public int compareTo(Item o) {
			return this.high - o.high;
		}
		
	}
}
