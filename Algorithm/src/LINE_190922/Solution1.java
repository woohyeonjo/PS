package LINE_190922;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution1 {
	
	static int M, C, ans;
	static Queue<Integer> mq;
	static int[] consume;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		M = sc.nextInt();
		C = sc.nextInt();

		mq = new LinkedList<>();
		consume = new int[C];
		ans = 0;
		
		for(int i = 0 ; i < M ; ++i) mq.offer(sc.nextInt());
		
		while(!mq.isEmpty()) {
			for(int i = 0 ; i < C ; ++i) {
				if(consume[i] == 0) consume[i] = mq.poll();
				if(consume[i] > 0) consume[i]--;
			}
			ans++;
		}
		
		int max = 0;
		for(int i = 0 ; i < C ; ++i) {
			max = consume[i] > max ? consume[i] : max;
		}
		
		System.out.println(ans + max);
	}
}
