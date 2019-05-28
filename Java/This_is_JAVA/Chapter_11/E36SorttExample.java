package Chapter_11;

import java.util.Arrays;

public class E36SorttExample {
	public static void main(String args[]){
		int[] scores = { 99, 97, 98 };
		Arrays.sort(scores);
		for(int i = 0 ; i < scores.length ; i ++){
			System.out.println("scores[" + i + "]=" + scores[i]);
		}
		System.out.println();
		
		String[] names = { "홍길동", "박동수", "김민수" };
		Arrays.sort(names);
		for(int i = 0 ; i < names.length ; i ++){
			System.out.println("names[" + i + "]=" + names[i]);
		}
		System.out.println();
		
		E36Member m1 = new E36Member("홍길동");
		E36Member m2 = new E36Member("박동수");
		E36Member m3 = new E36Member("김민수");
		E36Member[] members = {m1, m2, m3};
		Arrays.sort(members);
		for(int i = 0 ; i < members.length ; i++){
			System.out.println("members[" + i + "].name=" + members[i].name);
		}
	}
}
