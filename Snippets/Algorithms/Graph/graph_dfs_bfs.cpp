#include <iostream>
#include <vector>
#include <queue>
#include <stack>
using namespace std;

/**
 * 그래프 순회 알고리즘
 * DFS (Depth-First Search): 깊이 우선 탐색
 * BFS (Breadth-First Search): 너비 우선 탐색
 */
class Graph {
private:
    int vertices;                    // 정점 수
    vector<vector<int>> adj_list;    // 인접 리스트
    
public:
    Graph(int v) : vertices(v), adj_list(v) {}
    
    // 간선 추가 (무방향 그래프)
    void add_edge(int u, int v) {
        adj_list[u].push_back(v);
        adj_list[v].push_back(u);
    }
    
    // 간선 추가 (방향 그래프)
    void add_directed_edge(int u, int v) {
        adj_list[u].push_back(v);
    }
    
    /**
     * DFS - 재귀 버전
     * 시간복잡도: O(V + E), 공간복잡도: O(V)
     * 스택 오버플로우 가능성 있음
     */
    void dfs_recursive(int start, vector<bool>& visited) {
        visited[start] = true;
        cout << start << " ";
        
        // 인접한 모든 정점에 대해 재귀 호출
        for (int neighbor : adj_list[start]) {
            if (!visited[neighbor]) {
                dfs_recursive(neighbor, visited);
            }
        }
    }
    
    void dfs_recursive(int start) {
        vector<bool> visited(vertices, false);
        cout << "DFS (Recursive) from " << start << ": ";
        dfs_recursive(start, visited);
        cout << endl;
    }
    
    /**
     * DFS - 반복 버전 (스택 사용)
     * 시간복잡도: O(V + E), 공간복잡도: O(V)
     * 스택 오버플로우 방지
     */
    void dfs_iterative(int start) {
        vector<bool> visited(vertices, false);
        stack<int> s;
        
        cout << "DFS (Iterative) from " << start << ": ";
        s.push(start);
        
        while (!s.empty()) {
            int current = s.top();
            s.pop();
            
            if (!visited[current]) {
                visited[current] = true;
                cout << current << " ";
                
                // 인접한 정점들을 스택에 추가 (역순으로 추가하여 올바른 순서 보장)
                for (int i = adj_list[current].size() - 1; i >= 0; i--) {
                    int neighbor = adj_list[current][i];
                    if (!visited[neighbor]) {
                        s.push(neighbor);
                    }
                }
            }
        }
        cout << endl;
    }
    
    /**
     * BFS - 큐 사용
     * 시간복잡도: O(V + E), 공간복잡도: O(V)
     * 최단 경로 찾기에 사용
     */
    void bfs(int start) {
        vector<bool> visited(vertices, false);
        queue<int> q;
        
        cout << "BFS from " << start << ": ";
        visited[start] = true;
        q.push(start);
        
        while (!q.empty()) {
            int current = q.front();
            q.pop();
            cout << current << " ";
            
            // 인접한 모든 정점에 대해
            for (int neighbor : adj_list[current]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    q.push(neighbor);
                }
            }
        }
        cout << endl;
    }
    
    /**
     * BFS를 이용한 최단 거리 계산
     * 가중치가 없는 그래프에서 시작점으로부터의 최단 거리
     */
    void bfs_shortest_distance(int start) {
        vector<int> distance(vertices, -1);
        queue<int> q;
        
        distance[start] = 0;
        q.push(start);
        
        while (!q.empty()) {
            int current = q.front();
            q.pop();
            
            for (int neighbor : adj_list[current]) {
                if (distance[neighbor] == -1) {
                    distance[neighbor] = distance[current] + 1;
                    q.push(neighbor);
                }
            }
        }
        
        cout << "Shortest distances from " << start << ":" << endl;
        for (int i = 0; i < vertices; i++) {
            cout << "To " << i << ": " << distance[i] << endl;
        }
    }
    
    // 연결 성분 개수 찾기
    int count_connected_components() {
        vector<bool> visited(vertices, false);
        int count = 0;
        
        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                dfs_recursive(i, visited);
                count++;
            }
        }
        
        return count;
    }
    
    // 그래프 출력
    void print_graph() {
        cout << "Graph adjacency list:" << endl;
        for (int i = 0; i < vertices; i++) {
            cout << i << ": ";
            for (int neighbor : adj_list[i]) {
                cout << neighbor << " ";
            }
            cout << endl;
        }
    }
};

// 사용 예제
int main() {
    Graph g(6);
    
    // 그래프 구성
    g.add_edge(0, 1);
    g.add_edge(0, 2);
    g.add_edge(1, 3);
    g.add_edge(1, 4);
    g.add_edge(2, 4);
    g.add_edge(3, 4);
    g.add_edge(3, 5);
    
    g.print_graph();
    cout << endl;
    
    // 순회 알고리즘 실행
    g.dfs_recursive(0);
    g.dfs_iterative(0);
    g.bfs(0);
    
    cout << endl;
    g.bfs_shortest_distance(0);
    
    cout << endl;
    cout << "Connected components: " << g.count_connected_components() << endl;
    
    return 0;
}