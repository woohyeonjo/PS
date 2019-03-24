package Algorithm.JUNGOL.go;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class J2247 {
	
	static ArrayList<User> userList;
	static int N;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		userList = new ArrayList<User>();
		
		N = sc.nextInt();
		for(int n = 0 ; n < N ; ++n) {
			userList.add(new User(n, sc.nextInt(), sc.nextInt()));
		}
		
		Collections.sort(userList);
		
		int start = userList.get(0).start, end = userList.get(0).end;
		int et = 0, it = 0, t = 0;
		for(int n = 1 ; n < N ; ++n) {
			if(userList.get(n).start > end) {
				start = userList.get(n).start;
				t = start - end;
				it = t > it ? t : it;
				end = userList.get(n).end;
			} else if(userList.get(n).start == end) {
				end = userList.get(n).end;	
			} else {
				if(userList.get(n).end > end) {
					end = userList.get(n).end;
				}
			}
			t = end - start;
			et = t > et ? t : et;
		}
		System.out.println(et + " " + it);
	}
	
	static class User implements Comparable<User>{
		int num, start, end;
		int time;

		public User(int num, int start, int end) {
			super();
			this.num = num;
			this.start = start;
			this.end = end;
			time = end - start;
		}

		@Override
		public int compareTo(User o) {
			if(this.start == o.start) {
				return this.end - o.end;
			} else {
				return this.start - o.start;
			}
		}
	}
}
