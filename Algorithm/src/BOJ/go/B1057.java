package BOJ.go;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B1057 {
	
	static class Player {
		int seed;
		boolean isTarget;
		
		Player(int seed) {
			this.seed = seed;
			this.isTarget = false;
		}
		
		@Override
		public String toString() {
			return this.seed + "";
		}
		
	}
	static int N, K, I, round;
	static Queue<Player> q = new LinkedList<Player>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		K = sc.nextInt();
		I = sc.nextInt();
		
		round = 1;
		
		for(int i = 1 ; i <= N ; ++i){
			Player p = new Player(i);
			if(i == K || i == I) p.isTarget = true;
			q.offer(p);
		}
		
OUTER :	while(!q.isEmpty()){
			int size = q.size();
			int pCnt = 1;
			Player left, right, temp;
			
			if(size % 2 == 0){
				for(int i = 0 ; i < size / 2 ; ++i){
					temp = new Player(pCnt);
					left = q.poll();
					right = q.poll();
					if(left.isTarget && right.isTarget) break OUTER;
					if(left.isTarget || right.isTarget) temp.isTarget = true;
					q.offer(temp);
				}
			} else {
				for(int i = 0 ; i < (size - 1) / 2 ; ++i){
					temp = new Player(pCnt);
					left = q.poll();
					right = q.poll();
					if(left.isTarget && right.isTarget) break OUTER;
					if(left.isTarget || right.isTarget) temp.isTarget = true;
					q.offer(temp);
				}
				q.offer(q.poll());
			}
			round++;
		}
		
		System.out.println(round);
	}
}
