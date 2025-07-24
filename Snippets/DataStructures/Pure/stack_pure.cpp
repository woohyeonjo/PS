#include <iostream>
using namespace std;

/**
 * 순수 C++ 스택 구현 (STL 없이)
 * 배열 기반과 연결 리스트 기반 두 가지 구현
 */

/**
 * 1. 배열 기반 스택
 * 고정 크기 배열을 사용한 스택 구현
 */
template<typename T>
class ArrayStack {
private:
    T* data;
    int capacity;
    int top_index;
    
public:
    ArrayStack(int cap = 1000) : capacity(cap), top_index(-1) {
        data = new T[capacity];
    }
    
    ~ArrayStack() {
        delete[] data;
    }
    
    // 복사 생성자
    ArrayStack(const ArrayStack& other) {
        capacity = other.capacity;
        top_index = other.top_index;
        data = new T[capacity];
        for (int i = 0; i <= top_index; i++) {
            data[i] = other.data[i];
        }
    }
    
    // 대입 연산자
    ArrayStack& operator=(const ArrayStack& other) {
        if (this != &other) {
            delete[] data;
            capacity = other.capacity;
            top_index = other.top_index;
            data = new T[capacity];
            for (int i = 0; i <= top_index; i++) {
                data[i] = other.data[i];
            }
        }
        return *this;
    }
    
    void push(const T& item) {
        if (is_full()) {
            throw runtime_error("Stack overflow");
        }
        data[++top_index] = item;
    }
    
    T pop() {
        if (is_empty()) {
            throw runtime_error("Stack underflow");
        }
        return data[top_index--];
    }
    
    T top() const {
        if (is_empty()) {
            throw runtime_error("Stack is empty");
        }
        return data[top_index];
    }
    
    bool is_empty() const {
        return top_index == -1;
    }
    
    bool is_full() const {
        return top_index == capacity - 1;
    }
    
    int size() const {
        return top_index + 1;
    }
    
    void print() const {
        cout << "Stack (bottom to top): ";
        for (int i = 0; i <= top_index; i++) {
            cout << data[i] << " ";
        }
        cout << endl;
    }
};

/**
 * 2. 동적 배열 기반 스택
 * 크기가 자동으로 조절되는 스택
 */
template<typename T>
class DynamicStack {
private:
    T* data;
    int capacity;
    int top_index;
    
    void resize() {
        int new_capacity = capacity * 2;
        T* new_data = new T[new_capacity];
        
        for (int i = 0; i <= top_index; i++) {
            new_data[i] = data[i];
        }
        
        delete[] data;
        data = new_data;
        capacity = new_capacity;
    }
    
public:
    DynamicStack(int initial_cap = 4) : capacity(initial_cap), top_index(-1) {
        data = new T[capacity];
    }
    
    ~DynamicStack() {
        delete[] data;
    }
    
    // 복사 생성자
    DynamicStack(const DynamicStack& other) {
        capacity = other.capacity;
        top_index = other.top_index;
        data = new T[capacity];
        for (int i = 0; i <= top_index; i++) {
            data[i] = other.data[i];
        }
    }
    
    // 대입 연산자
    DynamicStack& operator=(const DynamicStack& other) {
        if (this != &other) {
            delete[] data;
            capacity = other.capacity;
            top_index = other.top_index;
            data = new T[capacity];
            for (int i = 0; i <= top_index; i++) {
                data[i] = other.data[i];
            }
        }
        return *this;
    }
    
    void push(const T& item) {
        if (top_index + 1 >= capacity) {
            resize();
        }
        data[++top_index] = item;
    }
    
    T pop() {
        if (is_empty()) {
            throw runtime_error("Stack underflow");
        }
        return data[top_index--];
    }
    
    T top() const {
        if (is_empty()) {
            throw runtime_error("Stack is empty");
        }
        return data[top_index];
    }
    
    bool is_empty() const {
        return top_index == -1;
    }
    
    int size() const {
        return top_index + 1;
    }
    
    int get_capacity() const {
        return capacity;
    }
    
    void print() const {
        cout << "Stack (bottom to top): ";
        for (int i = 0; i <= top_index; i++) {
            cout << data[i] << " ";
        }
        cout << " (capacity: " << capacity << ")" << endl;
    }
};

/**
 * 3. 연결 리스트 기반 스택
 * 메모리를 동적으로 할당하는 스택
 */
