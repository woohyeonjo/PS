package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class S2383_모의점심식사시간 {
	
	static class Stair {
		int r, c, k;
		
		Stair(int r, int c, int k){
			this.r = r;
			this.c = c;
			this.k = k;
		}
	}
	
	static class Person {
		int r, c;
		int stair;
		int arriveTime;
		int stairTime;
		
		Person(int r, int c){
			this.r = r;
			this.c = c;
		}
		
		private void calArriveTime() {
			this.arriveTime = Math.abs(r - stairs[stair].r) + Math.abs(c - stairs[stair].c);
		}
	}
	
	static Queue<Person>[] qs;
	static ArrayList<Person> persons;
	static boolean[] visited;
	static Stair[] stairs;
	static int N, T, ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		for(int t = 1 ; t <= T ; ++t) {
			
			N = Integer.parseInt(br.readLine());
			
			persons = new ArrayList<>();
			qs = new LinkedList[2];
			qs[0] = new LinkedList<>();
			qs[1] = new LinkedList<>();
			stairs = new Stair[2];
			ans = Integer.MAX_VALUE;
			
			int stairIdx = 0;
			
			for(int r = 1 ; r <= N ; ++r) {
				st = new StringTokenizer(br.readLine());
				for(int c = 1 ; c <= N ; ++c) {
					int num = Integer.parseInt(st.nextToken()); 
					
					if(num == 0) {
						continue;
					}
					else if(num == 1) {
						persons.add(new Person(r, c));
					}
					else {
						stairs[stairIdx] = new Stair(r, c, num);
						stairIdx++;
					}
				}
			}
			
			
			go(0);
			
			System.out.println("#" + t + " " + ans);
		}
	}

	private static void go(int idx) {
		
		if(idx == persons.size()) {
			visited = new boolean[persons.size()];

			int cur = simulation();
			
			ans = ans > cur ? cur : ans;
			return;
		}
		
		// 첫번째 계단 이용하기 
		persons.get(idx).stair = 0;
		persons.get(idx).calArriveTime();
		go(idx + 1);
		
		// 두번째 계단 이용하기
		persons.get(idx).stair = 1;
		persons.get(idx).calArriveTime();
		go(idx + 1);
	}

	private static int simulation() {
		int cnt = 0;
		int time = 1;
		
		while(true) {
			// 내려가고
			for(Queue<Person> q : qs) {
				int size = q.size();
				
				for(int i = 0 ; i < size ; ++i) {
					Person p = q.poll();
					Stair s = stairs[p.stair];
					
					if(p.stairTime + s.k <= time) {
						continue;
					}
					
					q.offer(p);
				}
			}
			
			if(cnt == persons.size() && qs[0].isEmpty() && qs[1].isEmpty()) {
				return time;
			}
			
			// 큐에 넣고
			for(int i = 0 ; i < persons.size() ; ++i) {
				if(visited[i]) continue;
				
				Person p = persons.get(i);
				Queue<Person> q = qs[p.stair];
				
				if(p.arriveTime + 1 <= time && q.size() < 3) {
					p.stairTime = time;
					visited[i] = true;
					q.offer(p);
					cnt++;
				}
			}
			time++;
		}
	}
}
