#include <iostream>
using namespace std;

/**
 * 레드-블랙 트리 (Red-Black Tree)
 * 자가 균형 이진 탐색 트리
 * 
 * 용도:
 * - 균형 잡힌 이진 탐색 트리가 필요한 모든 상황
 * - 삽입, 삭제, 검색이 모두 빈번한 동적 집합 관리
 * - 순서가 있는 데이터의 효율적 저장 및 조회
 * 
 * 특징:
 * - AVL 트리보다 느슨한 균형 조건으로 삽입/삭제가 더 빠름
 * - 최악의 경우에도 O(log n) 보장 (완전 이진 트리의 2배 높이 이하)
 * - 색깔 정보로 균형을 유지하여 메모리 오버헤드 최소
 * - 회전 연산이 AVL 트리보다 적음
 * 
 * 사용 시나리오:
 * - 삽입과 삭제가 검색보다 빈번한 경우
 * - 균형 잡힌 트리가 필요하지만 AVL 트리의 엄격한 균형이 과도한 경우
 * - 메모리 사용량을 최소화하면서 균형을 유지해야 하는 경우
 * 
 * 실제 사용 사례:
 * - C++ STL의 map, set, multimap, multiset
 * - Java의 TreeMap, TreeSet
 * - Linux 커널의 CFS(Completely Fair Scheduler) 스케줄러
 * - 많은 데이터베이스 시스템의 인덱스 구조
 * 
 * 원리:
 * - 노드에 색깔(빨강/검정) 정보를 추가하여 균형 조건 정의
 * - 5가지 색깔 규칙을 통해 트리의 균형 보장
 * - 삽입/삭제 시 색깔 변경과 회전을 통해 규칙 복원
 * - 검은 노드의 개수가 같다는 조건으로 높이 균형 유지
 * 
 * 레드-블랙 트리의 5가지 규칙:
 * 1. 모든 노드는 빨간색 또는 검은색이다.
 * 2. 루트는 검은색이다.
 * 3. 모든 리프(NIL)는 검은색이다.
 * 4. 빨간 노드의 자식은 모두 검은색이다. (빨간 노드는 연속으로 나타날 수 없다)
 * 5. 루트에서 모든 리프까지의 경로에서 검은 노드의 개수는 같다.
 * 
 * 시간복잡도: 삽입, 삭제, 검색 모두 O(log n)
 * 공간복잡도: O(n)
 */

enum Color { RED, BLACK };

template<typename T>
class RedBlackTree {
private:
    struct Node {
        T data;
        Color color;
        Node* left;
        Node* right;
        Node* parent;
        
        Node(const T& value) 
            : data(value), color(RED), left(nullptr), right(nullptr), parent(nullptr) {}
    };
    
    Node* root;
    Node* NIL;  // 센티넬 노드 (모든 리프를 대체)
    
    // 왼쪽 회전
    void rotate_left(Node* x) {
        Node* y = x->right;
        x->right = y->left;
        
        if (y->left != NIL) {
            y->left->parent = x;
        }
        
        y->parent = x->parent;
        
        if (x->parent == NIL) {
            root = y;
        } else if (x == x->parent->left) {
            x->parent->left = y;
        } else {
            x->parent->right = y;
        }
        
        y->left = x;
        x->parent = y;
    }
    
    // 오른쪽 회전
    void rotate_right(Node* y) {
        Node* x = y->left;
        y->left = x->right;
        
        if (x->right != NIL) {
            x->right->parent = y;
        }
        
        x->parent = y->parent;
        
        if (y->parent == NIL) {
            root = x;
        } else if (y == y->parent->left) {
            y->parent->left = x;
        } else {
            y->parent->right = x;
        }
        
        x->right = y;
        y->parent = x;
    }
    
    // 삽입 후 레드-블랙 트리 속성 복원
    void insert_fixup(Node* z) {
        while (z->parent->color == RED) {
            if (z->parent == z->parent->parent->left) {
                Node* y = z->parent->parent->right;  // 삼촌 노드
                
                if (y->color == RED) {
                    // Case 1: 삼촌이 빨간색
                    z->parent->color = BLACK;
                    y->color = BLACK;
                    z->parent->parent->color = RED;
                    z = z->parent->parent;
                } else {
                    if (z == z->parent->right) {
                        // Case 2: 삼촌은 검은색, z는 오른쪽 자식
                        z = z->parent;
                        rotate_left(z);
                    }
                    // Case 3: 삼촌은 검은색, z는 왼쪽 자식
                    z->parent->color = BLACK;
                    z->parent->parent->color = RED;
                    rotate_right(z->parent->parent);
                }
            } else {
                // 대칭적인 경우
                Node* y = z->parent->parent->left;  // 삼촌 노드
                
                if (y->color == RED) {
                    // Case 1: 삼촌이 빨간색
                    z->parent->color = BLACK;
                    y->color = BLACK;
                    z->parent->parent->color = RED;
                    z = z->parent->parent;
                } else {
                    if (z == z->parent->left) {
                        // Case 2: 삼촌은 검은색, z는 왼쪽 자식
                        z = z->parent;
                        rotate_right(z);
                    }
                    // Case 3: 삼촌은 검은색, z는 오른쪽 자식
                    z->parent->color = BLACK;
                    z->parent->parent->color = RED;
                    rotate_left(z->parent->parent);
                }
            }
        }
        root->color = BLACK;  // 루트는 항상 검은색
    }
    
