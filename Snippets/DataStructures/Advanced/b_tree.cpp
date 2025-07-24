#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

/**
 * B-트리 (B-Tree)
 * 자가 균형 다진 탐색 트리
 * 
 * 용도:
 * - 대용량 데이터베이스의 인덱스 구조
 * - 파일 시스템의 디렉토리 구조
 * - 디스크 기반 저장 시스템
 * - 외부 정렬 알고리즘
 * - 대용량 데이터의 범위 검색
 * 
 * 특징:
 * - 이진 트리와 달리 한 노드가 여러 키와 자식을 가짐
 * - 디스크 I/O 최적화를 위해 설계됨 (높은 branching factor)
 * - 모든 리프가 같은 레벨에 있어 균형 보장
 * - 노드 분할과 병합을 통한 동적 균형 유지
 * - 순차 접근과 범위 검색에 매우 효율적
 * 
 * 사용 시나리오:
 * - 디스크 기반 저장소에서 데이터 접근이 필요한 경우
 * - 메모리보다 훨씬 큰 데이터셋을 다루는 경우
 * - 범위 쿼리(range query)가 빈번한 경우
 * - 순차 접근 패턴이 중요한 경우
 * - 디스크 I/O 비용을 최소화해야 하는 경우
 * 
 * 실제 사용 사례:
 * - MySQL의 InnoDB 스토리지 엔진
 * - PostgreSQL의 B-tree 인덱스
 * - MongoDB의 인덱스 구조
 * - SQLite의 데이터베이스 파일 구조
 * - NTFS, ext4 등 파일 시스템의 디렉토리 인덱스
 * - Oracle, SQL Server 등 상용 데이터베이스
 * - HBase, Cassandra 등 NoSQL 데이터베이스
 * 
 * 원리:
 * - 노드 크기를 디스크 블록 크기에 맞춰 설계
 * - 높은 branching factor로 트리 높이 최소화
 * - 노드가 가득 차면 분할, 비어가면 병합
 * - 모든 연산에서 루트에서 리프까지 경로 길이 동일
 * - 디스크 I/O 횟수 = 트리 높이로 예측 가능한 성능
 * 
 * B-트리의 특성 (차수 m):
 * 1. 모든 리프 노드는 같은 레벨에 있다.
 * 2. 루트 노드를 제외한 모든 내부 노드는 최소 ⌈m/2⌉개의 자식을 가진다.
 * 3. 모든 노드는 최대 m개의 자식을 가진다.
 * 4. k개의 자식을 가진 노드는 k-1개의 키를 가진다.
 * 5. 노드 내의 키들은 오름차순으로 정렬되어 있다.
 * 
 * 시간복잡도: O(log n) (검색, 삽입, 삭제)
 * 공간복잡도: O(n)
 * 디스크 I/O가 중요한 환경에서 효율적
 */

template<typename T>
class BTree {
private:
    struct BTreeNode {
        vector<T> keys;           // 키들
        vector<BTreeNode*> children;  // 자식 노드들
        bool is_leaf;             // 리프 노드 여부
        
        BTreeNode(bool leaf = true) : is_leaf(leaf) {}
        
        ~BTreeNode() {
            for (BTreeNode* child : children) {
                delete child;
            }
        }
    };
    
    BTreeNode* root;
    int min_degree;  // 최소 차수 (t)
    
    // 노드가 가득 찬지 확인
    bool is_full(BTreeNode* node) {
        return node->keys.size() == 2 * min_degree - 1;
    }
    
    // 자식 노드 분할
    void split_child(BTreeNode* parent, int index) {
        BTreeNode* full_child = parent->children[index];
        BTreeNode* new_child = new BTreeNode(full_child->is_leaf);
        
        int mid = min_degree - 1;
        
        // 오른쪽 절반의 키들을 새 노드로 이동
        new_child->keys.assign(full_child->keys.begin() + mid + 1, full_child->keys.end());
        full_child->keys.resize(mid);
        
        // 내부 노드인 경우 자식들도 이동
        if (!full_child->is_leaf) {
            new_child->children.assign(full_child->children.begin() + mid + 1, 
                                     full_child->children.end());
            full_child->children.resize(mid + 1);
        }
        
        // 부모 노드에 중간 키와 새 자식 추가
        T mid_key = full_child->keys[mid];
        parent->keys.insert(parent->keys.begin() + index, mid_key);
        parent->children.insert(parent->children.begin() + index + 1, new_child);
    }
    
