package CodingTest.Programmers;

import java.util.Arrays;

public class Solution1_190928 {
	public static void main(String[] args) {
		int ans = 0;
		int[] goods = {5, 3, 7};
		int[] boxes = {3, 7, 6};
		
		Arrays.sort(goods);
		Arrays.sort(boxes);
		
		int box = 0;
		
		for(int i = 0 ; i < goods.length ; ++i) {
			while(goods[i] > boxes[box]) box++;
			if(goods[i] <= boxes[box]) {
				ans++;
				box++;
			}
		}
		
	}
}
