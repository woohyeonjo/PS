package CodingTest.Kakao_winterinternship_19;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Solution3_1 {
	
	ArrayList<Integer>[] guess;
	HashSet<Integer> id_set, result_set;
	int user_id_size, banned_id_size;
	
	
	private int solution(String[] user_ids, String[] banned_ids) {
		int answer = 0;
		
		init(user_ids, banned_ids);
		
		for(int i = 0 ; i < banned_id_size ; ++i) {
			String banned_id = banned_ids[i];
			for(int j = 0 ; j < user_id_size ; ++j) {
				boolean flag = true;
				String user_id = user_ids[j];
				
				if(banned_id.length() != user_id.length()) continue;
				
				for(int k = 0 ; k < banned_id.length() ; ++k) {
					if(banned_id.charAt(k) == '*') continue;
					if(banned_id.charAt(k) != user_id.charAt(k)) {
						flag = false;
						break;
					}
				}
				if(flag) guess[i].add(j);
			}
		}		
		
		permutation(0);
		answer = result_set.size();
		
		return answer;
	}

	private void permutation(int depth) {
		if(depth == banned_id_size) {
			addResultSet();
			return;
		}
		
		for(int i = 0 ; i < guess[depth].size() ; ++i) {
			int idx = guess[depth].get(i);
			if(!id_set.contains(idx)) {
				id_set.add(idx);
				permutation(depth + 1);
				id_set.remove(idx);
			}
		}
	}

	private void addResultSet() {
		ArrayList<Integer> result = new ArrayList<>(id_set);
		
		Collections.sort(result);
		
		int hashValue = 0;
		for(int i = 0 ; i < result.size() ; ++i) {
			hashValue += result.get(i) + 1;
			if(i != result.size() - 1) hashValue *= 10;
		}
		result_set.add(hashValue);
	}

	private void init(String[] user_id, String[] banned_id) {
		user_id_size = user_id.length;
		banned_id_size = banned_id.length;
		
		guess = new ArrayList[banned_id_size];
		for(int i = 0 ; i < banned_id_size ; ++i) guess[i] = new ArrayList<>();
		
		id_set = new HashSet<>();
		result_set = new HashSet<>();
	}
	
	public static void main(String[] args) {
		String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
		String[][] banned_id = {
				{"fr*d*", "abc1**"},
				{"*rodo", "*rodo", "******"},
				{"fr*d*", "*rodo", "******", "******"}
		};
		
		Solution3_1 sol = new Solution3_1();
		
		for(int i = 0 ; i < banned_id.length ; ++i) {
			System.out.println(i + "번째 문제");
			int answer = sol.solution(user_id, banned_id[i]);
			System.out.println(answer);
		}
	}
}

	
