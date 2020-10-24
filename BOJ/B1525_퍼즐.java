package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1525_퍼즐 {
	
	static HashMap<Integer, Integer> map;
	static Queue<Integer> q;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		// 2차원 퍼즐을 1차원으로 바꿔준다. 
		int start = 0;
		for(int i = 0 ; i < 3 ; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < 3 ; ++j) {
				int temp = stoi(st.nextToken());
				// 0일 때 9로 바꿔준다. 
				if(temp == 0) temp = 9;
				start = (start * 10) + temp;
			}
		}
		
		q = new LinkedList<>();
		map = new HashMap<>();

		q.offer(start);
		map.put(start, 0);
		
		System.out.println(bfs());
	}
	
	private static int bfs() {
		while(!q.isEmpty()) {
			// 큐에서 꺼낸 퍼즐의 상태를 문자열로 바꿔 9의 위치를 찾는다.
			int cur_int = q.poll();
			String cur = String.valueOf(cur_int);
			
			// 퍼즐이 맞춰진 상태일 경우 맵에서 시간을 꺼내고 리턴한다. 
			if(cur.equals("123456789")) {
				return map.get(cur_int);
			}
			
			int nine = cur.indexOf('9');
			// 현재 9의 좌표를 알아낸다. 
			// 몫 연산은 행, 나머지 연산은 열을 나타낸다.  
			int r = nine / 3;
			int c = nine % 3;
			
			for(int i = 0 ; i < 4 ; ++i) {
				int nr = r + dir[i][0];
				int nc = c + dir[i][1];
				if(nr >= 3 || nr < 0 || nc >= 3 || nc < 0) continue;
				
				StringBuilder next_str = new StringBuilder(cur);
				// r * 3 + c 는 좌표의 1차원 배열에서의 위치다.
				// 이전 9의 위치와 다음 위치의 숫자를 바꾼다. 
				char temp = next_str.charAt(nr * 3 + nc);
				next_str.setCharAt(nr * 3 + nc, '9');
				next_str.setCharAt(r * 3 + c, temp);
				
				int next = stoi(next_str.toString());
				
				// 맵에 없는(나온적 없는 퍼즐의 상태) 경우에는 맵에 추가한다. 
				if(!map.containsKey(next)){
					map.put(next, map.get(cur_int) + 1);
					q.offer(next);
				}
			}
		}
		return -1;
	}
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
