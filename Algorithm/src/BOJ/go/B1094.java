package BOJ.go;

import java.util.LinkedList;
import java.util.Scanner;

public class B1094 {

	static LinkedList<Integer> sticks = new LinkedList<Integer>();
	static int X;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		X = sc.nextInt();
		sticks.offer(64);

		int shortestStick = 0;
		int sum = 0;
		
		while(true){
			sum = sumOfLength();
			if(sum <= X ) break;
			
			shortestStick = sticks.pollLast();
			sticks.offer((int)shortestStick / 2);
			sticks.offer((int)shortestStick / 2);
			if(sum - (int)(shortestStick / 2) >= X) sticks.pollLast();
		}
		
		System.out.println(sticks.size());
	}
	
	private static int sumOfLength(){
		int result = 0;
		
		for(int stick : sticks) result += stick;
		return result;
	}
}