    // 가득 차지 않은 노드에 키 삽입
    void insert_non_full(BTreeNode* node, const T& key) {
        int i = node->keys.size() - 1;
        
        if (node->is_leaf) {
            // 리프 노드에 키 삽입
            node->keys.resize(node->keys.size() + 1);
            while (i >= 0 && key < node->keys[i]) {
                node->keys[i + 1] = node->keys[i];
                i--;
            }
            node->keys[i + 1] = key;
        } else {
            // 적절한 자식 찾기
            while (i >= 0 && key < node->keys[i]) {
                i--;
            }
            i++;
            
            // 자식이 가득 찬 경우 분할
            if (is_full(node->children[i])) {
                split_child(node, i);
                if (key > node->keys[i]) {
                    i++;
                }
            }
            
            insert_non_full(node->children[i], key);
        }
    }
    
    // 검색 헬퍼
    bool search_helper(BTreeNode* node, const T& key) {
        if (node == nullptr) return false;
        
        int i = 0;
        while (i < node->keys.size() && key > node->keys[i]) {
            i++;
        }
        
        if (i < node->keys.size() && key == node->keys[i]) {
            return true;  // 키를 찾음
        }
        
        if (node->is_leaf) {
            return false;  // 리프 노드에서 찾지 못함
        }
        
        return search_helper(node->children[i], key);
    }
    
    // 키의 전임자 찾기 (왼쪽 서브트리의 최댓값)
    T get_predecessor(BTreeNode* node, int idx) {
        BTreeNode* current = node->children[idx];
        while (!current->is_leaf) {
            current = current->children.back();
        }
        return current->keys.back();
    }
    
    // 키의 후임자 찾기 (오른쪽 서브트리의 최솟값)
    T get_successor(BTreeNode* node, int idx) {
        BTreeNode* current = node->children[idx + 1];
        while (!current->is_leaf) {
            current = current->children[0];
        }
        return current->keys[0];
    }
    
    // 자식에서 키 가져오기 (merge 전 처리)
    void fill(BTreeNode* node, int idx) {
        // 왼쪽 형제에서 가져오기
        if (idx != 0 && node->children[idx - 1]->keys.size() >= min_degree) {
            borrow_from_prev(node, idx);
        }
        // 오른쪽 형제에서 가져오기
        else if (idx != node->children.size() - 1 && 
                 node->children[idx + 1]->keys.size() >= min_degree) {
            borrow_from_next(node, idx);
        }
        // 형제와 병합
        else {
            if (idx != node->children.size() - 1) {
                merge(node, idx);
            } else {
                merge(node, idx - 1);
            }
        }
    }
    
    // 왼쪽 형제에서 키 가져오기
    void borrow_from_prev(BTreeNode* node, int idx) {
        BTreeNode* child = node->children[idx];
        BTreeNode* sibling = node->children[idx - 1];
        
        // 부모의 키를 자식으로 이동
        child->keys.insert(child->keys.begin(), node->keys[idx - 1]);
        
        // 형제의 마지막 키를 부모로 이동
        node->keys[idx - 1] = sibling->keys.back();
        sibling->keys.pop_back();
        
        // 내부 노드인 경우 자식도 이동
        if (!child->is_leaf) {
            child->children.insert(child->children.begin(), sibling->children.back());
            sibling->children.pop_back();
        }
    }
    
    // 오른쪽 형제에서 키 가져오기
    void borrow_from_next(BTreeNode* node, int idx) {
        BTreeNode* child = node->children[idx];
        BTreeNode* sibling = node->children[idx + 1];
        
        // 부모의 키를 자식으로 이동
        child->keys.push_back(node->keys[idx]);
        
        // 형제의 첫 번째 키를 부모로 이동
        node->keys[idx] = sibling->keys[0];
        sibling->keys.erase(sibling->keys.begin());
        
        // 내부 노드인 경우 자식도 이동
        if (!child->is_leaf) {
            child->children.push_back(sibling->children[0]);
            sibling->children.erase(sibling->children.begin());
        }
    }
    
