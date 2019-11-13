package CodingTest.Kakao;

public class Solution5 {
	public static void main(String[] args) {
		int[] stones = { 2, 4, 5, 3, 2, 1, 4, 2, 5, 1 };
		System.out.println(solution(stones, 3));
    }

   static int[] list;
   static int limit, size;

   public static int solution(int[] stones, int k) {
	   int answer = 0;
	   list = stones;
	   limit = k;
	   size = stones.length;
	   
	   while (isPossible()) {
		   answer++;
	   }
	   
	   return answer;
   }

   private static boolean isPossible() {
	   lp: for(int i = 0; i < size; i++) {
		   if(list[i] == 0) {
			   for(int j = 1; j < limit; j++) {
				   if(i + j >= size) {
	                  return true;
	               }
	               if(list[i + j] != 0) {
	                  list[i + j]--;
	                  i += j;
	                  continue lp;
	               }
			   }
			   return false;
		   } else {
			   list[i]--;
		   }
      }
      return true;
   }
}
