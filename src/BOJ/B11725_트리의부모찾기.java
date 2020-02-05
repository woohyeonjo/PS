package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// 왜 스택2개를 사용하면 더 빨라질까????

public class B11725_트리의부모찾기 {
	
	static class Edge {
		int from, to;
		
		Edge(int from, int to){
			this.from = from;
			this.to = to;
		}
	}

	static Stack<Edge> s, s2;
	static int[] tree;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = stoi(br.readLine());
		s = new Stack<>();
		s2 = new Stack<>();
		tree = new int[N + 1];
		
		for(int i = 1 ; i < N + 1 ; ++i) {
			tree[i] = -1;
		}
		
		tree[1] = 1;
		
		for(int i = 0 ; i < N - 1 ; ++i) {
			st = new StringTokenizer(br.readLine());
			int from = stoi(st.nextToken());
			int to = stoi(st.nextToken());
			
			if(tree[from] == -1 && tree[to] == -1) {
				s.push(new Edge(from, to));
				continue;
			}
			
			if(tree[from] == -1) {
				tree[from] = to;
				continue;
			}
			
			if(tree[to] == -1) {
				tree[to] = from;
				continue;
			}
		}
		while(!s.isEmpty() || !s2.isEmpty()){
			while(!s.isEmpty()) {
				Edge e = s.pop();
				
				if(tree[e.from] == -1 && tree[e.to] == -1) {
					s2.push(new Edge(e.from, e.to));
					continue;
				}
				
				if(tree[e.from] == -1) {
					tree[e.from] = e.to;
					continue;
				}
				
				if(tree[e.to] == -1) {
					tree[e.to] = e.from;
					continue;
				}
			}
			while(!s2.isEmpty()) {
				Edge e = s2.pop();
				
				if(tree[e.from] == -1 && tree[e.to] == -1) {
					s.push(new Edge(e.from, e.to));
					continue;
				}
				
				if(tree[e.from] == -1) {
					tree[e.from] = e.to;
					continue;
				}
				
				if(tree[e.to] == -1) {
					tree[e.to] = e.from;
					continue;
				}
			}
		}
		
		for(int i = 2 ; i <= N ; ++i) {
			System.out.println(tree[i]);
		}
		
	}
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