    // 두 자식 노드 병합
    void merge(BTreeNode* node, int idx) {
        BTreeNode* child = node->children[idx];
        BTreeNode* sibling = node->children[idx + 1];
        
        // 부모의 키를 자식으로 이동
        child->keys.push_back(node->keys[idx]);
        
        // 형제의 모든 키를 자식으로 이동
        child->keys.insert(child->keys.end(), sibling->keys.begin(), sibling->keys.end());
        
        // 내부 노드인 경우 자식들도 이동
        if (!child->is_leaf) {
            child->children.insert(child->children.end(), 
                                 sibling->children.begin(), sibling->children.end());
        }
        
        // 부모에서 키와 자식 제거
        node->keys.erase(node->keys.begin() + idx);
        node->children.erase(node->children.begin() + idx + 1);
        
        // 형제 노드 삭제 (자식들은 이동했으므로 직접 삭제)
        sibling->children.clear();  // 자식들 삭제 방지
        delete sibling;
    }
    
    // 삭제 헬퍼
    void remove_helper(BTreeNode* node, const T& key) {
        int idx = 0;
        while (idx < node->keys.size() && node->keys[idx] < key) {
            idx++;
        }
        
        if (idx < node->keys.size() && node->keys[idx] == key) {
            // 현재 노드에 키가 있음
            if (node->is_leaf) {
                // Case 1: 리프 노드에서 키 제거
                node->keys.erase(node->keys.begin() + idx);
            } else {
                // Case 2: 내부 노드에서 키 제거
                if (node->children[idx]->keys.size() >= min_degree) {
                    // Case 2a: 왼쪽 자식에 충분한 키가 있음
                    T pred = get_predecessor(node, idx);
                    node->keys[idx] = pred;
                    remove_helper(node->children[idx], pred);
                } else if (node->children[idx + 1]->keys.size() >= min_degree) {
                    // Case 2b: 오른쪽 자식에 충분한 키가 있음
                    T succ = get_successor(node, idx);
                    node->keys[idx] = succ;
                    remove_helper(node->children[idx + 1], succ);
                } else {
                    // Case 2c: 두 자식 모두 최소 키만 가지고 있음
                    merge(node, idx);
                    remove_helper(node->children[idx], key);
                }
            }
        } else {
            // Case 3: 키가 현재 노드에 없음
            if (node->is_leaf) {
                return;  // 키가 트리에 존재하지 않음
            }
            
            bool flag = (idx == node->keys.size());
            
            if (node->children[idx]->keys.size() < min_degree) {
                fill(node, idx);
            }
            
            if (flag && idx > node->keys.size()) {
                remove_helper(node->children[idx - 1], key);
            } else {
                remove_helper(node->children[idx], key);
            }
        }
    }
    
    // 중위 순회
    void inorder_helper(BTreeNode* node) {
        if (node == nullptr) return;
        
        int i;
        for (i = 0; i < node->keys.size(); i++) {
            if (!node->is_leaf) {
                inorder_helper(node->children[i]);
            }
            cout << node->keys[i] << " ";
        }
        
        if (!node->is_leaf) {
            inorder_helper(node->children[i]);
        }
    }
    
    // 트리 구조 출력
    void print_tree_helper(BTreeNode* node, int level) {
        if (node == nullptr) return;
        
        cout << "Level " << level << ": [";
        for (int i = 0; i < node->keys.size(); i++) {
            cout << node->keys[i];
            if (i < node->keys.size() - 1) cout << ", ";
        }
        cout << "]" << (node->is_leaf ? " (leaf)" : " (internal)") << endl;
        
        if (!node->is_leaf) {
            for (BTreeNode* child : node->children) {
                print_tree_helper(child, level + 1);
            }
        }
    }
    
    // 트리 높이 계산
    int height_helper(BTreeNode* node) {
        if (node == nullptr) return 0;
        if (node->is_leaf) return 1;
        return 1 + height_helper(node->children[0]);
    }
    
    // 노드 개수 계산
    int count_nodes(BTreeNode* node) {
        if (node == nullptr) return 0;
        
        int count = 1;
        if (!node->is_leaf) {
            for (BTreeNode* child : node->children) {
                count += count_nodes(child);
            }
        }
        return count;
    }
    
    // 키 개수 계산
    int count_keys(BTreeNode* node) {
        if (node == nullptr) return 0;
        
        int count = node->keys.size();
        if (!node->is_leaf) {
            for (BTreeNode* child : node->children) {
                count += count_keys(child);
            }
        }
        return count;
    }
    
public:
    // 생성자
    BTree(int degree) : min_degree(degree), root(nullptr) {
        if (degree < 2) {
            throw invalid_argument("Minimum degree must be at least 2");
        }
    }
    
