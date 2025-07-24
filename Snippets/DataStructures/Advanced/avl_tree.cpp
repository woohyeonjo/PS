#include <iostream>
#include <algorithm>
using namespace std;

/**
 * AVL 트리 (Adelson-Velsky and Landis Tree)
 * 자가 균형 이진 탐색 트리
 * 
 * 용도:
 * - 검색이 매우 빈번한 응용에서 최적의 성능 보장
 * - 높이 균형이 엄격히 요구되는 실시간 시스템
 * - 범위 검색이나 순차 접근이 중요한 데이터베이스 인덱스
 * 
 * 특징:
 * - 가장 엄격한 균형 조건을 가진 이진 탐색 트리
 * - 레드-블랙 트리보다 검색이 빠르지만 삽입/삭제는 느림
 * - 높이가 항상 O(log n)으로 보장 (피보나치 수열 기반)
 * - 각 노드에 높이 정보 저장으로 약간의 메모리 오버헤드
 * - 회전 연산이 레드-블랙 트리보다 많을 수 있음
 * 
 * 사용 시나리오:
 * - 검색 연산이 삽입/삭제보다 훨씬 빈번한 경우
 * - 실시간 시스템에서 최악의 경우 성능이 중요한경우
 * - 트리의 높이가 중요한 메모리 제약 환경
 * - 범위 쿼리나 순차 접근이 자주 필요한 경우
 * 
 * 실제 사용 사례:
 * - 데이터베이스 시스템의 인덱스 (MySQL, PostgreSQL의 일부)
 * - CAD/CAM 시스템의 공간 분할
 * - 컴파일러의 심볼 테이블
 * - 게임 엔진의 공간 분할 구조
 * - 메모리 관리 시스템 (가용 블록 관리)
 * 
 * 원리:
 * - 각 노드의 균형 인수(Balance Factor)를 -1, 0, 1로 유지
 * - 삽입/삭제 후 균형이 깨지면 회전을 통해 복원
 * - 4가지 회전 케이스: LL, RR, LR, RL
 * - 높이 정보를 이용한 효율적인 균형 검사
 * - 피보나치 수열 성질로 최소 높이 보장
 * 
 * AVL 트리의 균형 조건:
 * - 모든 노드에서 왼쪽 서브트리와 오른쪽 서브트리의 높이 차이가 최대 1
 * - 균형 인수(Balance Factor) = 왼쪽 서브트리 높이 - 오른쪽 서브트리 높이
 * - 균형 인수는 -1, 0, 1 중 하나여야 함
 * 
 * 시간복잡도: 삽입, 삭제, 검색 모두 O(log n)
 * 공간복잡도: O(n)
 * 레드-블랙 트리보다 더 엄격하게 균형을 유지하여 검색이 약간 더 빠름
 */

template<typename T>
class AVLTree {
private:
    struct Node {
        T data;
        int height;
        Node* left;
        Node* right;
        
        Node(const T& value) 
            : data(value), height(1), left(nullptr), right(nullptr) {}
    };
    
    Node* root;
    
    // 노드의 높이 반환
    int get_height(Node* node) {
        return node ? node->height : 0;
    }
    
    // 균형 인수 계산
    int get_balance_factor(Node* node) {
        return node ? get_height(node->left) - get_height(node->right) : 0;
    }
    
    // 노드의 높이 업데이트
    void update_height(Node* node) {
        if (node) {
            node->height = 1 + max(get_height(node->left), get_height(node->right));
        }
    }
    
    // 오른쪽 회전 (Right Rotation)
    Node* rotate_right(Node* y) {
        Node* x = y->left;
        Node* T2 = x->right;
        
        // 회전 수행
        x->right = y;
        y->left = T2;
        
        // 높이 업데이트
        update_height(y);
        update_height(x);
        
        return x;  // 새로운 루트
    }
    
    // 왼쪽 회전 (Left Rotation)
    Node* rotate_left(Node* x) {
        Node* y = x->right;
        Node* T2 = y->left;
        
        // 회전 수행
        y->left = x;
        x->right = T2;
        
        // 높이 업데이트
        update_height(x);
        update_height(y);
        
        return y;  // 새로운 루트
    }
    