    // 노드를 다른 노드로 교체
    void transplant(Node* u, Node* v) {
        if (u->parent == NIL) {
            root = v;
        } else if (u == u->parent->left) {
            u->parent->left = v;
        } else {
            u->parent->right = v;
        }
        v->parent = u->parent;
    }
    
    // 최솟값 노드 찾기
    Node* minimum(Node* node) {
        while (node->left != NIL) {
            node = node->left;
        }
        return node;
    }
    
    // 삭제 후 레드-블랙 트리 속성 복원
    void delete_fixup(Node* x) {
        while (x != root && x->color == BLACK) {
            if (x == x->parent->left) {
                Node* w = x->parent->right;  // 형제 노드
                
                if (w->color == RED) {
                    // Case 1: 형제가 빨간색
                    w->color = BLACK;
                    x->parent->color = RED;
                    rotate_left(x->parent);
                    w = x->parent->right;
                }
                
                if (w->left->color == BLACK && w->right->color == BLACK) {
                    // Case 2: 형제의 두 자식이 모두 검은색
                    w->color = RED;
                    x = x->parent;
                } else {
                    if (w->right->color == BLACK) {
                        // Case 3: 형제의 왼쪽 자식은 빨간색, 오른쪽 자식은 검은색
                        w->left->color = BLACK;
                        w->color = RED;
                        rotate_right(w);
                        w = x->parent->right;
                    }
                    // Case 4: 형제의 오른쪽 자식이 빨간색
                    w->color = x->parent->color;
                    x->parent->color = BLACK;
                    w->right->color = BLACK;
                    rotate_left(x->parent);
                    x = root;
                }
            } else {
                // 대칭적인 경우
                Node* w = x->parent->left;  // 형제 노드
                
                if (w->color == RED) {
                    // Case 1: 형제가 빨간색
                    w->color = BLACK;
                    x->parent->color = RED;
                    rotate_right(x->parent);
                    w = x->parent->left;
                }
                
                if (w->right->color == BLACK && w->left->color == BLACK) {
                    // Case 2: 형제의 두 자식이 모두 검은색
                    w->color = RED;
                    x = x->parent;
                } else {
                    if (w->left->color == BLACK) {
                        // Case 3: 형제의 오른쪽 자식은 빨간색, 왼쪽 자식은 검은색
                        w->right->color = BLACK;
                        w->color = RED;
                        rotate_left(w);
                        w = x->parent->left;
                    }
                    // Case 4: 형제의 왼쪽 자식이 빨간색
                    w->color = x->parent->color;
                    x->parent->color = BLACK;
                    w->left->color = BLACK;
                    rotate_right(x->parent);
                    x = root;
                }
            }
        }
        x->color = BLACK;
    }
    
    // 중위 순회 출력
    void inorder_helper(Node* node) const {
        if (node != NIL) {
            inorder_helper(node->left);
            cout << node->data << "(" << (node->color == RED ? "R" : "B") << ") ";
            inorder_helper(node->right);
        }
    }
    
    // 트리 구조 출력
    void print_tree_helper(Node* node, int depth) const {
        if (node != NIL) {
            print_tree_helper(node->right, depth + 1);
            
            for (int i = 0; i < depth; i++) {
                cout << "    ";
            }
            cout << node->data << "(" << (node->color == RED ? "R" : "B") << ")" << endl;
            
            print_tree_helper(node->left, depth + 1);
        }
    }
    
    // 메모리 해제
    void destroy_tree(Node* node) {
        if (node != NIL) {
            destroy_tree(node->left);
            destroy_tree(node->right);
            delete node;
        }
    }
    
    // 검은 높이 검증 (디버깅용)
    int verify_black_height(Node* node) const {
        if (node == NIL) {
            return 1;  // NIL 노드는 검은색으로 카운트
        }
        
        int left_height = verify_black_height(node->left);
        int right_height = verify_black_height(node->right);
        
        if (left_height == -1 || right_height == -1 || left_height != right_height) {
            return -1;  // 속성 위반
        }
        
        return left_height + (node->color == BLACK ? 1 : 0);
    }
    
public:
    // 생성자
    RedBlackTree() {
        NIL = new Node(T());  // 센티넬 노드
        NIL->color = BLACK;
        NIL->left = NIL->right = NIL->parent = NIL;
        root = NIL;
    }
    
    // 소멸자
    ~RedBlackTree() {
        destroy_tree(root);
        delete NIL;
    }
    
