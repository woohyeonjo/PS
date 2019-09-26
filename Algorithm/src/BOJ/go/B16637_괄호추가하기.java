package BOJ.go;

import java.util.Scanner;

public class B16637_괄호추가하기 {
	
	static int N;
	static String[] arr;
	static String[] copy;
	static boolean[] braket;
	static long ans;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		arr = sc.next().split("");
		braket = new boolean[arr.length];
		ans = Integer.MIN_VALUE;
				
		permu(1);
		
		System.out.println(ans);
	}

	private static void permu(int index) {
		if(index >= N){
			copy();
			for(int i = 1 ; i < copy.length - 1 ; i += 2){
				if(braket[i]){
					copy[i] = calc(copy[i], Integer.parseInt(copy[i + 1]), Integer.parseInt(copy[i - 1])) + "";
					copy[i - 1] = null;
					copy[i + 1] = null;
				}
			}
			
			long current = 0;
			String op = "+";
			for(int i = 0 ; i < copy.length ; ++i) {
				if(copy[i] == null) continue;
				switch(copy[i]){
				case "+":
				case "*":
				case "-":
					op = copy[i];
					break;
				default:
					current = calc(op, Integer.parseInt(copy[i]), current);
				}
			}
			ans = current > ans ? current : ans;
			return;
		}
		
		if(index == 1){
			braket[index] = true;
			permu(index + 2);
			braket[index] = false;
			permu(index + 2);
		} else {
			if(!braket[index - 2]) {
				braket[index] = true;
				permu(index + 2);
				braket[index] = false;
				permu(index + 2);
			} else {
				permu(index + 2);
			}
		}
	}

	private static long calc(String op, int num, long result) {
		switch(op){
			case "+": return result + num;
			case "*": return result * num;
			case "-": return result - num;
		}
		return 0;
	}
	
	private static void print(String[] Arr){
		for(int i = 0 ; i < Arr.length ; ++i){
			if(Arr[i] == null) continue;
			System.out.print(Arr[i] + " ");
		}
		System.out.println();
	}
	
	private static void copy(){
		copy = new String[arr.length];
		for(int i = 0 ; i < arr.length ; ++i){
			copy[i] = arr[i];
		}
	}
	
}
