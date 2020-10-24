package DS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Kruskal {
    static int[] parent;
    static ArrayList<Edge> adj;
    static int V, E, ans;
    static class Edge implements Comparable<Edge> {
        int from, to, weight;

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        @Override
        public int compareTo(Edge o) { return this.weight - o.weight; }
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if(x != y)  parent[y] = x;
    }

    public static int find(int x) {
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        V = sc.nextInt();
        E = sc.nextInt();
        ans = 0;
        adj = new ArrayList<Edge>();
        parent = new int[V + 1];

        int from, to, weight;
        for(int i = 0; i < E; i++) {
            from = sc.nextInt();
            to = sc.nextInt();
            weight = sc.nextInt();
            adj.add(new Edge(from, to, weight));
        }
        for(int i = 1; i <= V; i++) parent[i] = i;

        Collections.sort(adj);

        for(Edge e : adj) {
            if(find(e.from) == find(e.to)) continue;
            else {
                ans += e.weight;
                union(e.from, e.to);
            }
        }

        System.out.println(ans);
    }



}