template<typename T>
class LinkedStack {
private:
    struct Node {
        T data;
        Node* next;
        
        Node(const T& value) : data(value), next(nullptr) {}
    };
    
    Node* top_node;
    int stack_size;
    
public:
    LinkedStack() : top_node(nullptr), stack_size(0) {}
    
    ~LinkedStack() {
        clear();
    }
    
    // 복사 생성자
    LinkedStack(const LinkedStack& other) : top_node(nullptr), stack_size(0) {
        if (other.top_node != nullptr) {
            // 임시 배열을 사용하여 순서를 맞춤
            T* temp = new T[other.stack_size];
            Node* current = other.top_node;
            int index = other.stack_size - 1;
            
            while (current != nullptr) {
                temp[index--] = current->data;
                current = current->next;
            }
            
            for (int i = 0; i < other.stack_size; i++) {
                push(temp[i]);
            }
            
            delete[] temp;
        }
    }
    
    // 대입 연산자
    LinkedStack& operator=(const LinkedStack& other) {
        if (this != &other) {
            clear();
            
            if (other.top_node != nullptr) {
                T* temp = new T[other.stack_size];
                Node* current = other.top_node;
                int index = other.stack_size - 1;
                
                while (current != nullptr) {
                    temp[index--] = current->data;
                    current = current->next;
                }
                
                for (int i = 0; i < other.stack_size; i++) {
                    push(temp[i]);
                }
                
                delete[] temp;
            }
        }
        return *this;
    }
    
    void push(const T& item) {
        Node* new_node = new Node(item);
        new_node->next = top_node;
        top_node = new_node;
        stack_size++;
    }
    
    T pop() {
        if (is_empty()) {
            throw runtime_error("Stack underflow");
        }
        
        Node* temp = top_node;
        T data = temp->data;
        top_node = top_node->next;
        delete temp;
        stack_size--;
        
        return data;
    }
    
    T top() const {
        if (is_empty()) {
            throw runtime_error("Stack is empty");
        }
        return top_node->data;
    }
    
    bool is_empty() const {
        return top_node == nullptr;
    }
    
    int size() const {
        return stack_size;
    }
    
    void clear() {
        while (!is_empty()) {
            pop();
        }
    }
    
    void print() const {
        cout << "Stack (top to bottom): ";
        Node* current = top_node;
        while (current != nullptr) {
            cout << current->data << " ";
            current = current->next;
        }
        cout << endl;
    }
};

// 사용 예제
int main() {
    cout << "=== Array-based Stack ===" << endl;
    ArrayStack<int> arr_stack(5);
    
    // 데이터 추가
    for (int i = 1; i <= 4; i++) {
        arr_stack.push(i * 10);
        cout << "Pushed: " << i * 10 << endl;
    }
    
    arr_stack.print();
    cout << "Size: " << arr_stack.size() << endl;
    
    // 데이터 제거
    while (!arr_stack.is_empty()) {
        cout << "Popped: " << arr_stack.pop() << endl;
    }
    cout << endl;
    
    cout << "=== Dynamic Stack ===" << endl;
    DynamicStack<int> dyn_stack(2);  // 작은 초기 용량으로 시작
    
    // 용량 초과하도록 데이터 추가
    for (int i = 1; i <= 8; i++) {
        dyn_stack.push(i * 5);
        cout << "Pushed: " << i * 5 << ", Capacity: " << dyn_stack.get_capacity() << endl;
    }
    
    dyn_stack.print();
    cout << endl;
    
    cout << "=== Linked Stack ===" << endl;
    LinkedStack<int> linked_stack;
    
    // 데이터 추가
    for (int i = 1; i <= 5; i++) {
        linked_stack.push(i * 7);
        cout << "Pushed: " << i * 7 << endl;
    }
    
    linked_stack.print();
    cout << "Size: " << linked_stack.size() << endl;
    
    // 복사 생성자 테스트
    LinkedStack<int> copied_stack = linked_stack;
    cout << "Copied stack: ";
    copied_stack.print();
    
    // 데이터 제거
    cout << "Popping from original: ";
    while (!linked_stack.is_empty()) {
        cout << linked_stack.pop() << " ";
    }
    cout << endl;
    
    cout << "Copied stack after popping from original: ";
    copied_stack.print();
    
    return 0;
}