package SWEA.go;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class S1204 {
	
	static int[] countArr;
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int T = Integer.parseInt(sc.nextLine());
		
		for(int t = 1 ; t <= T ; ++t) {
			sc.nextLine();
			countArr = new int[101];
			int modeIndex = 0;
			String[] inputArr = sc.nextLine().split(" ");
			for(int i = 0 ; i < inputArr.length ; ++i) {
				countArr[Integer.parseInt(inputArr[i])]++;
			}
			
			for(int i = 0; i <= 100 ; ++i) {
				if(countArr[i] >= countArr[modeIndex]) modeIndex = i;
			}
			
			System.out.println("#" + t + " " + modeIndex);
		}
	}
}
