package CodingTest.LINE_200405;
import java.util.*;

class Solution3 {
    static int answer;
    
    public int solution(String road, int n) {
        answer = 0;
       
        char[] tile = road.toCharArray();
        
        int cnt = 0;
        for(int i = 0 ; i < tile.length ; ++i){
            if(tile[i] == '0') cnt++;
        }
        
        System.out.println(cnt);
        
        if(cnt > n){
            solve(-1, 0, n, tile);
        } else {
            solve(-1, 0, cnt, tile);
        }
        
        return answer;
    }
    
    public void solve(int idx, int cnt, int max, char[] tile){
        if(cnt == max){
            int longest = 0;
            int len = 0;
            
            for(int i = 0 ; i < tile.length ; ++i){
                if(tile[i] == '1'){
                    len++;
                    longest = len > longest ? len : longest;
                } else {
                    longest = len > longest ? len : longest;
                    len = 0;
                }
            }
            
            answer = longest > answer ? longest : answer;
            return;
        }
        
        for(int i = idx + 1 ; i < tile.length ; ++i){
            if(tile[i] == '0'){
                tile[i] = '1';
                solve(i, cnt + 1, max, tile);
                tile[i] = '0';
            }
        }
    }
}