    // 삽입 (재귀)
    Node* insert_helper(Node* node, const T& value) {
        // 1. 일반적인 BST 삽입
        if (node == nullptr) {
            return new Node(value);
        }
        
        if (value < node->data) {
            node->left = insert_helper(node->left, value);
        } else if (value > node->data) {
            node->right = insert_helper(node->right, value);
        } else {
            return node;  // 중복 키는 허용하지 않음
        }
        
        // 2. 높이 업데이트
        update_height(node);
        
        // 3. 균형 인수 계산
        int balance = get_balance_factor(node);
        
        // 4. 불균형인 경우 회전 수행
        
        // Left-Left Case
        if (balance > 1 && value < node->left->data) {
            return rotate_right(node);
        }
        
        // Right-Right Case
        if (balance < -1 && value > node->right->data) {
            return rotate_left(node);
        }
        
        // Left-Right Case
        if (balance > 1 && value > node->left->data) {
            node->left = rotate_left(node->left);
            return rotate_right(node);
        }
        
        // Right-Left Case
        if (balance < -1 && value < node->right->data) {
            node->right = rotate_right(node->right);
            return rotate_left(node);
        }
        
        return node;  // 균형이 맞는 경우
    }
    
    // 최솟값 노드 찾기
    Node* find_min_node(Node* node) {
        while (node->left != nullptr) {
            node = node->left;
        }
        return node;
    }
    
    // 삭제 (재귀)
    Node* delete_helper(Node* node, const T& value) {
        // 1. 일반적인 BST 삭제
        if (node == nullptr) {
            return node;
        }
        
        if (value < node->data) {
            node->left = delete_helper(node->left, value);
        } else if (value > node->data) {
            node->right = delete_helper(node->right, value);
        } else {
            // 삭제할 노드를 찾음
            if (node->left == nullptr || node->right == nullptr) {
                Node* temp = node->left ? node->left : node->right;
                
                if (temp == nullptr) {
                    // 자식이 없는 경우
                    temp = node;
                    node = nullptr;
                } else {
                    // 자식이 하나인 경우
                    *node = *temp;
                }
                delete temp;
            } else {
                // 자식이 둘인 경우: 오른쪽 서브트리의 최솟값으로 대체
                Node* temp = find_min_node(node->right);
                node->data = temp->data;
                node->right = delete_helper(node->right, temp->data);
            }
        }
        
        if (node == nullptr) {
            return node;
        }
        
        // 2. 높이 업데이트
        update_height(node);
        
        // 3. 균형 인수 계산
        int balance = get_balance_factor(node);
        
        // 4. 불균형인 경우 회전 수행
        
        // Left-Left Case
        if (balance > 1 && get_balance_factor(node->left) >= 0) {
            return rotate_right(node);
        }
        
        // Left-Right Case
        if (balance > 1 && get_balance_factor(node->left) < 0) {
            node->left = rotate_left(node->left);
            return rotate_right(node);
        }
        
        // Right-Right Case
        if (balance < -1 && get_balance_factor(node->right) <= 0) {
            return rotate_left(node);
        }
        
        // Right-Left Case
        if (balance < -1 && get_balance_factor(node->right) > 0) {
            node->right = rotate_right(node->right);
            return rotate_left(node);
        }
        
        return node;
    }
    
    // 검색 (재귀)
    bool search_helper(Node* node, const T& value) const {
        if (node == nullptr) {
            return false;
        }
        
        if (value == node->data) {
            return true;
        } else if (value < node->data) {
            return search_helper(node->left, value);
        } else {
            return search_helper(node->right, value);
        }
    }
    
    // 중위 순회
    void inorder_helper(Node* node) const {
        if (node != nullptr) {
            inorder_helper(node->left);
            cout << node->data << "(" << node->height << ") ";
            inorder_helper(node->right);
        }
    }
    
    // 전위 순회 (균형 인수 포함)
    void preorder_helper(Node* node) const {
        if (node != nullptr) {
            cout << node->data << "[BF:" << get_balance_factor(node) << "] ";
            preorder_helper(node->left);
            preorder_helper(node->right);
        }
    }
    
    // 트리 구조 출력
    void print_tree_helper(Node* node, int depth) const {
        if (node != nullptr) {
            print_tree_helper(node->right, depth + 1);
            
            for (int i = 0; i < depth; i++) {
                cout << "    ";
            }
            cout << node->data << "[h:" << node->height 
                 << ",bf:" << get_balance_factor(node) << "]" << endl;
            
            print_tree_helper(node->left, depth + 1);
        }
    }
    
    // 메모리 해제
    void destroy_tree(Node* node) {
        if (node != nullptr) {
            destroy_tree(node->left);
            destroy_tree(node->right);
            delete node;
        }
    }
    
    // AVL 속성 검증
    bool verify_avl_property(Node* node) const {
        if (node == nullptr) {
            return true;
        }
        
        int balance = get_balance_factor(node);
        if (balance < -1 || balance > 1) {
            cout << "AVL property violation at node " << node->data 
                 << " (balance factor: " << balance << ")" << endl;
            return false;
        }
        
        return verify_avl_property(node->left) && verify_avl_property(node->right);
    }
    
