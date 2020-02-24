package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class B12906_새로운하노이탑 {
	
	static class Node {
		Stack<Character>[] tower;
		
		Node(){
			this.tower = new Stack[3];
			
			for(int i = 0 ; i < 3 ; ++i) {
				this.tower[i] = new Stack<>();
			}
		}

		public String statusCode() {
			String result = "";
			
			for(char c : this.tower[0]) result += c;
			result += '/';
			for(char c : this.tower[1]) result += c;
			result += '/';
			for(char c : this.tower[2]) result += c;
			
			return result;
		}
	}
	
	static HashSet<String> set;
	static Queue<Node> q;
	static String answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		set = new HashSet<>();
		q = new LinkedList<>();
		
		int a, b, c;
		a = b = c = 0;
		
		// 데이터 입력받아 초기 상태 만들기 
		Node start = new Node();
		for(int i = 0 ; i < 3 ; ++i) {
			st = new StringTokenizer(br.readLine());
			if(st.nextToken().equals("0")) continue;
			
			for(char ch : st.nextToken().toCharArray()) {
				if(ch == 'A') a++;
				if(ch == 'B') b++;
				if(ch == 'C') c++;
				start.tower[i].push(ch);
			}
		}
		
		// 정답 상태 만들기 
		Node end = new Node();
		for(int i = 0 ; i < a ; ++i) end.tower[0].push('A');
		for(int i = 0 ; i < b ; ++i) end.tower[1].push('B');
		for(int i = 0 ; i < c ; ++i) end.tower[2].push('C');
		
		answer = end.statusCode();
		
		// BFS 들어가기 
		q.offer(start);
		set.add(start.statusCode());
		System.out.println(bfs());
	}

	private static int bfs() {
		int times = 0;
		
		while(!q.isEmpty()) {
			int size = q.size();

			for(int i = 0 ; i < size ; ++i) {
				Node now = q.poll();
				
				// 원판이 모두 제자리를 찾아갔으면 리턴 
				if(now.statusCode().equals(answer)) {
					return times;
				}
				
				// 원판을 옮길 수 있는 모든 경우 
				for(int from = 0 ; from < 2 ; ++from) {
					for(int to = 0 ; to < 3 ; ++to) {
						if(!now.tower[from].isEmpty()) {
							Node next = copy(now);
							
							next.tower[to].push(next.tower[from].pop());
							String statusCode = next.statusCode();
							if(!set.contains(statusCode)){
								set.add(statusCode);
								q.offer(next);
							}
						}
						if(!now.tower[to].isEmpty()) {
							Node next = copy(now);
							
							next.tower[from].push(next.tower[to].pop());
							String statusCode = next.statusCode();
							if(!set.contains(statusCode)){
								set.add(statusCode);
								q.offer(next);
							}
						}
						
					}
				}
			}
			times++;
		}
		
		return times;
	}
	
	private static Node copy(Node origin) {
		Node result = new Node();
		
		for(int i = 0 ; i < 3 ; ++i) {
			for(char ch : origin.tower[i]) result.tower[i].push(ch);
		}
		
		return result;
	}
	
}
