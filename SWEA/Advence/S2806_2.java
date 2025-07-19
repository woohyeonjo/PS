package SWEA;


import java.util.Scanner;

public class S2806_2 {
	
	static int size;
	static boolean[] col, slash, bSlah;
	static int count = 0;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int t = 1 ; t <= T ; ++t) {
			size = sc.nextInt();
			count = 0;
			
			col = new boolean[size + 1];
			slash = new boolean[2 * size + 1]; // / : ?��?��?��
			bSlah = new boolean[2 * size + 1]; // \ : ?��?��?��
			
			go(1);
			
			System.out.println("#" + t + " " + count);
		}
	}
	
	private static void go(int row) {
		if(row > size) {
			count++;
			return;
		}
		
		for(int i = 1 ; i <= size ; ++i) {
			// ?��, /, \ check
			if(!col[i] && !slash[row + i] && !bSlah[size + row - i]) { // ?�을 ?��?�� ?�� ?��?���?
				// ?��?�� ?��?�� ?? ?��?��
				col[i] = slash[row + i] = bSlah[size + row - i] = true;
				go(row + 1);
				col[i] = slash[row + i] = bSlah[size + row - i] = false;
				
			}
		}
	}
}