    // 노드 개수 계산
    int count_nodes(Node* node) const {
        if (node == nullptr) {
            return 0;
        }
        return 1 + count_nodes(node->left) + count_nodes(node->right);
    }
    
public:
    // 생성자
    AVLTree() : root(nullptr) {}
    
    // 소멸자
    ~AVLTree() {
        destroy_tree(root);
    }
    
    // 삽입
    void insert(const T& value) {
        root = insert_helper(root, value);
    }
    
    // 삭제
    void remove(const T& value) {
        root = delete_helper(root, value);
    }
    
    // 검색
    bool search(const T& value) const {
        return search_helper(root, value);
    }
    
    // 중위 순회
    void inorder() const {
        cout << "Inorder traversal (value(height)): ";
        inorder_helper(root);
        cout << endl;
    }
    
    // 전위 순회 (균형 인수 포함)
    void preorder() const {
        cout << "Preorder traversal (value[BF:balance_factor]): ";
        preorder_helper(root);
        cout << endl;
    }
    
    // 트리 구조 출력
    void print_tree() const {
        cout << "AVL Tree structure (value[h:height,bf:balance_factor]):" << endl;
        if (root == nullptr) {
            cout << "Empty tree" << endl;
        } else {
            print_tree_helper(root, 0);
        }
        cout << endl;
    }
    
    // 트리가 비어있는지 확인
    bool empty() const {
        return root == nullptr;
    }
    
    // 트리 높이
    int height() const {
        return get_height(root);
    }
    
    // 노드 개수
    int size() const {
        return count_nodes(root);
    }
    
    // 최솟값
    T find_min() const {
        if (root == nullptr) {
            throw runtime_error("Tree is empty");
        }
        
        Node* current = root;
        while (current->left != nullptr) {
            current = current->left;
        }
        return current->data;
    }
    
    // 최댓값
    T find_max() const {
        if (root == nullptr) {
            throw runtime_error("Tree is empty");
        }
        
        Node* current = root;
        while (current->right != nullptr) {
            current = current->right;
        }
        return current->data;
    }
    
    // AVL 속성 검증
    bool verify_avl_property() const {
        cout << "Verifying AVL property..." << endl;
        bool is_valid = verify_avl_property(root);
        if (is_valid) {
            cout << "AVL property is satisfied." << endl;
        }
        return is_valid;
    }
    
    // 트리 통계 출력
    void print_stats() const {
        cout << "AVL Tree Statistics:" << endl;
        cout << "- Size: " << size() << " nodes" << endl;
        cout << "- Height: " << height() << endl;
        cout << "- Theoretical min height: " << static_cast<int>(log2(size() + 1)) << endl;
        cout << "- Balance efficiency: " << (size() > 0 ? 
            static_cast<double>(static_cast<int>(log2(size() + 1))) / height() * 100 : 0) 
            << "%" << endl;
    }
};

// 사용 예제
int main() {
    cout << "=== AVL Tree Example ===" << endl;
    
    AVLTree<int> avl;
    
    // 순차적 삽입 (불균형을 유발할 수 있는 패턴)
    cout << "Inserting values sequentially: 1, 2, 3, 4, 5, 6, 7" << endl;
    for (int i = 1; i <= 7; i++) {
        cout << "Inserting " << i << "..." << endl;
        avl.insert(i);
        avl.print_tree();
    }
    
    // 순회 및 통계
    avl.inorder();
    avl.preorder();
    avl.print_stats();
    avl.verify_avl_property();
    
    cout << "\n=== Random Insertion Test ===" << endl;
    AVLTree<int> avl2;
    
    int random_values[] = {50, 30, 70, 20, 40, 60, 80, 10, 25, 35, 45};
    cout << "Inserting values: ";
    for (int val : random_values) {
        cout << val << " ";
        avl2.insert(val);
    }
    cout << endl;
    
    avl2.print_tree();
    avl2.inorder();
    avl2.print_stats();
    
    // 검색 테스트
    cout << "\nSearch tests:" << endl;
    cout << "Search 25: " << (avl2.search(25) ? "Found" : "Not found") << endl;
    cout << "Search 100: " << (avl2.search(100) ? "Found" : "Not found") << endl;
    
    // 최솟값, 최댓값
    cout << "Min value: " << avl2.find_min() << endl;
    cout << "Max value: " << avl2.find_max() << endl;
    
    // 삭제 테스트
    cout << "\nDeleting nodes: 30, 50, 70" << endl;
    avl2.remove(30);
    cout << "After deleting 30:" << endl;
    avl2.print_tree();
    
    avl2.remove(50);
    cout << "After deleting 50:" << endl;
    avl2.print_tree();
    
    avl2.remove(70);
    cout << "After deleting 70:" << endl;
    avl2.print_tree();
    
    avl2.inorder();
    avl2.verify_avl_property();
    
    return 0;
}