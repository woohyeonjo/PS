package BOJ.go;

import java.util.Scanner;

public class B16945_매직스퀘어변경하기 {
	
	static final int MAGIC_NUM = 15;
	static int[][] input;
	static int[] magicArray;
	static boolean[] visited;
	static int ans, current;
	
	public static void main(String[] args) {
		 Scanner sc = new Scanner(System.in);
		 
		 input = new int[3][3];
		 magicArray = new int[9];
		 visited = new boolean[10];
		 
		 ans = Integer.MAX_VALUE;
		 
		 for(int r = 0 ; r < 3 ; ++r){
			 for(int c = 0 ; c < 3 ; ++c) {
				 input[r][c] = sc.nextInt();
			 }
		 }
		 
		 go(0);
		 
		 System.out.println(ans);
	}

	private static void go(int cnt) {
		if(cnt == 9){
			if(check()){
				current = compare();
				ans = ans > current ? current : ans;
			}
			return;
		}
		
		for(int i = 1 ; i < 10 ; ++i){
			if(visited[i]) continue;
			magicArray[cnt] = i;
			visited[i] = true;
			go(cnt + 1);
			visited[i] = false;
			magicArray[cnt] = 0;
		}
	}

	private static int compare() {
		int gap = 0;
		int index = 0;
		
		for(int i = 0 ; i < 3 ; ++i) {
			for(int j = 0 ; j < 3 ; ++j) {
				if(input[i][j] != magicArray[index]){
					gap += Math.abs(input[i][j] - magicArray[index]);
				}
				index++;
			}
		}
		return gap;
	}

	private static boolean check() {
		int sum = 0;
		
		for(int i = 0 ; i < 9 ; i += 3) {
			sum = magicArray[i] + magicArray[i + 1] + magicArray[i + 2];
			if(MAGIC_NUM != sum) return false;
		}
		
		for(int i = 0 ; i < 3 ; ++i) {
			sum = magicArray[i] + magicArray[i + 3] + magicArray[i + 6];
			if(MAGIC_NUM != sum) return false;
		}
		
		sum = magicArray[0] + magicArray[4] + magicArray[8];
		if(MAGIC_NUM != sum) return false;
		
		sum = magicArray[2] + magicArray[4] + magicArray[6];
		if(MAGIC_NUM != sum) return false;
		
		return true;
	}


	
}
