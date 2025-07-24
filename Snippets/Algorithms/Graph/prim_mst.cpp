#include <iostream>
#include <vector>
#include <queue>
#include <climits>
using namespace std;

/**
 * 프림 알고리즘 (Prim's Algorithm)
 * 최소 신장 트리(MST)를 구하는 알고리즘
 * 한 정점에서 시작하여 가장 작은 가중치의 간선을 하나씩 추가
 * 시간복잡도: O(E log V) with priority queue
 */

struct Edge {
    int to;
    int weight;
    
    Edge(int t, int w) : to(t), weight(w) {}
};

// 우선순위 큐를 위한 비교 함수 (최소 힙)
struct Compare {
    bool operator()(const pair<int, pair<int, int>>& a, 
                   const pair<int, pair<int, int>>& b) {
        return a.first > b.first;  // 가중치 기준 최소 힙
    }
};

class Graph {
private:
    int vertices;
    vector<vector<Edge>> adj_list;
    
public:
    Graph(int v) : vertices(v), adj_list(v) {}
    
    // 무방향 그래프에 간선 추가
    void add_edge(int u, int v, int weight) {
        adj_list[u].push_back(Edge(v, weight));
        adj_list[v].push_back(Edge(u, weight));
    }
    
    /**
     * 프림 알고리즘으로 MST 구하기
     * start: 시작 정점
     * 반환: MST의 총 가중치와 선택된 간선들
     */
    pair<int, vector<pair<int, int>>> prim_mst(int start = 0) {
        vector<bool> in_mst(vertices, false);  // MST에 포함된 정점들
        vector<int> key(vertices, INT_MAX);    // 각 정점의 최소 가중치
        vector<int> parent(vertices, -1);      // MST에서의 부모 정점
        
        // 우선순위 큐: (가중치, (정점, 부모))
        priority_queue<pair<int, pair<int, int>>, 
                      vector<pair<int, pair<int, int>>>, 
                      Compare> pq;
        
        // 시작 정점 초기화
        key[start] = 0;
        pq.push({0, {start, -1}});
        
        vector<pair<int, int>> mst_edges;
        int total_weight = 0;
        
        while (!pq.empty()) {
            int weight = pq.top().first;
            int u = pq.top().second.first;
            int p = pq.top().second.second;
            pq.pop();
            
            // 이미 MST에 포함된 정점인 경우 스킵
            if (in_mst[u]) {
                continue;
            }
            
            // MST에 정점 추가
            in_mst[u] = true;
            parent[u] = p;
            total_weight += weight;
            
            // 간선 추가 (시작 정점 제외)
            if (p != -1) {
                mst_edges.push_back({min(u, p), max(u, p)});
            }
            
            // 인접한 정점들 확인
            for (const Edge& edge : adj_list[u]) {
                int v = edge.to;
                int w = edge.weight;
                
                // MST에 포함되지 않고, 더 작은 가중치를 가진 경우
                if (!in_mst[v] && w < key[v]) {
                    key[v] = w;
                    pq.push({w, {v, u}});
                }
            }
        }
        
        return {total_weight, mst_edges};
    }
    
    /**
     * 배열 기반 프림 알고리즘 (밀집 그래프에 적합)
     * 시간복잡도: O(V²)
     */
    pair<int, vector<pair<int, int>>> prim_mst_array(int start = 0) {
        vector<bool> in_mst(vertices, false);
        vector<int> key(vertices, INT_MAX);
        vector<int> parent(vertices, -1);
        
        key[start] = 0;
        
        vector<pair<int, int>> mst_edges;
        int total_weight = 0;
        
        for (int count = 0; count < vertices; count++) {
            // MST에 포함되지 않은 정점 중 최소 key 값을 가진 정점 찾기
            int u = -1;
            for (int v = 0; v < vertices; v++) {
                if (!in_mst[v] && (u == -1 || key[v] < key[u])) {
                    u = v;
                }
            }
            
            in_mst[u] = true;
            total_weight += key[u];
            
            if (parent[u] != -1) {
                mst_edges.push_back({min(u, parent[u]), max(u, parent[u])});
            }
            
            // 인접한 정점들의 key 값 업데이트
            for (const Edge& edge : adj_list[u]) {
                int v = edge.to;
                int weight = edge.weight;
                
                if (!in_mst[v] && weight < key[v]) {
                    key[v] = weight;
                    parent[v] = u;
                }
            }
        }
        
        return {total_weight, mst_edges};
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
        cout << endl;
    }
    
    // MST 결과 출력
    void print_mst() {
        cout << "=== Priority Queue Version ===" << endl;
        auto result1 = prim_mst();
        print_mst_result(result1.first, result1.second);
        
        cout << "\n=== Array Version ===" << endl;
        auto result2 = prim_mst_array();
        print_mst_result(result2.first, result2.second);
    }
    
private:
    void print_mst_result(int total_weight, const vector<pair<int, int>>& edges) {
        cout << "Minimum Spanning Tree (Prim):" << endl;
        cout << "Selected edges:" << endl;
        
        for (const auto& edge : edges) {
            cout << edge.first << " - " << edge.second << endl;
        }
        
        cout << "Total weight: " << total_weight << endl;
        cout << "Number of edges: " << edges.size() << endl;
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
    g.add_edge(2, 4, 2);
    g.add_edge(3, 4, 3);
    g.add_edge(3, 5, 2);
    g.add_edge(4, 5, 6);
    
    g.print_graph();
    g.print_mst();
    
    return 0;
}