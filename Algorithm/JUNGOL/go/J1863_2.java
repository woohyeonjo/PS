package Algorithm.JUNGOL.go;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
 
public class J1863_2{
    static LinkedList<Integer>[] adj;
    static boolean[] visited;
    static Queue<Integer> q = new LinkedList<Integer>();
    static int count = 0;
    public static void main(String[] args) {
         
        Scanner sc = new Scanner(System.in);
         
        String temp[] = sc.nextLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);
         
        if(M != 0) {
        	adj = new LinkedList[N + 1];
        	for(int i = 1 ; i <= N ; ++i) {
        		adj[i] = new LinkedList<Integer>();
        	}
        	visited = new boolean[N + 1];
        	
        	for (int m = 0; m < M; ++m) {
        		temp = sc.nextLine().split(" ");
        		int i = Integer.parseInt(temp[0]);
        		int j = Integer.parseInt(temp[1]);
//        		if (adj[i] == null) {
//        			adj[i] = new LinkedList<Integer>();
//        		}
//        		if (adj[j] == null) {
//        			adj[j] = new LinkedList<Integer>();
//        		}
        		adj[i].add(j);
        		adj[j].add(i);
        	}
        	
        	for (int i = 1; i <= N; ++i) {
        		if (!visited[i]) {
        			count++;
        			q.offer(i);
        			bfs();
        		}
        	}
        } else count = N;
        System.out.println(count);
    }
     
    private static void bfs() {
        while(!q.isEmpty()) {
            int p = (int) q.poll();
             
            for(Integer num : adj[p]) {
            	if(!visited[num]) {
            		visited[num] = true;
            		q.offer(num);
            	}
            }
        }
    }
}