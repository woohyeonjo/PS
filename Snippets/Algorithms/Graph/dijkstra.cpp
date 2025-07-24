#include <iostream>
#include <vector>
#include <queue>
#include <climits>
using namespace std;

/**
 * 다익스트라 알고리즘 (Dijkstra's Algorithm)
 * 가중치가 있는 그래프에서 한 정점으로부터 모든 정점까지의 최단 경로를 구함
 * 시간복잡도: O((V + E) log V) with priority queue
 * 음수 가중치가 없는 그래프에서만 사용 가능
 */

struct Edge {
    int to;      // 목적지 정점
    int weight;  // 가중치
    
    Edge(int t, int w) : to(t), weight(w) {}
};

class Graph {
private:
    int vertices;
    vector<vector<Edge>> adj_list;
    
public:
    Graph(int v) : vertices(v), adj_list(v) {}
    
    // 방향 그래프에 간선 추가
    void add_edge(int from, int to, int weight) {
        adj_list[from].push_back(Edge(to, weight));
    }
    
    // 무방향 그래프에 간선 추가
    void add_undirected_edge(int u, int v, int weight) {
        adj_list[u].push_back(Edge(v, weight));
        adj_list[v].push_back(Edge(u, weight));
    }
    
    /**
     * 다익스트라 알고리즘 - 우선순위 큐 사용
     * start: 시작 정점
     * 반환: 시작 정점으로부터 모든 정점까지의 최단 거리 배열
     */
    vector<int> dijkstra(int start) {
        // 거리 배열 초기화 (무한대로 설정)
        vector<int> distance(vertices, INT_MAX);
        
        // 우선순위 큐 (거리, 정점) - 최소 힙
        priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
        
        // 시작 정점의 거리는 0
        distance[start] = 0;
        pq.push({0, start});
        
        while (!pq.empty()) {
            int current_dist = pq.top().first;
            int current_vertex = pq.top().second;
            pq.pop();
            
            // 이미 처리된 정점인 경우 스킵
            if (current_dist > distance[current_vertex]) {
                continue;
            }
            
            // 인접한 모든 정점에 대해
            for (const Edge& edge : adj_list[current_vertex]) {
                int next_vertex = edge.to;
                int weight = edge.weight;
                int new_distance = distance[current_vertex] + weight;
                
                // 더 짧은 경로를 발견한 경우
                if (new_distance < distance[next_vertex]) {
                    distance[next_vertex] = new_distance;
                    pq.push({new_distance, next_vertex});
                }
            }
        }
        
        return distance;
    }
    
    /**
     * 경로 추적이 가능한 다익스트라
     * 최단 거리뿐만 아니라 경로도 저장
     */
    pair<vector<int>, vector<int>> dijkstra_with_path(int start) {
        vector<int> distance(vertices, INT_MAX);
        vector<int> parent(vertices, -1);  // 경로 추적용
        
        priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
        
        distance[start] = 0;
        pq.push({0, start});
        
        while (!pq.empty()) {
            int current_dist = pq.top().first;
            int current_vertex = pq.top().second;
            pq.pop();
            
            if (current_dist > distance[current_vertex]) {
                continue;
            }
            
            for (const Edge& edge : adj_list[current_vertex]) {
                int next_vertex = edge.to;
                int weight = edge.weight;
                int new_distance = distance[current_vertex] + weight;
                
                if (new_distance < distance[next_vertex]) {
                    distance[next_vertex] = new_distance;
                    parent[next_vertex] = current_vertex;
                    pq.push({new_distance, next_vertex});
                }
            }
        }
        
        return {distance, parent};
    }
    
    // 특정 목적지까지의 경로 출력
    void print_path(const vector<int>& parent, int start, int end) {
        if (parent[end] == -1 && start != end) {
            cout << "No path from " << start << " to " << end << endl;
            return;
        }
        
        vector<int> path;
        int current = end;
        
        // 역추적하여 경로 구성
        while (current != -1) {
            path.push_back(current);
            current = parent[current];
        }
        
        // 경로 출력 (역순으로 저장되어 있으므로 뒤집어서 출력)
        cout << "Path from " << start << " to " << end << ": ";
        for (int i = path.size() - 1; i >= 0; i--) {
            cout << path[i];
            if (i > 0) cout << " -> ";
        }
        cout << endl;
    }
    
    // 그래프 출력
    void print_graph() {
        cout << "Graph adjacency list:" << endl;
        for (int i = 0; i < vertices; i++) {
            cout << i << ": ";
            for (const Edge& edge : adj_list[i]) {
                cout << "(" << edge.to << ", " << edge.weight << ") ";
            }
            cout << endl;
        }
    }
};

// 사용 예제
int main() {
    Graph g(6);
    
    // 그래프 구성
    g.add_edge(0, 1, 4);
    g.add_edge(0, 2, 3);
    g.add_edge(1, 2, 1);
    g.add_edge(1, 3, 2);
    g.add_edge(2, 3, 4);
    g.add_edge(3, 4, 2);
    g.add_edge(4, 5, 6);
    
    g.print_graph();
    cout << endl;
    
    int start = 0;
    
    // 기본 다익스트라
    vector<int> distances = g.dijkstra(start);
    cout << "Shortest distances from vertex " << start << ":" << endl;
    for (int i = 0; i < distances.size(); i++) {
        if (distances[i] == INT_MAX) {
            cout << "To " << i << ": INF" << endl;
        } else {
            cout << "To " << i << ": " << distances[i] << endl;
        }
    }
    
    cout << endl;
    
    // 경로 추적이 가능한 다익스트라
    auto result = g.dijkstra_with_path(start);
    vector<int> dist = result.first;
    vector<int> parent = result.second;
    
    // 모든 정점까지의 경로 출력
    for (int i = 0; i < dist.size(); i++) {
        if (i != start) {
            g.print_path(parent, start, i);
        }
    }
    
    return 0;
}