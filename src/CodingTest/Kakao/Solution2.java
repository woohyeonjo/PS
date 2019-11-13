package CodingTest.Kakao;

import java.util.*;

class Solution2 {
    public int[] solution(String s) {
        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<String> list = new ArrayList<>();
        
        String input = s.substring(2, s.length() - 2);
	    input = input.replace("},", " ");
        input = input.replace("{", "");
        
        StringTokenizer st = new StringTokenizer(input);
        
        while(st.hasMoreTokens()) {
            String tuple = st.nextToken(" ");
            list.add(tuple);
        }
        
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        
        for(String tuple : list) {
            String[] numbers = tuple.split(",");
            for(String number : numbers) {
                int now = Integer.parseInt(number);
                
                if(result.contains(now)) continue;
                result.add(now);
            }
        }
        
        int[] answer = new int[result.size()];

        for(int i = 0 ; i < answer.length ; ++i) {
            answer[i] = result.get(i);
        }
        
        return answer;
    }
}