package CodingTest.ESTsoft_191001;

public class Solution1 {
	public static void main(String[] args) {
		String S = "a";
		String S2 = "aaaaaa";
		String S3 = "baaabbabbb";
		
		char[] line = S2.toCharArray();
		
		
		int ans = -1, size = 0, dup = 0;
		int start = 0, end = 0;
		char now, before = line[0];
		for(int i = 0 ; i < line.length ; ++i) {
			now = line[i];
			if(now == before) dup++;
			else dup = 1;
			
			System.out.println("before : " + before + " now : " + now + " dup : " + dup);
			if(dup == 3) {
				System.out.println(start + " " + end);
				size = end - start;
				ans = size > ans ? size : ans;
				
				dup = 2;
				start = end - 1;
			}
			end++;
			before = now;
		}
		
		if(ans == -1) ans = line.length;
		System.out.println(ans);
		
	}
}
