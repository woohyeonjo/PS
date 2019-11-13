package CodingTest.Kakao;

import java.util.*;

class Solution3 {
   static HashSet<Integer> hs;
   static ArrayList<Integer>[] list;
   static int bSize, uSize, result;
   static HashSet<Integer> resultSet;
   static String[] user_id;

   public int solution(String[] user_id, String[] banned_id) {
      init(user_id, banned_id);

      for (int i = 0; i < banned_id.length; i++) {
         lp: for (int j = 0; j < user_id.length; j++) {
            if (banned_id[i].length() == user_id[j].length()) {
               for (int k = 0; k < user_id[j].length(); k++) {
                  char b = banned_id[i].charAt(k);
                  char u = user_id[j].charAt(k);
                  if (b == '*') {
                     continue;
                  }

                  if (b != u) {
                     continue lp;
                  }
               }
               list[i].add(j);
            }
         }
      }

      permutation(0);
//      for(int num : resultSet) {
//         System.out.print(num + " ");
//      }
//      System.out.println();

      return resultSet.size();
   }

   private static void permutation(int depth) {
      if (depth == bSize) {

         addResult();
         return;
      }
      for (int i = 0; i < list[depth].size(); i++) {
         if (!hs.contains(list[depth].get(i))) {
            hs.add(list[depth].get(i));
            permutation(depth + 1);
            hs.remove(list[depth].get(i));
         }
      }
   }

   private static void addResult() {
      ArrayList<Integer> numList = new ArrayList<>(hs);
      
      Collections.sort(numList);
      int temp = 0;
      for(int i = 0; i < numList.size(); i++) {
         temp += numList.get(i)+1;
         if(i != numList.size()-1) {
            temp *= 10;
         }
      }
//      System.out.println(temp);
      resultSet.add(temp);
   }

   private static void init(String[] u, String[] b) {
      user_id = u;
      hs = new HashSet<>();
      resultSet = new HashSet<>();
      bSize = b.length;
      uSize = u.length;
      result = 0;
      list = new ArrayList[bSize];
      for (int i = 0; i < bSize; i++) {
         list[i] = new ArrayList<>();
      }
   }
}