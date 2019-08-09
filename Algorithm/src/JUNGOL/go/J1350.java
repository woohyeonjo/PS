package JUNGOL.go;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class J1350 {
	
    private static int[] parents;
    private static class Node implements Comparable<Node> {
        int from, to, cost;

        public Node(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return o.cost - this.cost;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);
        long ans = 0;
        
        Node[] list = new Node[m];
        parents = new int[n + 1];
        Arrays.fill(parents, -1);
        
        for(int i = 0 ; i < m; i++) {
            line = br.readLine().split(" ");
            int a = Integer.parseInt(line[0]);
            int b = Integer.parseInt(line[1]);
            int c = Integer.parseInt(line[2]);
            list[i] = new Node(a, b, c);
        }
        
        Arrays.sort(list);
        for(Node e: list) if(union(e.from, e.to)) ans += e.cost;
        System.out.println(ans);
    }
    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot == bRoot) return false;
        parents[bRoot] = aRoot;
        return true;
    }
    private static int find(int a) {
        if(parents[a] == -1) return a;
        return parents[a] = find(parents[a]);
    }
}