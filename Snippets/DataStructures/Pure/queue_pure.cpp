#include <iostream>
using namespace std;

/**
 * 순수 C++ 큐 구현 (STL 없이)
 * 배열 기반, 원형 큐, 연결 리스트 기반 구현
 */

/**
 * 1. 배열 기반 선형 큐
 * 단순한 배열을 사용한 큐 (front가 이동하면서 공간 낭비 발생)
 */
template<typename T>
class ArrayQueue {
private:
    T* data;
    int capacity;
    int front_idx;
    int rear_idx;
    
public:
    ArrayQueue(int cap = 1000) : capacity(cap), front_idx(0), rear_idx(-1) {
        data = new T[capacity];
    }
    
    ~ArrayQueue() {
        delete[] data;
    }
    
    void enqueue(const T& item) {
        if (is_full()) {
            throw runtime_error("Queue overflow");
        }
        data[++rear_idx] = item;
    }
    
    T dequeue() {
        if (is_empty()) {
            throw runtime_error("Queue underflow");
        }
        return data[front_idx++];
    }
    
    T front() const {
        if (is_empty()) {
            throw runtime_error("Queue is empty");
        }
        return data[front_idx];
    }
    
    T rear() const {
        if (is_empty()) {
            throw runtime_error("Queue is empty");
        }
        return data[rear_idx];
    }
    
    bool is_empty() const {
        return front_idx > rear_idx;
    }
    
    bool is_full() const {
        return rear_idx >= capacity - 1;
    }
    
    int size() const {
        return rear_idx - front_idx + 1;
    }
    
    void print() const {
        cout << "Queue (front to rear): ";
        for (int i = front_idx; i <= rear_idx; i++) {
            cout << data[i] << " ";
        }
        cout << endl;
    }
};

/**
 * 2. 원형 큐 (Circular Queue)
 * 배열을 원형으로 사용하여 공간을 효율적으로 활용
 */
template<typename T>
class CircularQueue {
private:
    T* data;
    int capacity;
    int front_idx;
    int rear_idx;
    int count;  // 현재 원소 개수
    
public:
    CircularQueue(int cap = 1000) : capacity(cap), front_idx(0), rear_idx(-1), count(0) {
        data = new T[capacity];
    }
    
    ~CircularQueue() {
        delete[] data;
    }
    
    // 복사 생성자
    CircularQueue(const CircularQueue& other) {
        capacity = other.capacity;
        front_idx = other.front_idx;
        rear_idx = other.rear_idx;
        count = other.count;
        data = new T[capacity];
        
        // 원형 큐의 모든 원소 복사
        for (int i = 0; i < count; i++) {
            int idx = (front_idx + i) % capacity;
            data[idx] = other.data[idx];
        }
    }
    
    // 대입 연산자
    CircularQueue& operator=(const CircularQueue& other) {
        if (this != &other) {
            delete[] data;
            capacity = other.capacity;
            front_idx = other.front_idx;
            rear_idx = other.rear_idx;
            count = other.count;
            data = new T[capacity];
            
            for (int i = 0; i < count; i++) {
                int idx = (front_idx + i) % capacity;
                data[idx] = other.data[idx];
            }
        }
        return *this;
    }
    
    void enqueue(const T& item) {
        if (is_full()) {
            throw runtime_error("Queue overflow");
        }
        rear_idx = (rear_idx + 1) % capacity;
        data[rear_idx] = item;
        count++;
    }
    
    T dequeue() {
        if (is_empty()) {
            throw runtime_error("Queue underflow");
        }
        T item = data[front_idx];
        front_idx = (front_idx + 1) % capacity;
        count--;
        return item;
    }
    
    T front() const {
        if (is_empty()) {
            throw runtime_error("Queue is empty");
        }
        return data[front_idx];
    }
    
    T rear() const {
        if (is_empty()) {
            throw runtime_error("Queue is empty");
        }
        return data[rear_idx];
    }
    
