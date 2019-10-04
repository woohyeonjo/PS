package BOJ;


import java.util.ArrayList;
import java.util.Scanner;

public class B16987 {
	static ArrayList<Egg> eggList = new ArrayList<Egg>();
	static int N, ans = 0;
	static class Egg{
		int d, w;

		public Egg(int d, int w) {
			super();
			this.d = d;
			this.w = w;
		}
		
		@Override
		public String toString() {
			return this.d + "";
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		for(int n = 0 ; n < N ; ++n) {
			eggList.add(new Egg(sc.nextInt(), sc.nextInt()));
		}
		
		dfs(0);
		System.out.println(ans);
	}

	private static void dfs(int inHand) {
		
		if(inHand == N) {
			ans = Math.max(ans, count());
			return;
		}
		
		for(int n = 0 ; n < N ; ++n) {
			if(n == inHand || eggList.get(n).d <= 0) continue;
			eggList.get(inHand).d -= eggList.get(n).w;
			eggList.get(n).d -= eggList.get(inHand).w;
			for(int i = inHand + 1 ; i <= N; ++i) {
				if(i == N) {
					dfs(i);
					break;
				}
				if(eggList.get(i).d > 0) {
					dfs(i);
					break;
				}
			}
			eggList.get(inHand).d += eggList.get(n).w;
			eggList.get(n).d += eggList.get(inHand).w;
		}
		ans = Math.max(ans, count());
	}
	
	private static int count() {
		int cnt = 0;
		for(Egg e : eggList) if(e.d <= 0) cnt++;
		return cnt;
	}
	
	
}
