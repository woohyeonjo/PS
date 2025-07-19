package DS;

import java.math.BigInteger;
import java.util.Scanner;

public class HanoiTower {
    static int N;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        BigInteger base = new BigInteger("2");
        BigInteger ans = base.pow(N).subtract(BigInteger.ONE);

        System.out.println(ans);
        if(N <= 20) hanoi(N, 1, 2, 3);
    }

    private static void hanoi(int disk, int from, int by, int to){
        if(disk == 1) {
            System.out.println(from + "번 기둥에서 " + to + "번 기둥으로 ");
            return;
        }
        hanoi(disk - 1, from, to, by);
        System.out.println(from + "번 기둥에서 " + to + "번 기둥으로 ");
        hanoi(disk - 1, by, from, to);
    }
}
