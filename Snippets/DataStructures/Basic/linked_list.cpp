#include <iostream>
using namespace std;

/**
 * 연결 리스트 (Linked List)
 * 각 노드가 데이터와 다음 노드의 주소를 저장하는 선형 자료구조
 * 동적 메모리 할당, 삽입/삭제가 효율적
 */
template<typename T>
class LinkedList {
private:
    struct Node {
        T data;
        Node* next;
        
        Node(const T& value) : data(value), next(nullptr) {}
    };
    
    Node* head;
    Node* tail;
    size_t list_size;
    
public:
    LinkedList() : head(nullptr), tail(nullptr), list_size(0) {}
    
    // 소멸자 - 메모리 해제
    ~LinkedList() {
        clear();
    }
    
    // 리스트 맨 앞에 노드 추가
    void push_front(const T& value) {
        Node* new_node = new Node(value);
        if (head == nullptr) {
            head = tail = new_node;
        } else {
            new_node->next = head;
            head = new_node;
        }
        list_size++;
    }
    
    // 리스트 맨 뒤에 노드 추가
    void push_back(const T& value) {
        Node* new_node = new Node(value);
        if (tail == nullptr) {
            head = tail = new_node;
        } else {
            tail->next = new_node;
            tail = new_node;
        }
        list_size++;
    }
    
    // 특정 위치에 노드 삽입
    void insert(size_t index, const T& value) {
        if (index == 0) {
            push_front(value);
            return;
        }
        if (index >= list_size) {
            push_back(value);
            return;
        }
        
        Node* new_node = new Node(value);
        Node* current = head;
        for (size_t i = 0; i < index - 1; i++) {
            current = current->next;
        }
        new_node->next = current->next;
        current->next = new_node;
        list_size++;
    }
    
    // 맨 앞 노드 제거
    void pop_front() {
        if (head == nullptr) return;
        
        Node* temp = head;
        head = head->next;
        if (head == nullptr) {
            tail = nullptr;
        }
        delete temp;
        list_size--;
    }
    
    // 특정 값을 가진 노드 제거
    bool remove(const T& value) {
        if (head == nullptr) return false;
        
        if (head->data == value) {
            pop_front();
            return true;
        }
        
        Node* current = head;
        while (current->next != nullptr && current->next->data != value) {
            current = current->next;
        }
        
        if (current->next != nullptr) {
            Node* temp = current->next;
            current->next = temp->next;
            if (temp == tail) {
                tail = current;
            }
            delete temp;
            list_size--;
            return true;
        }
        return false;
    }
    
    // 특정 값 검색
    bool find(const T& value) const {
        Node* current = head;
        while (current != nullptr) {
            if (current->data == value) {
                return true;
            }
            current = current->next;
        }
        return false;
    }
    
    // 리스트 크기 반환
    size_t size() const {
        return list_size;
    }
    
    // 리스트가 비어있는지 확인
    bool empty() const {
        return head == nullptr;
    }
    
    // 모든 노드 제거
    void clear() {
        while (head != nullptr) {
            Node* temp = head;
            head = head->next;
            delete temp;
        }
        tail = nullptr;
        list_size = 0;
    }
    
    // 리스트 출력
    void print() const {
        Node* current = head;
        while (current != nullptr) {
            cout << current->data << " ";
            current = current->next;
        }
        cout << endl;
    }
};

// 사용 예제
int main() {
    LinkedList<int> list;
    
    // 데이터 추가
    list.push_back(10);
    list.push_back(20);
    list.push_front(5);
    list.insert(2, 15);
    
    cout << "List: ";
    list.print();
    
    cout << "Size: " << list.size() << endl;
    cout << "Find 15: " << (list.find(15) ? "Found" : "Not found") << endl;
    
    // 노드 제거
    list.remove(15);
    cout << "After removing 15: ";
    list.print();
    
    return 0;
}