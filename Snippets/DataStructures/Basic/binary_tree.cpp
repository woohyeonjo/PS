#include <iostream>
#include <queue>
using namespace std;

/**
 * 이진 트리 (Binary Tree)
 * 각 노드가 최대 2개의 자식을 가지는 트리 구조
 * 계층적 데이터 저장, 검색 트리 등에 사용
 */
template<typename T>
class BinaryTree {
private:
    struct Node {
        T data;
        Node* left;
        Node* right;
        
        Node(const T& value) : data(value), left(nullptr), right(nullptr) {}
    };
    
    Node* root;
    
    // 전위 순회 (재귀)
    void preorder_helper(Node* node) const {
        if (node != nullptr) {
            cout << node->data << " ";
            preorder_helper(node->left);
            preorder_helper(node->right);
        }
    }
    
    // 중위 순회 (재귀)
    void inorder_helper(Node* node) const {
        if (node != nullptr) {
            inorder_helper(node->left);
            cout << node->data << " ";
            inorder_helper(node->right);
        }
    }
    
    // 후위 순회 (재귀)
    void postorder_helper(Node* node) const {
        if (node != nullptr) {
            postorder_helper(node->left);
            postorder_helper(node->right);
            cout << node->data << " ";
        }
    }
    
    // 트리 높이 계산 (재귀)
    int height_helper(Node* node) const {
        if (node == nullptr) return 0;
        return 1 + max(height_helper(node->left), height_helper(node->right));
    }
    
    // 메모리 해제 (재귀)
    void clear_helper(Node* node) {
        if (node != nullptr) {
            clear_helper(node->left);
            clear_helper(node->right);
            delete node;
        }
    }
    
public:
    BinaryTree() : root(nullptr) {}
    
    // 소멸자
    ~BinaryTree() {
        clear();
    }
    
    // 루트 설정
    void set_root(const T& value) {
        if (root != nullptr) {
            delete root;
        }
        root = new Node(value);
    }
    
    // 특정 값을 가진 노드에 왼쪽 자식 추가
    bool add_left_child(const T& parent_value, const T& child_value) {
        Node* parent = find_node(root, parent_value);
        if (parent != nullptr && parent->left == nullptr) {
            parent->left = new Node(child_value);
            return true;
        }
        return false;
    }
    
    // 특정 값을 가진 노드에 오른쪽 자식 추가
    bool add_right_child(const T& parent_value, const T& child_value) {
        Node* parent = find_node(root, parent_value);
        if (parent != nullptr && parent->right == nullptr) {
            parent->right = new Node(child_value);
            return true;
        }
        return false;
    }
    
    // 노드 찾기
    Node* find_node(Node* node, const T& value) const {
        if (node == nullptr) return nullptr;
        if (node->data == value) return node;
        
        Node* left_result = find_node(node->left, value);
        if (left_result != nullptr) return left_result;
        
        return find_node(node->right, value);
    }
    
    // 전위 순회 (Root -> Left -> Right)
    void preorder() const {
        cout << "Preorder: ";
        preorder_helper(root);
        cout << endl;
    }
    
    // 중위 순회 (Left -> Root -> Right)
    void inorder() const {
        cout << "Inorder: ";
        inorder_helper(root);
        cout << endl;
    }
    
    // 후위 순회 (Left -> Right -> Root)
    void postorder() const {
        cout << "Postorder: ";
        postorder_helper(root);
        cout << endl;
    }
    
    // 레벨 순회 (BFS)
    void level_order() const {
        if (root == nullptr) return;
        
        cout << "Level order: ";
        queue<Node*> q;
        q.push(root);
        
        while (!q.empty()) {
            Node* current = q.front();
            q.pop();
            
            cout << current->data << " ";
            
            if (current->left != nullptr) {
                q.push(current->left);
            }
            if (current->right != nullptr) {
                q.push(current->right);
            }
        }
        cout << endl;
    }
    
    // 트리 높이
    int height() const {
        return height_helper(root);
    }
    
    // 트리 비우기
    void clear() {
        clear_helper(root);
        root = nullptr;
    }
    
    // 트리가 비어있는지 확인
    bool empty() const {
        return root == nullptr;
    }
};

// 사용 예제
int main() {
    BinaryTree<int> tree;
    
    // 트리 구성
    tree.set_root(1);
    tree.add_left_child(1, 2);
    tree.add_right_child(1, 3);
    tree.add_left_child(2, 4);
    tree.add_right_child(2, 5);
    tree.add_left_child(3, 6);
    tree.add_right_child(3, 7);
    
    // 순회 방법들
    tree.preorder();    // 1 2 4 5 3 6 7
    tree.inorder();     // 4 2 5 1 6 3 7
    tree.postorder();   // 4 5 2 6 7 3 1
    tree.level_order(); // 1 2 3 4 5 6 7
    
    cout << "Tree height: " << tree.height() << endl;
    
    return 0;
}