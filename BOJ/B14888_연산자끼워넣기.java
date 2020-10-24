package BOJ;


import java.util.Scanner;

public class B14888_연산자끼워넣기 {
	static int[] numbers;
	static int[] operator;
	static int[] selected;
	static int N, MAX, MIN;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		MAX = Integer.MIN_VALUE;
		MIN = Integer.MAX_VALUE;
		
		numbers = new int[N];
		selected = new int[N - 1];
		operator = new int[4];
		
		for(int n = 0 ; n < N ; ++n){
			numbers[n] = sc.nextInt();
		}
		
		for(int o = 0 ; o < 4 ; ++o){
			int op = sc.nextInt();
			operator[o] = op;
		}
		
		findSet(0);
		
		System.out.println(MAX);
		System.out.println(MIN);
	}
	private static void findSet(int index) {
		
		if(index == N - 1){
			cal();
			return;
		}
		
		for(int i = 0 ; i < 4 ; ++i){
			if(operator[i] > 0 ){
				operator[i]--;
				selected[index] = i;
				findSet(index + 1);
				operator[i]++;
			}
		}
	}
	
	private static void cal() {
		int result = numbers[0];
		
		for(int i = 0 ; i < N - 1 ; ++i){
			switch(selected[i]){
			case 0:
				result = result + numbers[i + 1];
				break;
			case 1:
				result = result - numbers[i + 1];
				break;
			case 2:
				result = result * numbers[i + 1];
				break;
			case 3:
				if(result < 0) result = -((-result) / numbers[i + 1]);
				else result = result / numbers[i + 1];
				break;
			}
		}
		
		MAX = result > MAX ? result : MAX;
		MIN = result < MIN ? result : MIN;
	}
}
