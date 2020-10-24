package CodingTest.LINE_200405;
import java.util.*;

class Solution2 {
    
    static int answer;
    public int solution(String answer_sheet, String[] sheets) {
        
        answer = 0;
        
        int[] selected = new int[2];
        
        permu(0, -1, selected, answer_sheet, sheets);
        
        return answer;
    }
    
    public int calc(int[] selected, String answer_sheet, String[] sheets){
        int longest = 0;
        int cnt = 0;
        int amount = 0;
        String A = sheets[selected[0]];
        String B = sheets[selected[1]];
        
        for(int i = 0 ; i < answer_sheet.length() ; ++i){
            char solution = answer_sheet.charAt(i);
            char answer_A = A.charAt(i);
            char answer_B = B.charAt(i);
            
            if(answer_A == answer_B && answer_A != solution){
                amount++;
                cnt++;
                longest = cnt > longest ? cnt : longest; 
            } else {
                cnt = 0;
            }
        }
        
        return amount + (int)Math.pow(longest, 2);
    }
    
    public void permu(int cnt, int idx, int[] selected, String answer_sheet, String[] sheets){
        if(cnt == 2){
            int point = calc(selected, answer_sheet, sheets);
            System.out.println(point);
            answer = point > answer ? point : answer;
            return;
        }

        for(int i = idx + 1 ; i < sheets.length ; ++i){
            selected[cnt] = i;
            permu(cnt + 1, i, selected, answer_sheet, sheets);
        }
    }
}