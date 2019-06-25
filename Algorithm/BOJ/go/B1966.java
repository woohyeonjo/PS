package B190625;

import java.util.LinkedList;
import java.util.Scanner;

public class B1966 {
	
	static LinkedList<Doc> q;
	static int T, N, M, ans;
	
	static class Doc implements Comparable<Doc> {
		int importance;
		boolean isTarget;
		
		Doc(int importance){
			this.importance = importance;
		}
		
		@Override
		public int compareTo(Doc o) {
			return this.importance - o.importance;
		}
		
		@Override
		public String toString() {
			return importance + " " + isTarget;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		
		for(int t = 1 ; t <= T ; ++t){
			ans = 0;
			N = sc.nextInt();
			M = sc.nextInt();
			q = new LinkedList<Doc>();
			
			for(int n = 0 ; n < N ; ++n)q.offer(new Doc(sc.nextInt()));
			q.get(M).isTarget = true;
			
	OUTER:	while(!q.isEmpty()){
				int peek = q.peek().importance;
				for(int i = 0 ; i < q.size() ; ++i){
					if( q.get(i).importance > peek ) {
						q.offer(q.poll());
						continue OUTER;
					}
				}
				ans++;
				if(q.poll().isTarget) break;
			}
			
			System.out.println(ans);
		}
	}
}
