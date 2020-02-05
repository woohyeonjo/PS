package BOJ;

import java.util.Scanner;

public class B2004_조합0의개수 {
	public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        long N = sc.nextLong();
        long M = sc.nextLong();

        long twoCnt = countTwo(N) - countTwo(N-M) - countTwo(M);
        long fiveCnt = countFive(N) - countFive(N-M) - countFive(M);

        if(twoCnt>fiveCnt){
            System.out.println(fiveCnt);
        }else{
            System.out.println(twoCnt);
        }


    }

    public static long countTwo(long value){
        long cnt = 0;

        for(long i = 2; i <= value; i*= 2) {
            cnt = cnt + value/i;
        }

        return cnt;
    }

    public static long countFive(long value){
        long cnt = 0;

        for(long i = 5; i <= value; i*=5) {
            cnt += value/i;
        }
        
        return cnt;
    }
}
