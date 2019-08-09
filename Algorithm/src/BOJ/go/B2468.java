package BOJ.go;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B2468 {
    static Zone[][] map;
    static boolean[][] visited;
    static Queue<Zone> q;
    static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static int N, H, max_h, current_h, ans, cnt;
    static class Zone {
        int r, c, h;

        public Zone(int r, int c, int h) {
            this.r = r;
            this.c = c;
            this.h = h;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        ans = 0;
        cnt = 0;
        visited = new boolean[N][N];
        map = new Zone[N][N];
        q = new LinkedList<Zone>();

        for(int r = 0 ; r < N ; ++r) {
            for (int c = 0 ; c < N ; ++c) {
                H = sc.nextInt();
                max_h = H > max_h ? H : max_h;
                map[r][c] = new Zone(r, c, H);
            }
        }

        for(int i = max_h ; i > 0 ; --i) {
            current_h = i;
            for(int r = 0 ; r < N ; ++r){
                for(int c = 0 ; c < N ; ++c) {
                    if(visited[r][c] || map[r][c].h < current_h) continue;
                    q.offer(map[r][c]);
                    bfs();
                }
            }
            ans = cnt > ans ? cnt : ans;
            visited = new boolean[N][N];
            cnt = 0;
        }
        System.out.println(ans);
    }

    public static void bfs() {
        int nr = 0, nc = 0;

        while(!q.isEmpty()) {
            Zone zone = q.poll();

            for(int i = 0 ; i < dir.length ; ++i) {
                nr = zone.r + dir[i][0];
                nc = zone.c + dir[i][1];
                if(nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc]) continue;
                if(map[nr][nc].h < current_h) continue;

                visited[nr][nc] = true;
                q.offer(new Zone(nr, nc, zone.h));
            }
        }
        cnt++;
    }
}
