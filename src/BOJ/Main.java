package BOJ;


import java.util.*;

public class Main {
    public static void main(String[] args){
    	String s = "1234";
    	
    	for(int i = 0 ; i < s.length() ; ++i) {
    		int c = (int) s.charAt(i);
    		System.out.println(c);
    	}
    	
    	
    	int[] arr = {1, 2, 3, 4};
    	
    	Arrays.sort(arr);
    }
}