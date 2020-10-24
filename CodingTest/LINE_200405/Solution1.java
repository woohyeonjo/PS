package CodingTest.LINE_200405;
import java.util.*;

class Solution1 {
    public int solution(String inputString) {
        HashMap<Character, Integer> map = new HashMap<>();
        int answer = 0;
        
        for(int i = 0 ; i < inputString.length() ; ++i){
            char ch = inputString.charAt(i);
            
            switch(ch){
                case '(':
                case '{':
                case '[':
                case '<':
                    if(map.containsKey(ch)){
                        map.put(ch, map.get(ch) + 1);
                    } else {
                        map.put(ch, 1);
                    }
                    break;
                case ')':
                    if(map.containsKey('(')){
                        int cnt = map.get('(');
                        if(cnt == 1){
                            map.remove('(');
                        } else {
                            map.put('(', cnt - 1);
                        }
                        answer++;
                    } else {
                        return -1;
                    }
                    break;
                case '}':
                    if(map.containsKey('{')){
                        int cnt = map.get('{');
                        if(cnt == 1){
                            map.remove('{');
                        } else {
                            map.put('{', cnt - 1);
                        }
                        answer++;
                    } else {
                        return -1;
                    }
                    break;
                case ']':
                    if(map.containsKey('[')){
                        int cnt = map.get('[');
                        if(cnt == 1){
                            map.remove('[');
                        } else {
                            map.put('[', cnt - 1);
                        }
                        answer++;
                    } else {
                        return -1;
                    }
                    break;
                case '>':
                    if(map.containsKey('<')){
                        int cnt = map.get('<');
                        if(cnt == 1){
                            map.remove('<');
                        } else {
                            map.put('<', cnt - 1);
                        }
                        answer++;
                    } else {
                        return -1;
                    }
                    break;
            }
        }
        
        
        return answer;
    }
}