    bool is_empty() const {
        return count == 0;
    }
    
    bool is_full() const {
        return count == capacity;
    }
    
    int size() const {
        return count;
    }
    
    void print() const {
        cout << "Circular Queue (front to rear): ";
        for (int i = 0; i < count; i++) {
            int idx = (front_idx + i) % capacity;
            cout << data[idx] << " ";
        }
        cout << " [front=" << front_idx << ", rear=" << rear_idx << "]" << endl;
    }
};

/**
 * 3. 동적 원형 큐
 * 크기가 자동으로 조절되는 원형 큐
 */
template<typename T>
class DynamicCircularQueue {
private:
    T* data;
    int capacity;
    int front_idx;
    int rear_idx;
    int count;
    
    void resize() {
        int new_capacity = capacity * 2;
        T* new_data = new T[new_capacity];
        
        // 기존 데이터를 새 배열에 순서대로 복사
        for (int i = 0; i < count; i++) {
            int old_idx = (front_idx + i) % capacity;
            new_data[i] = data[old_idx];
        }
        
        delete[] data;
        data = new_data;
        capacity = new_capacity;
        front_idx = 0;
        rear_idx = count - 1;
    }
    
public:
    DynamicCircularQueue(int initial_cap = 4) 
        : capacity(initial_cap), front_idx(0), rear_idx(-1), count(0) {
        data = new T[capacity];
    }
    
    ~DynamicCircularQueue() {
        delete[] data;
    }
    
    void enqueue(const T& item) {
        if (is_full()) {
            resize();
        }
        rear_idx = (rear_idx + 1) % capacity;
        data[rear_idx] = item;
        count++;
    }
    
    T dequeue() {
        if (is_empty()) {
            throw runtime_error("Queue underflow");
        }
        T item = data[front_idx];
        front_idx = (front_idx + 1) % capacity;
        count--;
        return item;
    }
    
    T front() const {
        if (is_empty()) {
            throw runtime_error("Queue is empty");
        }
        return data[front_idx];
    }
    
    T rear() const {
        if (is_empty()) {
            throw runtime_error("Queue is empty");
        }
        return data[rear_idx];
    }
    
    bool is_empty() const {
        return count == 0;
    }
    
    bool is_full() const {
        return count == capacity;
    }
    
    int size() const {
        return count;
    }
    
    int get_capacity() const {
        return capacity;
    }
    
    void print() const {
        cout << "Dynamic Queue (front to rear): ";
        for (int i = 0; i < count; i++) {
            int idx = (front_idx + i) % capacity;
            cout << data[idx] << " ";
        }
        cout << " (capacity: " << capacity << ")" << endl;
    }
};

/**
 * 4. 연결 리스트 기반 큐
 * 메모리를 동적으로 할당하는 큐
 */
template<typename T>
class LinkedQueue {
private:
    struct Node {
        T data;
        Node* next;
        
        Node(const T& value) : data(value), next(nullptr) {}
    };
    
    Node* front_node;
    Node* rear_node;
    int queue_size;
    
public:
    LinkedQueue() : front_node(nullptr), rear_node(nullptr), queue_size(0) {}
    
    ~LinkedQueue() {
        clear();
    }
    
    // 복사 생성자
    LinkedQueue(const LinkedQueue& other) : front_node(nullptr), rear_node(nullptr), queue_size(0) {
        Node* current = other.front_node;
        while (current != nullptr) {
            enqueue(current->data);
            current = current->next;
        }
    }
    
    // 대입 연산자
    LinkedQueue& operator=(const LinkedQueue& other) {
        if (this != &other) {
            clear();
            Node* current = other.front_node;
            while (current != nullptr) {
                enqueue(current->data);
                current = current->next;
            }
        }
        return *this;
    }
    
