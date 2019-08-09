package Algorithm.BOJ.go;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B2468 {

    static Zone[][] map;
    static boolean[][] visited;
    static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static Queue<Zone> q;
    static int N, H, count;

    static class Zone {
        int r, c, h;
        boolean isWet;

        public Zone(int r, int c, int h, boolean isWet) {
            this.r = r;
            this.c = c;
            this.h = h;
            this.isWet = isWet;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        map = new Zone[N][N];
        visited = new boolean[N][N];
        q = new LinkedList<Zone>();

        for(int r = 0 ; r < N ; ++r){
            for(int c = 0 ; c < N ; ++c){
                map[r][c] = new Zone(r, c, sc.nextInt(), false);
                q.offer(map[r][c]);
            }
        }

        for(int r = 0 ; r < N ; ++r){
            for(int c = 0 ; c < N ; ++c){
                System.out.print(map[r][c] + " ");
            }
            System.out.println();
        }



    }
    public void bfs() {

    }
}
