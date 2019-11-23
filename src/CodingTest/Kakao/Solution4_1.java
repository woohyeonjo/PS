package CodingTest.Kakao;

import java.util.HashMap;

public class Solution4_1 {
	public static void main(String[] args) {
		long[] room_number = { 1, 3, 4, 1, 3, 1 };

		for (long l : solution(10, room_number)) {
			System.out.print(l + " ");
		}
		System.out.println();
	}

	static HashMap<Long, Long> hm;

	public static long[] solution(long k, long[] room_number) {
		hm = new HashMap<>();
		long[] result = new long[room_number.length];
		
		for(int i = 0 ; i < room_number.length ; ++i) {
			long room = find(room_number[i]);
			hm.put(room, find(room + 1));
			result[i] = room;
		}
		
		return result;
	}

	private static long find(long selected) {
		if(!hm.containsKey(selected)) {
			return selected;
		}
		
		long assignment = find(hm.get(selected));
		hm.put(selected, assignment);
		
		return assignment;
	}
}
