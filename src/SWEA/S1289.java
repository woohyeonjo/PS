package SWEA;


import java.util.Arrays;
import java.util.Scanner;

public class S1289 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		int[] memory;
		sc.nextLine();
		
		for(int t = 1 ; t <=T ; t++) {
			int count = 0;
			String line = sc.nextLine();
			String[] bits = line.split("");
			memory = new int[bits.length];

			for(int i = 0 ; i < bits.length ; i++) {
				if(i == 0) {
					Arrays.fill(memory, i, memory.length, Integer.parseInt(bits[i]));
					if(Integer.parseInt(bits[i]) == 1) count++;
				}
				else {
					if(Integer.parseInt(bits[i]) == memory[i - 1]) continue;
					else {
						Arrays.fill(memory, i, memory.length, Integer.parseInt(bits[i]));
						count++;
					}
				}	
			}
			//Arrays.fill(memory, bits.length, memory.length, 0);
				
			System.out.println("#" + t + " " + count);
		}
	}
}
