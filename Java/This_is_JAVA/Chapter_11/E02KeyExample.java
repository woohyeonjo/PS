package Chapter_11;

import java.util.HashMap;

public class E02KeyExample {
	public static void main(String args[]){
		HashMap<E02Key, String> hashMap = new HashMap<E02Key, String>();
		
		hashMap.put(new E02Key(1), "홍길동");
		
		String value = hashMap.get(new E02Key(1));
		System.out.println(value);
	}
}
