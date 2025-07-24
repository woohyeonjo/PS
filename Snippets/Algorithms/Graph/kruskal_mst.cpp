#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

/**
 * 크루스칼 알고리즘 (Kruskal's Algorithm)
 * 최소 신장 트리(MST)를 구하는 알고리즘
 * 간선을 가중치 순으로 정렬한 후, 사이클을 만들지 않는 간선만 선택
 * 시간복잡도: O(E log E), Union-Find 사용 시
 */

// 간선 구조체
struct Edge {
    int from, to, weight;
    
    Edge(int f, int t, int w) : from(f), to(t), weight(w) {}
    
    // 가중치로 정렬하기 위한 연산자
    bool operator<(const Edge& other) const {
        return weight < other.weight;
    }
};

// Union-Find (Disjoint Set) 자료구조
class UnionFind {
private:
    vector<int> parent;
    vector<int> rank;  // 트리의 높이 (최적화용)
    
public:
    UnionFind(int n) : parent(n), rank(n, 0) {
        // 초기에는 각 원소가 자기 자신을 부모로 가짐
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }
    
    // Find 연산 - 경로 압축 최적화
    int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);  // 경로 압축
        }
        return parent[x];
    }
    
    // Union 연산 - 랭크를 이용한 최적화
    bool unite(int x, int y) {
        int root_x = find(x);
        int root_y = find(y);
        
        // 이미 같은 집합에 속해 있으면 false 반환
        if (root_x == root_y) {
            return false;
        }
        
        // 랭크가 낮은 트리를 높은 트리 아래에 붙임
        if (rank[root_x] < rank[root_y]) {
            parent[root_x] = root_y;
        } else if (rank[root_x] > rank[root_y]) {
            parent[root_y] = root_x;
        } else {
            parent[root_y] = root_x;
            rank[root_x]++;
        }
        
        return true;
    }
    
    // 두 원소가 같은 집합에 속하는지 확인
    bool connected(int x, int y) {
        return find(x) == find(y);
    }
};

class Graph {
private:
    int vertices;
    vector<Edge> edges;
    
public:
    Graph(int v) : vertices(v) {}
    
    // 간선 추가
    void add_edge(int from, int to, int weight) {
        edges.push_back(Edge(from, to, weight));
    }
    
    /**
     * 크루스칼 알고리즘으로 MST 구하기
     * 반환: MST의 총 가중치와 선택된 간선들
     */
    pair<int, vector<Edge>> kruskal_mst() {
        // 1. 모든 간선을 가중치 순으로 정렬
        sort(edges.begin(), edges.end());
        
        UnionFind uf(vertices);
        vector<Edge> mst_edges;
        int total_weight = 0;
        
        // 2. 간선을 하나씩 검사하며 MST 구성
        for (const Edge& edge : edges) {
            // 사이클을 만들지 않는다면 간선 추가
            if (uf.unite(edge.from, edge.to)) {
                mst_edges.push_back(edge);
                total_weight += edge.weight;
                
                // MST는 정점 수 - 1개의 간선을 가짐
                if (mst_edges.size() == vertices - 1) {
                    break;
                }
            }
        }
        
        return {total_weight, mst_edges};
    }
    
    // 그래프의 모든 간선 출력
    void print_edges() {
        cout << "All edges:" << endl;
        for (const Edge& edge : edges) {
            cout << edge.from << " - " << edge.to << " : " << edge.weight << endl;
        }
        cout << endl;
    }
    
    // MST 결과 출력
    void print_mst() {
        auto result = kruskal_mst();
        int total_weight = result.first;
        vector<Edge> mst_edges = result.second;
        
        cout << "Minimum Spanning Tree (Kruskal):" << endl;
        cout << "Selected edges:" << endl;
        
        for (const Edge& edge : mst_edges) {
            cout << edge.from << " - " << edge.to << " : " << edge.weight << endl;
        }
        
        cout << "Total weight: " << total_weight << endl;
        cout << "Number of edges: " << mst_edges.size() << endl;
    }
    
    // 연결 그래프인지 확인
    bool is_connected() {
        auto result = kruskal_mst();
        return result.second.size() == vertices - 1;
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
    
    g.print_edges();
    g.print_mst();
    
    cout << endl;
    cout << "Is connected: " << (g.is_connected() ? "Yes" : "No") << endl;
    
    return 0;
}