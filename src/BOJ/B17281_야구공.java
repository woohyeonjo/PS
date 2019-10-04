package BOJ;


import java.util.Scanner;

public class B17281_야구공 {
	
	static int[][] player;
	static int[] order;
	static boolean[] visited;
	static int N, ans, current;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		player = new int[N + 1][10];
		order = new int[10];
		visited = new boolean[10];
		ans = 0;
		
		for(int r = 1 ; r <= N ; ++r){
			for(int c = 1 ; c <= 9 ; ++c){
				player[r][c] = sc.nextInt();
			}
		}
		
		setOrder(1);
		System.out.println(ans);
	}

	private static void setOrder(int index) {
		
		if(index > 9){
			current = play();
			ans = current > ans ? current : ans;
			return;
		}
		
		if(index == 4){
			order[index] = 1;
			setOrder(index + 1);
		} else {
			for(int i = 2 ; i <= 9 ; ++i){
				if(visited[i]) continue;
				visited[i] = true;
				order[index] = i;
				setOrder(index + 1);
				visited[i] = false;
			}
		}
	}

	private static int play() {
		
		int points = 0;
		int outCnt = 0;
		int inning = 1;
		int[] base = new int[4];
		int onPlayer = 1;
		
		while(true){
			if(outCnt == 3) {
				inning++;
				outCnt = 0;
				for(int i = 0 ; i < 4 ; ++i) base[i] = 0;
				continue;
			}
			if(inning > N) return points;
			
			switch(player[inning][order[onPlayer]]){
				case 1:
					run(base, 1);
					base[1]++;
					break;
				case 2:
					run(base, 2);
					base[2]++;
					break;
				case 3:
					run(base, 3);
					base[3]++;
					break;
				case 4:
					run(base, 4);
					base[0]++;
					break;
				case 0:
					outCnt++;
					break;
			}
			onPlayer = (onPlayer % 9) + 1;
			points += base[0];
			base[0] = 0;
		}
	}

	private static void run(int[] base, int step) {
		if(step == 1){
			base[0] = base[3];
			base[3] = base[2];
			base[2] = base[1];
			base[1] = 0;
		} else if(step == 2) {
			base[0] = base[3];
			base[0] += base[2];
			base[3] = base[1];
			base[1] = 0;
			base[2] = 0;
		} else if(step == 3) {
			base[0] = base[3];
			base[0] += base[2];
			base[0] += base[1];
			base[1] = 0;
			base[2] = 0;
			base[3] = 0;
		} else if(step == 4) {
			base[0] = base[3];
			base[0] += base[2];
			base[0] += base[1];
			for(int i = 1 ; i < 4 ; ++i) base[i] = 0;
		}
	}
}