    // 삽입
    void insert(const T& value) {
        Node* z = new Node(value);
        z->left = z->right = NIL;
        
        Node* y = NIL;
        Node* x = root;
        
        // 삽입 위치 찾기
        while (x != NIL) {
            y = x;
            if (z->data < x->data) {
                x = x->left;
            } else {
                x = x->right;
            }
        }
        
        z->parent = y;
        
        if (y == NIL) {
            root = z;  // 트리가 비어있음
        } else if (z->data < y->data) {
            y->left = z;
        } else {
            y->right = z;
        }
        
        // 새 노드는 빨간색으로 시작
        z->color = RED;
        
        // 레드-블랙 트리 속성 복원
        insert_fixup(z);
    }
    
    // 검색
    bool search(const T& value) const {
        Node* current = root;
        
        while (current != NIL) {
            if (value == current->data) {
                return true;
            } else if (value < current->data) {
                current = current->left;
            } else {
                current = current->right;
            }
        }
        
        return false;
    }
    
    // 삭제
    void remove(const T& value) {
        Node* z = root;
        
        // 삭제할 노드 찾기
        while (z != NIL && z->data != value) {
            if (value < z->data) {
                z = z->left;
            } else {
                z = z->right;
            }
        }
        
        if (z == NIL) {
            return;  // 노드를 찾지 못함
        }
        
        Node* y = z;
        Node* x;
        Color y_original_color = y->color;
        
        if (z->left == NIL) {
            x = z->right;
            transplant(z, z->right);
        } else if (z->right == NIL) {
            x = z->left;
            transplant(z, z->left);
        } else {
            y = minimum(z->right);
            y_original_color = y->color;
            x = y->right;
            
            if (y->parent == z) {
                x->parent = y;
            } else {
                transplant(y, y->right);
                y->right = z->right;
                y->right->parent = y;
            }
            
            transplant(z, y);
            y->left = z->left;
            y->left->parent = y;
            y->color = z->color;
        }
        
        delete z;
        
        // 삭제된 노드가 검은색이었다면 속성 복원
        if (y_original_color == BLACK) {
            delete_fixup(x);
        }
    }
    
    // 중위 순회
    void inorder() const {
        cout << "Inorder traversal: ";
        inorder_helper(root);
        cout << endl;
    }
    
    // 트리 구조 출력
    void print_tree() const {
        cout << "Tree structure:" << endl;
        if (root == NIL) {
            cout << "Empty tree" << endl;
        } else {
            print_tree_helper(root, 0);
        }
        cout << endl;
    }
    
    // 트리가 비어있는지 확인
    bool empty() const {
        return root == NIL;
    }
    
    // 최솟값
    T find_min() const {
        if (root == NIL) {
            throw runtime_error("Tree is empty");
        }
        
        Node* current = root;
        while (current->left != NIL) {
            current = current->left;
        }
        return current->data;
    }
    
    // 최댓값
    T find_max() const {
        if (root == NIL) {
            throw runtime_error("Tree is empty");
        }
        
        Node* current = root;
        while (current->right != NIL) {
            current = current->right;
        }
        return current->data;
    }
    
    // 레드-블랙 트리 속성 검증
    bool verify_properties() const {
        if (root == NIL) {
            return true;
        }
        
        // 루트가 검은색인지 확인
        if (root->color != BLACK) {
            cout << "Property violation: Root is not black" << endl;
            return false;
        }
        
        // 검은 높이가 일정한지 확인
        int black_height = verify_black_height(root);
        if (black_height == -1) {
            cout << "Property violation: Black heights are not equal" << endl;
            return false;
        }
        
        cout << "All Red-Black Tree properties are satisfied." << endl;
        cout << "Black height: " << black_height << endl;
        return true;
    }
};

// 사용 예제
int main() {
    cout << "=== Red-Black Tree Example ===" << endl;
    
    RedBlackTree<int> rbt;
    
    // 데이터 삽입
    int values[] = {10, 20, 30, 15, 25, 5, 1, 35, 40};
    cout << "Inserting values: ";
    for (int val : values) {
        cout << val << " ";
        rbt.insert(val);
    }
    cout << endl << endl;
    
    // 트리 구조 출력
    rbt.print_tree();
    
    // 중위 순회 (정렬된 순서로 출력됨)
    rbt.inorder();
    
    // 검색 테스트
    cout << "\nSearch tests:" << endl;
    cout << "Search 15: " << (rbt.search(15) ? "Found" : "Not found") << endl;
    cout << "Search 100: " << (rbt.search(100) ? "Found" : "Not found") << endl;
    
    // 최솟값, 최댓값
    cout << "\nMin value: " << rbt.find_min() << endl;
    cout << "Max value: " << rbt.find_max() << endl;
    
    // 속성 검증
    cout << "\nVerifying Red-Black Tree properties:" << endl;
    rbt.verify_properties();
    
    // 노드 삭제
    cout << "\nDeleting nodes: 20, 30, 15" << endl;
    rbt.remove(20);
    rbt.remove(30);
    rbt.remove(15);
    
    cout << "\nAfter deletion:" << endl;
    rbt.print_tree();
    rbt.inorder();
    
    // 삭제 후 속성 검증
    cout << "\nVerifying properties after deletion:" << endl;
    rbt.verify_properties();
    
    return 0;
}