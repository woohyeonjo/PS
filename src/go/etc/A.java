package go.etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class A {

	static Egg[] eggList;
	static int N, ans;
	static Queue<Egg> q = new LinkedList<Egg>();

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		N = Integer.parseInt(st.nextToken());
		eggList = new Egg[N];
		ans = 0;

		for (int n = 0; n < N; ++n) {
			st = new StringTokenizer(in.readLine());
			eggList[n] = new Egg(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), false);
		}
		
		for(int n = 0 ; n < N ; ++n) {
			q.offer(eggList[n]);
			bfs();
		}
		
	}
	
	private static void bfs() {
		while(!q.isEmpty()) {
			Egg e = q.poll();
			
			for(int i = 0 ; i < q.size() ; ++i) {
				Egg temp = q.poll();
				temp.hp -= e.weight;
				if(temp.hp <= 0) {
					temp.status = true;
				}
				if(!temp.status) q.offer(temp);
			}
		}
		
	}


	static class Egg {
		int hp, weight;
		boolean status;

		public Egg(int hp, int weight, boolean status) {
			super();
			this.hp = hp;
			this.weight = weight;
			this.status = status;
		}
	}
}
