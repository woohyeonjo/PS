package BOJ;


import java.util.Scanner;

public class B2503_숫자야구 {
	
	static int[][] input;
	static int[] number;
	static boolean[] visited;
	static int N, S, B, ans;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();

		input = new int[N][5];
		number = new int[3];
		visited = new boolean[10];
		ans = 0;
		
		char[] num;
		for(int i = 0 ; i < N ; ++i){
			num = sc.next().toCharArray();
			input[i][0] = num[0] - '0';
			input[i][1] = num[1] - '0';
			input[i][2] = num[2] - '0';
			input[i][3] = sc.nextInt();
			input[i][4] = sc.nextInt();
		}
		
		go(0);
		System.out.println(ans);
	}

	private static void go(int index) {
		if(index == 3){
			if(check()) ans++;
			return;
		}
		
		for(int i = 1 ; i < 10 ; ++i){
			if(visited[i]) continue;
			visited[i] = true;
			number[index] = i;
			go(index + 1);
			visited[i] = false;
		}
	}

	private static boolean check() {
		int s = 0;
		int b = 0;
		
		for(int n = 0 ; n < N ; ++n){
			s = 0;
			b = 0;
			for(int i = 0 ; i < 3 ; ++i){
				for(int j = 0 ; j < 3 ; ++j){
					if(input[n][i] == number[j]){
						if(i == j)s++;
						else b++;
					}
				}
			}
			if(input[n][3] != s || input[n][4] != b) return false;
		}
		
		return true;
	}
}