    void enqueue(const T& item) {
        Node* new_node = new Node(item);
        
        if (is_empty()) {
            front_node = rear_node = new_node;
        } else {
            rear_node->next = new_node;
            rear_node = new_node;
        }
        queue_size++;
    }
    
    T dequeue() {
        if (is_empty()) {
            throw runtime_error("Queue underflow");
        }
        
        Node* temp = front_node;
        T data = temp->data;
        front_node = front_node->next;
        
        if (front_node == nullptr) {
            rear_node = nullptr;  // 큐가 비어있게 됨
        }
        
        delete temp;
        queue_size--;
        return data;
    }
    
    T front() const {
        if (is_empty()) {
            throw runtime_error("Queue is empty");
        }
        return front_node->data;
    }
    
    T rear() const {
        if (is_empty()) {
            throw runtime_error("Queue is empty");
        }
        return rear_node->data;
    }
    
    bool is_empty() const {
        return front_node == nullptr;
    }
    
    int size() const {
        return queue_size;
    }
    
    void clear() {
        while (!is_empty()) {
            dequeue();
        }
    }
    
    void print() const {
        cout << "Linked Queue (front to rear): ";
        Node* current = front_node;
        while (current != nullptr) {
            cout << current->data << " ";
            current = current->next;
        }
        cout << endl;
    }
};

// 사용 예제
int main() {
    cout << "=== Array Queue ===" << endl;
    ArrayQueue<int> arr_queue(5);
    
    // 데이터 추가
    for (int i = 1; i <= 4; i++) {
        arr_queue.enqueue(i * 10);
        cout << "Enqueued: " << i * 10 << endl;
    }
    
    arr_queue.print();
    cout << "Front: " << arr_queue.front() << ", Rear: " << arr_queue.rear() << endl;
    
    // 데이터 제거
    cout << "Dequeued: " << arr_queue.dequeue() << endl;
    cout << "Dequeued: " << arr_queue.dequeue() << endl;
    arr_queue.print();
    cout << endl;
    
    cout << "=== Circular Queue ===" << endl;
    CircularQueue<int> circ_queue(5);
    
    // 데이터 추가
    for (int i = 1; i <= 4; i++) {
        circ_queue.enqueue(i * 20);
        cout << "Enqueued: " << i * 20 << endl;
    }
    
    circ_queue.print();
    
    // 일부 제거 후 다시 추가 (원형 특성 확인)
    cout << "Dequeued: " << circ_queue.dequeue() << endl;
    cout << "Dequeued: " << circ_queue.dequeue() << endl;
    
    circ_queue.enqueue(100);
    circ_queue.enqueue(120);
    circ_queue.print();
    cout << endl;
    
    cout << "=== Dynamic Circular Queue ===" << endl;
    DynamicCircularQueue<int> dyn_queue(3);  // 작은 초기 용량
    
    // 용량 초과하도록 데이터 추가
    for (int i = 1; i <= 8; i++) {
        dyn_queue.enqueue(i * 5);
        cout << "Enqueued: " << i * 5 << ", Capacity: " << dyn_queue.get_capacity() << endl;
    }
    
    dyn_queue.print();
    cout << endl;
    
    cout << "=== Linked Queue ===" << endl;
    LinkedQueue<int> linked_queue;
    
    // 데이터 추가
    for (int i = 1; i <= 5; i++) {
        linked_queue.enqueue(i * 7);
        cout << "Enqueued: " << i * 7 << endl;
    }
    
    linked_queue.print();
    cout << "Size: " << linked_queue.size() << endl;
    
    // 복사 생성자 테스트
    LinkedQueue<int> copied_queue = linked_queue;
    cout << "Copied queue: ";
    copied_queue.print();
    
    // 데이터 제거
    cout << "Dequeuing from original: ";
    while (!linked_queue.is_empty()) {
        cout << linked_queue.dequeue() << " ";
    }
    cout << endl;
    
    cout << "Copied queue after dequeuing from original: ";
    copied_queue.print();
    
    return 0;
}