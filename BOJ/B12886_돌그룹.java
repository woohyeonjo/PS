package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B12886_돌그룹 {
	
	static Queue<int[]> q;
	static HashSet<String> set;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		q = new LinkedList<>();
		set = new HashSet<>();
		
		int a = stoi(st.nextToken());
		int b = stoi(st.nextToken());
		int c = stoi(st.nextToken());
		
		// 3등분 할 수 없다면 불가하다. 
		if((a + b + c) % 3 != 0) {
			System.out.println(0);
			return;
		}
		
		int[] arr = {a, b, c};
		Arrays.sort(arr);
		
		set.add("" + arr[0] + arr[1] + arr[2]);
		q.offer(arr);
		
		System.out.println(bfs());
	}
	
	private static int bfs() {
		int[] arr;
		while(!q.isEmpty()) {
			int[] now = q.poll();
			
			// 세 그룹의 돌 갯수가 같으면 중지 
			if(now[0] == now[1] && now[1] == now[2]) {
				return 1;
			}
			
			// a - b
			if(now[0] != now[1]) {
				arr = new int[3];
				arr[0] = now[0] + now[0];
				arr[1] = now[1] - now[0];
				arr[2] = now[2];
				
				if(arr[1] > 0) {
					Arrays.sort(arr);
					
					String temp = "" + arr[0] + arr[1] + arr[2];
					if(!set.contains(temp)) {
						set.add(temp);
						q.offer(arr);
					}
				}
			}
			// a - c
			if(now[0] != now[2]) {
				arr = new int[3];
				arr[0] = now[0] + now[0];
				arr[1] = now[1];
				arr[2] = now[2] - now[0];
				
				if(arr[2] > 0) {
					Arrays.sort(arr);
					
					String temp = "" + arr[0] + arr[1] + arr[2];
					if(!set.contains(temp)) {
						set.add(temp);
						q.offer(arr);
					}
				}
			}
//			// b - c
//			if(now[1] != now[2]) {
//				arr = new int[3];
//				arr[0] = now[0];
//				arr[1] = now[1] + now[1];
//				arr[2] = now[2] - now[1];
//				
//				if(arr[2] > 0) {
//					Arrays.sort(arr);
//					
//					String temp = "" + arr[0] + arr[1] + arr[2];
//					if(!set.contains(temp)) {
//						set.add(temp);
//						q.offer(arr);
//					}
//				}
//			}
		}
		return 0;
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
