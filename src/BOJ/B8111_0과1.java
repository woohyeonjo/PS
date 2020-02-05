package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class B8111_0과1 {

	static final int MAX = 20000;
	static boolean[] visited;
	static int[] parent;
	static HashMap<Integer, Character> map;
	static int T, N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < T ; ++i) {
			N = Integer.parseInt(br.readLine());
			
			if(N == 1) {
				System.out.println(1);
				continue;
			}
			
			map = new HashMap<>();
			visited = new boolean[MAX];
			parent = new int[MAX];
			
			bfs();
			// 마지막에 나누어 떨어졌기 때문에 0을 인자로 넣는다.
			print(0);
			System.out.println();
		}
	}

	private static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		
		// 가장 앞에는 항상 1이 온다. 따라서 1부터 시작한다.
		q.offer(1);
		// 1이 루트라서 부모배열을 -1로 초기화한다. 
		parent[1] = -1;
		visited[1] = true;
		// 1을 넣을때는 1을 붙였다. 
		map.put(1, '1');
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			// 뒤에 0을 붙이는 경우와 1을 붙이는 경우
			// 오버플로우 방지를 위해 mod N 연산을 계속해준다.
			// 원래 숫자에 0 또는 1을 붙인것과 mod연산 후에 0 또는 1을 붙인것과 mod연산을 하면 같다.
			int[] next = {(cur * 10) % N, (cur * 10 + 1) % N};
			for(int i = 0 ; i < 2 ; ++i) {
				// 같은 숫자가 나온적 있으면 반복할 필요가 없다. 
				if(visited[next[i]]) continue;
				
				// 새로운 나머지가 나왔을 때 어떤 수를 뒤에 붙였는지 기록한다.
				// 숫자를 char형으로 바꾸기 위해서 + '0' 해준다.
				map.put(next[i], (char)(i + '0'));
				// 후에 출력을 위한 순서를 기록하기 위해 부모를 기억한다. 
				parent[next[i]] = cur;
				// 나누어 떨어지면 종료한다. 
				if(next[i] == 0) return;
				
				visited[next[i]] = true;
				q.offer(next[i]);
			}
		}
	}
	
	// 부모를 계속 찾아가며 루트부터 말단까지 출력한다. 
	private static void print(int idx) {
		if(idx == -1) {
			return;
		}
		
		print(parent[idx]);
		System.out.print(map.get(idx));
	}
}
