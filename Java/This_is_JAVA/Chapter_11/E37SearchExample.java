package Chapter_11;

import java.util.Arrays;

public class E37SearchExample {
	public static void main(String args[]){
		int[] scores = { 99, 97, 98 };
		Arrays.sort(scores);;
		int index = Arrays.binarySearch(scores, 99);
		System.out.println("찾은 인덱스: " + index);
		
		String[] names = { "홍길동", "박동수 ", "김민수"};
		Arrays.sort(names);
		index = Arrays.binarySearch(names, "홍길동");
		System.out.println("찾은 인덱스: " + index);
		
		E36Member m1 = new E36Member("홍길동");
		E36Member m2 = new E36Member("박동수");
		E36Member m3 = new E36Member("김민수");
		E36Member[] members = {m1, m2, m3};
		Arrays.sort(members);
		index = Arrays.binarySearch(members, m1);
		System.out.println("찾은 인덱스: " + index);
	}
}
