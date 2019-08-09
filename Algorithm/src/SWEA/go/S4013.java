package SWEA.go;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
 
public class S4013 {
     
    static Magnet[] magnets;
    static boolean[] turned;
    static Queue<Magnet> q;
    static int T, K, target, dir;
    static class Magnet{
        int num;
        int dir;
        int[] cog;
         
        public Magnet(int num) {
            super();
            this.num = num;
            cog = new int[8];
        }
         
        public void forward() {
            int temp = cog[7];
            for(int i = 7 ; i >= 1; --i) {
                cog[i] = cog[i - 1];
            }
            cog[0] = temp;
        }
         
        public void backward() {
            int temp = cog[0];
            for(int i = 0 ; i < 7 ; ++i) {
                cog[i] = cog[i + 1];
            }
            cog[7] = temp;
        }
         
        @Override
        public String toString() {
            return Arrays.toString(cog);
        }
    }
     
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
         
        T = sc.nextInt();
        for(int t = 1 ; t <= T ; ++t) {
            K = sc.nextInt();
             
            magnets = new Magnet[5];
            q = new LinkedList<Magnet>();
             
            for(int i = 1 ; i <= 4 ; ++i) {
                magnets[i] = new Magnet(i);
                for(int j = 0 ; j < 8 ; ++j) {
                    magnets[i].cog[j] = sc.nextInt();
                }
            }
             
            for(int k = 0 ; k < K ; ++k) {
                turned = new boolean[5];
                target = sc.nextInt();
                dir = sc.nextInt();
                magnets[target].dir = dir;
                q.offer(magnets[target]);
                turned[target] = true;
                calc();
                 
                if(dir == -1) magnets[target].backward();
                else if(dir == 1) magnets[target].forward();
                 
            }
            System.out.println("#" + t + " " + score());
        }
    }
 
    private static void calc() {
        int left, right;
        while(!q.isEmpty()) {
            Magnet mg = q.poll();
             
            left = mg.num - 1;
            right = mg.num + 1;
             
            if(left > 0 && !turned[left]) {
                Magnet origin = new Magnet(magnets[left].num);
                origin.cog = magnets[left].cog.clone();
                turned[left] = true;
                if(mg.cog[6] != magnets[left].cog[2]) {
                    if(mg.dir == 1) {
                        magnets[left].backward();
                        origin.dir = -1;
                    }
                    else if(mg.dir == -1) {
                        magnets[left].forward();
                        origin.dir = 1;
                    }
                    q.offer(origin);
                }
            }
             
            if(right < 5 && !turned[right]) {
                Magnet origin = new Magnet(magnets[right].num);
                origin.cog = magnets[right].cog.clone();
                turned[right] = true;
                if(mg.cog[2] != magnets[right].cog[6]) {
                    if(mg.dir == 1) {
                        magnets[right].backward();
                        origin.dir = -1;
                    }
                    else if(mg.dir == -1) {
                        magnets[right].forward();
                        origin.dir = 1;
                    }
                    q.offer(origin);
                }
            }
        }
    }
     
    private static int score() {
        int ans = 0;
         
        for(int i = 0 ; i < 4 ; ++i) {
            if(magnets[i + 1].cog[0] == 1) {
                ans += Math.pow(2, i);
            }
        }
        return ans;
    }
}