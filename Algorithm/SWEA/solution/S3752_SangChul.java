package Algorithm.SWEA.solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class S3752_SangChul{
    private static int n, ans;
    private static int[] arr;
    private static int[][] chk;
    private static Set<Integer> set;
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
 
        int tcase = Integer.parseInt(st.nextToken());
        for (int t = 1; t <= tcase; t++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            arr = new int[n];
            chk = new int[n + 1][10002];
            set = new HashSet<>();
            ans = 0;
 
            st = new StringTokenizer(br.readLine());
            for(int i = 0 ; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());
            go(0, 0);
            System.out.printf("#%d %d\n", t, ans);
        }
    }
 
    private static void go(int lo, int sum) {
        chk[lo][sum] = 1;
        if(!set.contains(sum)) {
            set.add(sum);
            ans++;
        }
        if(lo == n) return;
        for(int i = lo; i < n; i++) {
            if(chk[i+ 1][sum + arr[i]] == 1) continue;
            go(i + 1, sum + arr[i]);
        }
    }
}
