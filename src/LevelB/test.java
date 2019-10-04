package LevelB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class test {

   static int size, tSize, max = 0;
   static char[] input, patten;
   static HashSet<String> hs;
   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int tnum = Integer.parseInt(br.readLine());
      for (int t = 1; t <= tnum; t++) {
         
         input = br.readLine().toCharArray();
         hs = new HashSet<>();
         String temp = br.readLine();
         hs.add(temp);
         size = input.length;
         tSize = temp.length();
         max = 0;
         
         isPossible();
         
         System.out.println("#" + t + " " + max);
      }
   }

   private static void isPossible() {
      String temp = "";
      
      for(int i = 0; i < tSize; i++) {
         temp += input[i];
      }
      if(hs.contains(temp)) max++;
      for(int i = 1; i <= size - tSize; i++) {
         temp = temp.substring(1);
         temp += input[i];
         if(hs.contains(temp)) max++;
      }
   }
}
