package Algorithm.SWEA.go;

import java.util.Scanner;

public class S1213 {
	public static void main(String args[]){
 
        Scanner sc = new Scanner(System.in);
         
       
        
        for(int t = 1; t <= 10; t++){
        	String T = sc.next();
            String target = sc.next();
            String s = sc.next();
            int count = 0;
            int start = 0;
            
            while(true) {
                int end = s.substring(start, s.length()).indexOf(target);
                if(end >= 0) {
                    count++;
                    start += (end+1);
                } else break;
            }
            System.out.println("#" + T + " " + count);
        }
    }
}
