package CodingTest.COSPro.Level1;

// 다음과 같이 import를 사용할 수 있습니다.
import java.util.*;

class Solution_4_8 {
	ArrayList<Integer> list;
	
	private int[] counting(int[] card) {
		int[] count = new int[10];
		
		for(int i = 0 ; i < card.length ; ++i) {
			count[card[i]]++;
		}
		
		return count;
	}
	
	private void recur(int[] count, int max_depth, int depth, int num) {
		if(depth == max_depth) {
			list.add(num);
			return;
		}
		
		for(int i = 1 ; i < 10 ; ++i) {
			if(count[i] > 0) {
				count[i]--;
				recur(count, max_depth, depth + 1, num * 10 + i);
				count[i]++;
			}
		}
	}
	
    public int solution(int[] card, int n) {
        // 여기에 코드를 작성해주세요.
        int answer = -1;
        
        list = new ArrayList<>();
        int[] count = counting(card);
        
        recur(count, card.length, 0, 0);
        
        for(int i = 0 ; i < list.size() ; ++i) {
        	if(list.get(i) == n) {
        		answer = i + 1;
        		break;
        	}
        }
        
        return answer;
    }
    


	// 아래는 테스트케이스 출력을 해보기 위한 main 메소드입니다.
    public static void main(String[] args) {
    	Solution_4_8 sol = new Solution_4_8();
        int card1[] = {1, 2, 1, 3};
        int n1 = 1312;
        int ret1 = sol.solution(card1, n1);

        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("solution 메소드의 반환 값은 " + ret1 + " 입니다.");

        int card2[] = {1, 1, 1, 2};
        int n2 = 1122;
        int ret2 = sol.solution(card2, n2);
        
        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("solution 메소드의 반환 값은 " + ret2 + " 입니다.");
    }    
}