    // 소멸자
    ~BTree() {
        delete root;
    }
    
    // 삽입
    void insert(const T& key) {
        if (root == nullptr) {
            root = new BTreeNode(true);
            root->keys.push_back(key);
        } else {
            if (is_full(root)) {
                // 루트가 가득 찬 경우 새 루트 생성
                BTreeNode* new_root = new BTreeNode(false);
                new_root->children.push_back(root);
                split_child(new_root, 0);
                root = new_root;
            }
            insert_non_full(root, key);
        }
    }
    
    // 검색
    bool search(const T& key) {
        return search_helper(root, key);
    }
    
    // 삭제
    void remove(const T& key) {
        if (root == nullptr) return;
        
        remove_helper(root, key);
        
        // 루트가 비어있으면 새 루트 설정
        if (root->keys.empty()) {
            BTreeNode* old_root = root;
            if (root->is_leaf) {
                root = nullptr;
            } else {
                root = root->children[0];
            }
            old_root->children.clear();  // 자식 삭제 방지
            delete old_root;
        }
    }
    
    // 중위 순회
    void inorder() {
        cout << "Inorder traversal: ";
        inorder_helper(root);
        cout << endl;
    }
    
    // 트리 구조 출력
    void print_tree() {
        cout << "B-Tree structure (degree " << min_degree << "):" << endl;
        if (root == nullptr) {
            cout << "Empty tree" << endl;
        } else {
            print_tree_helper(root, 0);
        }
        cout << endl;
    }
    
    // 트리가 비어있는지 확인
    bool empty() {
        return root == nullptr;
    }
    
    // 트리 높이
    int height() {
        return height_helper(root);
    }
    
    // 통계 정보
    void print_stats() {
        cout << "B-Tree Statistics:" << endl;
        cout << "- Minimum degree: " << min_degree << endl;
        cout << "- Height: " << height() << endl;
        cout << "- Number of nodes: " << count_nodes(root) << endl;
        cout << "- Number of keys: " << count_keys(root) << endl;
        cout << "- Max keys per node: " << (2 * min_degree - 1) << endl;
        cout << "- Min keys per node (except root): " << (min_degree - 1) << endl;
    }
};

// 사용 예제
int main() {
    cout << "=== B-Tree Example (degree 3) ===" << endl;
    
    BTree<int> btree(3);  // 차수 3인 B-트리
    
    // 데이터 삽입
    vector<int> values = {10, 20, 5, 6, 12, 30, 7, 17, 1, 2, 3, 4, 8, 9, 11, 13, 14, 15, 16, 18, 19};
    
    cout << "Inserting values: ";
    for (int val : values) {
        cout << val << " ";
        btree.insert(val);
    }
    cout << endl << endl;
    
    // 트리 구조 출력
    btree.print_tree();
    
    // 중위 순회 (정렬된 순서)
    btree.inorder();
    
    // 통계 정보
    btree.print_stats();
    
    // 검색 테스트
    cout << "\nSearch tests:" << endl;
    vector<int> search_values = {1, 15, 25, 30};
    for (int val : search_values) {
        cout << "Search " << val << ": " << (btree.search(val) ? "Found" : "Not found") << endl;
    }
    
    // 삭제 테스트
    cout << "\nDeleting values: 6, 13, 7, 4, 2..." << endl;
    vector<int> delete_values = {6, 13, 7, 4, 2};
    for (int val : delete_values) {
        cout << "Deleting " << val << "..." << endl;
        btree.remove(val);
        btree.print_tree();
    }
    
    cout << "Final inorder traversal: ";
    btree.inorder();
    
    cout << "\n=== B-Tree with different degree ===" << endl;
    
    BTree<int> btree5(5);  // 차수 5인 B-트리
    
    cout << "Inserting values into degree-5 B-Tree..." << endl;
    for (int i = 1; i <= 20; i++) {
        btree5.insert(i);
    }
    
    btree5.print_tree();
    btree5.print_stats();
    
    cout << "\n=== B-Tree Properties ===" << endl;
    cout << "B-Tree advantages:" << endl;
    cout << "- Optimized for disk I/O operations" << endl;
    cout << "- Guarantees logarithmic time complexity" << endl;
    cout << "- Self-balancing" << endl;
    cout << "- Efficient range queries" << endl;
    cout << "- Used in databases and file systems" << endl;
    
    return 0;
}