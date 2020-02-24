package BOJ;

import java.util.Scanner;

public class B2290_LCDTest {
	
	static StringBuilder lcd;
	static char[] numbers;
	static int overallV, overallH;
	static int size, length;
	
	public static void main(String[] args) {
		init();
		
		for(int i = 0 ; i < overallV ; ++i) {
			for(int j = 0 ;j < length ; ++j) {
				append(i, numbers[j]);
				lcd.append(" ");
			}
			lcd.append("\n");
		}
		
		System.out.println(lcd.toString());
	}
	
	private static void append(int height, char num) {
		switch(num) {
			case '0':
				if(height == 0) horizon(true);
				else if(height >= 1 && height <= size) vertical(true, true);
				else if(height == size + 1) horizon(false);
				else if(height > size + 1 && height <= size * 2 + 1) vertical(true, true);
				else horizon(true);
				break;
			case '1':
				if(height == 0) horizon(false);
				else if(height >= 1 && height <= size) vertical(false, true);
				else if(height == size + 1) horizon(false);
				else if(height > size + 1 && height <= size * 2 + 1) vertical(false, true);
				else horizon(false);
				break;
			case '2':
				if(height == 0) horizon(true);
				else if(height >= 1 && height <= size) vertical(false, true);
				else if(height == size + 1) horizon(true);
				else if(height > size + 1 && height <= size * 2 + 1) vertical(true, false);
				else horizon(true);
				break;
			case '3':
				if(height == 0) horizon(true);
				else if(height >= 1 && height <= size) vertical(false, true);
				else if(height == size + 1) horizon(true);
				else if(height > size + 1 && height <= size * 2 + 1) vertical(false, true);
				else horizon(true);
				break;
			case '4':
				if(height == 0) horizon(false);
				else if(height >= 1 && height <= size) vertical(true, true);
				else if(height == size + 1) horizon(true);
				else if(height > size + 1 && height <= size * 2 + 1) vertical(false, true);
				else horizon(false);
				break;
			case '5':
				if(height == 0) horizon(true);
				else if(height >= 1 && height <= size) vertical(true, false);
				else if(height == size + 1) horizon(true);
				else if(height > size + 1 && height <= size * 2 + 1) vertical(false, true);
				else horizon(true);
				break;
			case '6':
				if(height == 0) horizon(true);
				else if(height >= 1 && height <= size) vertical(true, false);
				else if(height == size + 1) horizon(true);
				else if(height > size + 1 && height <= size * 2 + 1) vertical(true, true);
				else horizon(true);
				break;
			case '7':
				if(height == 0) horizon(true);
				else if(height >= 1 && height <= size) vertical(false, true);
				else if(height == size + 1) horizon(false);
				else if(height > size + 1 && height <= size * 2 + 1) vertical(false, true);
				else horizon(false);
				break;
			case '8':
				if(height == 0) horizon(true);
				else if(height >= 1 && height <= size) vertical(true, true);
				else if(height == size + 1) horizon(true);
				else if(height > size + 1 && height <= size * 2 + 1) vertical(true, true);
				else horizon(true);
				break;
			case '9':
				if(height == 0) horizon(true);
				else if(height >= 1 && height <= size) vertical(true, true);
				else if(height == size + 1) horizon(true);
				else if(height > size + 1 && height <= size * 2 + 1) vertical(false, true);
				else horizon(true);
				break;
		}
	}
	
	private static void vertical(boolean left, boolean right) {
		if(left) {
			lcd.append("|");
		} else lcd.append(" ");
		for(int i = 0 ; i < size ; ++i) {
			lcd.append(" ");
		}
		if(right) {
			lcd.append("|");
		} else lcd.append(" ");
	}
	
	private static void horizon(boolean line) {
		if(line) {
			lcd.append(" ");
			for(int i = 0 ; i < size ; ++i) {
				lcd.append("-");
			}
			lcd.append(" ");
		} else {
			for(int i = 0 ; i < size + 2 ; ++i) {
				lcd.append(" ");
			}
		}
	}

	private static void init() {
		Scanner sc = new Scanner(System.in);
		
		size = sc.nextInt();
		numbers = sc.next().toCharArray();
		
		length = numbers.length;
		
		overallV = size * 2 + 3;
		overallH = ((size + 2) * length) + (length - 1);
		
		lcd = new StringBuilder();
	}
}
