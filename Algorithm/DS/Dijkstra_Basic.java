import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Dijkstra_Basic {

    static ArrayList<Edge>[] adj;
    static int[] distance;
    static boolean[] visited;
    static int V, E, start, current, min;

    static class Edge {
        int to, weight;

        public Edge(int to, int weight) {
            super();
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        V = sc.nextInt();
        E = sc.nextInt();
        start = sc.nextInt();
        current = start;
        adj = new ArrayList[V + 1];
        distance = new int[V + 1];
        visited = new boolean[V + 1];

        for(int v = 1 ; v <= V; ++v) adj[v] = new ArrayList<Edge>();
        int from, to, weight;
        for(int e = 0 ; e < E ; ++e){
            from = sc.nextInt();
            to = sc.nextInt();
            weight = sc.nextInt();
            adj[from].add(new Edge(to, weight));
        }
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        for(int e = 0 ; e < E ; ++e){
            min = Integer.MAX_VALUE;
            for(int i = 1 ; i <= V ; ++i){
                if(!visited[i] && distance[i] < min){
                    min = distance[i];
                    current = i;
                }
            }

            visited[current] = true;

            for(int i = 0 ; i < adj[current].size() ; ++i){
                Edge edge = adj[current].get(i);
                if(!visited[edge.to] && edge.weight != 0 && edge.weight + min < distance[edge.to]){
                    distance[edge.to] = min + edge.weight;
                }
            }
        }

        for(int i = 1 ; i <= V ; ++i) {
            if(i == start) System.out.println(0);
            else {
                if(distance[i] == Integer.MAX_VALUE) System.out.println("INF");
                else System.out.println(distance[i]);
            }
        }
    }
}