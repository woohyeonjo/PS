import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class B1197 {

    static ArrayList<Edge>[] adj;
    static boolean[] visited;
    static Queue<Edge> q;
    static int V, E, ans;
    static class Edge implements Comparable<Edge>{
        int to, weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        V = sc.nextInt();
        E = sc.nextInt();

        visited = new boolean[V + 1];
        q = new PriorityQueue<Edge>();
        adj = new ArrayList[V + 1];
        ans = 0;
        for(int v = 1 ; v <= V ; ++v) adj[v] = new ArrayList<Edge>();
        int from, to, weight;
        for(int e = 0 ; e < E ; ++e){
            from = sc.nextInt();
            to = sc.nextInt();
            weight = sc.nextInt();
            adj[from].add(new Edge(to, weight));
            adj[to].add(new Edge(from, weight));
        }
        q.offer(new Edge(1, 0));

        while(!q.isEmpty()){
            Edge e = q.poll();
            if(visited[e.to]) continue;
            visited[e.to] = true;
            ans += e.weight;
            for(Edge ne : adj[e.to]) if(!visited[ne.to]) q.add(ne);
        }
        System.out.println(ans);
    }
}
