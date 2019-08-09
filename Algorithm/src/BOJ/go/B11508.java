package BOJ.go;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class B11508 {

	static ArrayList<Integer> list = new ArrayList<Integer>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		for(int n = 0 ; n < N ; ++n) {
			list.add(sc.nextInt());
		}
		
		Collections.sort(list);
		Collections.reverse(list);
		
		int cnt = 1, ans = 0;
		for(int i = 0 ; i < list.size() ; ++i) {
			if(cnt % 3 == 0) {
				cnt++;
				continue;
			}
			ans += list.get(i);
			cnt++;
		}
		
		System.out.println(ans);
		
	}
}
