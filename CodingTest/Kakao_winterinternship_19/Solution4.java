package CodingTest.Kakao_winterinternship_19;

import java.util.HashMap;

public class Solution4 {

   public static void main(String[] args) {
      long[] num = { 1, 3, 4, 1, 3, 1 };

      long[] result = solution(10, num);

      for (int i = 0; i < size; i++) {
         System.out.println(result[i]);
      }
   }

   static int size;
   static HashMap<Long, Long> hm;

   public static long[] solution(long k, long[] room_number) {

      size = room_number.length;
      hm = new HashMap<>();
      long[] answer = new long[size];

      for (int i = 0; i < size; i++) {
         if (!hm.containsKey(room_number[i])) {
            hm.put(room_number[i], room_number[i] + 1);
            answer[i] = room_number[i];
         } else {
            long temp = find(room_number[i]);            
            hm.put(temp, temp + 1);
            hm.put(room_number[i], temp+1);
            answer[i] = temp;
         }
      }
      return answer;
   }
   
   private static long find(long target) {
      if(!hm.containsKey(target)) {
         return target;
      }
      target = hm.get(target);
      return find(target);
   }

}
