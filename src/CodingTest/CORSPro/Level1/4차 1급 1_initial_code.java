import java.util.*;

public class Solution {
    String[] vowels = {"A", "E", "I", "O", "U"};
    ArrayList<String> words;
    public void create_words(int lev, String str) {
        words.add(str);
        for (int i = 0; i < 5; i++) {
            if (lev < 5) {
                create_words(lev, str.concat(vowels[i]));
            }
        }
    }
    
    public int solution(String word) {
        int answer = 0;
        words  = new ArrayList<String>();
        create_words(0, "");
        for (int i = 0; i < words.size(); i++) {
            if (word.equals(words.get(i))) {
                answer = i;
                break;
            }
        }
        return answer;
    }

    // 아래는 테스트케이스 출력을 해보기 위한 코드입니다. 아래에는 잘못된 부분이 없으니, 위의 코드만 수정하세요.
    public static void main(String[] args) {
        Solution sol = new Solution();
        String word1 = new String("AAAAE");
        int ret1 = sol.solution(word1);

        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("solution 메소드의 반환 값은 " + ret1 + " 입니다.");

        String word2 = new String("AAAE");
        int ret2 = sol.solution(word2);

        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("solution 메소드의 반환 값은 " + ret2 + " 입니다.");
    }
}