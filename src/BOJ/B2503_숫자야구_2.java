package BOJ;

import java.util.ArrayList;
import java.util.Scanner;

public class B2503_숫자야구_2 {
	
	static ArrayList<Integer> answers;
	static int game;

	public static void main(String[] args) {
		answers = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		game = sc.nextInt();
		
		String num;
		for(int i = 1 ; i < 10 ; ++i) {
			for(int j = 1 ; j < 10 ; ++j) {
				if(j == i) continue;
				for(int k = 1 ; k < 10 ; ++k) {
					if(k == j || k == i) continue;
					num = "" + i + "" + j + "" + k;
					answers.add(Integer.parseInt(num));
				}
			}
		}
		
		int numbers, ball, strike;
		for(int i = 0 ; i < game ; ++i) {
			numbers = sc.nextInt();
			strike = sc.nextInt();
			ball = sc.nextInt();
			
			check(numbers, strike, ball);
		}
		System.out.println(answers.size());
	}

	private static void check(int numbers, int strike, int ball) {
		int[] arr1 = new int[3];
		int[] arr2 = new int[3];
		int s, b;
		
		arr1[0] = numbers / 100;
		arr1[1] = (numbers % 100) / 10;
		arr1[2] = numbers % 10;
		
		int temp;
		
		for(int i = answers.size() - 1 ; i >= 0 ; --i) {
			s = 0;
			b = 0;
			temp = answers.get(i);
			arr2[0] = temp / 100;
			arr2[1] = (temp % 100) / 10;
			arr2[2] = temp % 10;
			
			for(int first = 0 ; first < 3 ; ++first) {
				for(int second = 0 ; second < 3 ; ++second ) {
					if(arr1[first] == arr2[second]) {
						if(first == second) s++;
						else b++;
					}
				}
			}
			if(s != strike || b != ball) answers.remove(i);
		}
		
	}
}
