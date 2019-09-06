package BOJ.go;

import java.util.*;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        boolean[][] map = new boolean[100][100];
        
        int T = sc.nextInt();
        
        int R, C;
        for(int t = 0 ; t < T ; ++t){
        	C = sc.nextInt();
        	R = sc.nextInt();
        	for(int r = R ; r < R + 10 ; ++r){
        		for(int c = C ; c < C + 10 ; ++c){
        			map[r][c] = true;
        		}
        	}
        }
        
        int ans = 0;
        for(int r = 0 ; r < 100 ; ++r){
            for(int c = 0 ; c < 100 ; ++c){
                if(map[r][c])ans++;
            }
        }
        System.out.println(ans);
    }
}