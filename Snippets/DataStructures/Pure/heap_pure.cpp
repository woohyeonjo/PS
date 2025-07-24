#include <iostream>
using namespace std;

/**
 * 순수 C++ 힙 구현 (STL 없이)
 * 최대 힙과 최소 힙 구현
 * 우선순위 큐의 기본 자료구조
 */

/**
 * 최대 힙 (Max Heap)
 * 부모 노드가 자식 노드보다 크거나 같은 완전 이진 트리
 */
template<typename T>
class MaxHeap {
private:
    T* data;
    size_t capacity;
    size_t heap_size;
    
    // 부모 노드 인덱스
    size_t parent(size_t i) const {
        return (i - 1) / 2;
    }
    
    // 왼쪽 자식 노드 인덱스
    size_t left_child(size_t i) const {
        return 2 * i + 1;
    }
    
    // 오른쪽 자식 노드 인덱스
    size_t right_child(size_t i) const {
        return 2 * i + 2;
    }
    
    // 두 값 교환
    void swap(T& a, T& b) {
        T temp = a;
        a = b;
        b = temp;
    }
    
    // 위로 올라가며 힙 속성 유지 (삽입 시 사용)
    void heapify_up(size_t index) {
        while (index > 0 && data[parent(index)] < data[index]) {
            swap(data[parent(index)], data[index]);
            index = parent(index);
        }
    }
    
    // 아래로 내려가며 힙 속성 유지 (삭제 시 사용)
    void heapify_down(size_t index) {
        size_t largest = index;
        size_t left = left_child(index);
        size_t right = right_child(index);
        
        // 왼쪽 자식과 비교
        if (left < heap_size && data[left] > data[largest]) {
            largest = left;
        }
        
        // 오른쪽 자식과 비교
        if (right < heap_size && data[right] > data[largest]) {
            largest = right;
        }
        
        // 가장 큰 값이 현재 노드가 아니라면 교환하고 재귀
        if (largest != index) {
            swap(data[index], data[largest]);
            heapify_down(largest);
        }
    }
    
    // 용량 증가
    void resize() {
        size_t new_capacity = (capacity == 0) ? 1 : capacity * 2;
        T* new_data = new T[new_capacity];
        
        for (size_t i = 0; i < heap_size; i++) {
            new_data[i] = data[i];
        }
        
        delete[] data;
        data = new_data;
        capacity = new_capacity;
    }
    
public:
    // 생성자
    MaxHeap(size_t initial_capacity = 10) 
        : capacity(initial_capacity), heap_size(0) {
        data = new T[capacity];
    }
    
    // 배열로부터 힙 생성
    MaxHeap(T arr[], size_t size) : capacity(size), heap_size(size) {
        data = new T[capacity];
        
        // 배열 복사
        for (size_t i = 0; i < size; i++) {
            data[i] = arr[i];
        }
        
        // 힙 구성 (bottom-up)
        for (int i = static_cast<int>(heap_size / 2) - 1; i >= 0; i--) {
            heapify_down(static_cast<size_t>(i));
        }
    }
    
    // 소멸자
    ~MaxHeap() {
        delete[] data;
    }
    
    // 복사 생성자
    MaxHeap(const MaxHeap& other) 
        : capacity(other.capacity), heap_size(other.heap_size) {
        data = new T[capacity];
        for (size_t i = 0; i < heap_size; i++) {
            data[i] = other.data[i];
        }
    }
    
    // 대입 연산자
    MaxHeap& operator=(const MaxHeap& other) {
        if (this != &other) {
            delete[] data;
            capacity = other.capacity;
            heap_size = other.heap_size;
            data = new T[capacity];
            for (size_t i = 0; i < heap_size; i++) {
                data[i] = other.data[i];
            }
        }
        return *this;
    }
    
    // 원소 삽입
    void push(const T& value) {
        if (heap_size >= capacity) {
            resize();
        }
        
        data[heap_size] = value;
        heapify_up(heap_size);
        heap_size++;
    }
    
    // 최대값 제거 및 반환
    T pop() {
        if (empty()) {
            throw runtime_error("Heap is empty");
        }
        
        T max_value = data[0];
        data[0] = data[heap_size - 1];
        heap_size--;
        
        if (!empty()) {
            heapify_down(0);
        }
        
        return max_value;
    }
    
    // 최대값 조회 (제거하지 않음)
    T top() const {
        if (empty()) {
            throw runtime_error("Heap is empty");
        }
        return data[0];
    }
    
    // 힙이 비어있는지 확인
    bool empty() const {
        return heap_size == 0;
    }
    
    // 힙 크기
    size_t size() const {
        return heap_size;
    }
    
    // 힙 출력 (레벨 순서)
    void print() const {
        cout << "Max Heap: ";
        for (size_t i = 0; i < heap_size; i++) {
            cout << data[i] << " ";
        }
        cout << "(size: " << heap_size << ")" << endl;
    }
    
    // 힙 구조 출력
    void print_structure() const {
        if (empty()) {
            cout << "Empty heap" << endl;
            return;
        }
        
        cout << "Heap structure:" << endl;
        size_t level = 0;
        size_t level_size = 1;
        size_t current = 0;
        
        while (current < heap_size) {
            cout << "Level " << level << ": ";
            for (size_t i = 0; i < level_size && current < heap_size; i++) {
                cout << data[current++] << " ";
            }
            cout << endl;
            level++;
            level_size *= 2;
        }
    }
};

/**
 * 최소 힙 (Min Heap)
 * 부모 노드가 자식 노드보다 작거나 같은 완전 이진 트리
 */
template<typename T>
class MinHeap {
private:
    T* data;
    size_t capacity;
    size_t heap_size;
    
    size_t parent(size_t i) const { return (i - 1) / 2; }
    size_t left_child(size_t i) const { return 2 * i + 1; }
    size_t right_child(size_t i) const { return 2 * i + 2; }
    
    void swap(T& a, T& b) {
        T temp = a;
        a = b;
        b = temp;
    }
    
    void heapify_up(size_t index) {
        while (index > 0 && data[parent(index)] > data[index]) {
            swap(data[parent(index)], data[index]);
            index = parent(index);
        }
    }
    
    void heapify_down(size_t index) {
        size_t smallest = index;
        size_t left = left_child(index);
        size_t right = right_child(index);
        
        if (left < heap_size && data[left] < data[smallest]) {
            smallest = left;
        }
        
        if (right < heap_size && data[right] < data[smallest]) {
            smallest = right;
        }
        
        if (smallest != index) {
            swap(data[index], data[smallest]);
            heapify_down(smallest);
        }
    }
    
    void resize() {
        size_t new_capacity = (capacity == 0) ? 1 : capacity * 2;
        T* new_data = new T[new_capacity];
        
        for (size_t i = 0; i < heap_size; i++) {
            new_data[i] = data[i];
        }
        
        delete[] data;
        data = new_data;
        capacity = new_capacity;
    }
    
public:
    MinHeap(size_t initial_capacity = 10) 
        : capacity(initial_capacity), heap_size(0) {
        data = new T[capacity];
    }
    
    ~MinHeap() {
        delete[] data;
    }
    
    void push(const T& value) {
        if (heap_size >= capacity) {
            resize();
        }
        
        data[heap_size] = value;
        heapify_up(heap_size);
        heap_size++;
    }
    
    T pop() {
        if (empty()) {
            throw runtime_error("Heap is empty");
        }
        
        T min_value = data[0];
        data[0] = data[heap_size - 1];
        heap_size--;
        
        if (!empty()) {
            heapify_down(0);
        }
        
        return min_value;
    }
    
    T top() const {
        if (empty()) {
            throw runtime_error("Heap is empty");
        }
        return data[0];
    }
    
    bool empty() const {
        return heap_size == 0;
    }
    
    size_t size() const {
        return heap_size;
    }
    
    void print() const {
        cout << "Min Heap: ";
        for (size_t i = 0; i < heap_size; i++) {
            cout << data[i] << " ";
        }
        cout << "(size: " << heap_size << ")" << endl;
    }
};

/**
 * 우선순위 큐 (Priority Queue)
 * 힙을 이용한 우선순위 큐 구현
 */
template<typename T>
class PriorityQueue {
private:
    MaxHeap<T> heap;
    
public:
    void push(const T& value) {
        heap.push(value);
    }
    
    T pop() {
        return heap.pop();
    }
    
    T top() const {
        return heap.top();
    }
    
    bool empty() const {
        return heap.empty();
    }
    
    size_t size() const {
        return heap.size();
    }
    
    void print() const {
        cout << "Priority Queue: ";
        heap.print();
    }
};

// 사용 예제
int main() {
    cout << "=== Max Heap Test ===" << endl;
    MaxHeap<int> max_heap;
    
    // 원소 삽입
    int values[] = {4, 10, 3, 5, 1, 15, 20, 17};
    cout << "Inserting: ";
    for (int val : values) {
        cout << val << " ";
        max_heap.push(val);
    }
    cout << endl;
    
    max_heap.print();
    max_heap.print_structure();
    
    cout << "\nExtracting max values:" << endl;
    while (!max_heap.empty()) {
        cout << "Max: " << max_heap.pop() << endl;
    }
    
    cout << "\n=== Min Heap Test ===" << endl;
    MinHeap<int> min_heap;
    
    // 같은 값들을 최소 힙에 삽입
    cout << "Inserting: ";
    for (int val : values) {
        cout << val << " ";
        min_heap.push(val);
    }
    cout << endl;
    
    min_heap.print();
    
    cout << "\nExtracting min values:" << endl;
    while (!min_heap.empty()) {
        cout << "Min: " << min_heap.pop() << endl;
    }
    
    cout << "\n=== Priority Queue Test ===" << endl;
    PriorityQueue<int> pq;
    
    // 우선순위 큐에 작업 추가 (높은 숫자 = 높은 우선순위)
    int priorities[] = {3, 1, 4, 1, 5, 9, 2, 6};
    cout << "Adding tasks with priorities: ";
    for (int priority : priorities) {
        cout << priority << " ";
        pq.push(priority);
    }
    cout << endl;
    
    pq.print();
    
    cout << "\nProcessing tasks by priority:" << endl;
    while (!pq.empty()) {
        cout << "Processing task with priority: " << pq.pop() << endl;
    }
    
    cout << "\n=== Array-based Heap Construction ===" << endl;
    int arr[] = {1, 3, 6, 5, 9, 8};
    size_t arr_size = sizeof(arr) / sizeof(arr[0]);
    
    cout << "Original array: ";
    for (size_t i = 0; i < arr_size; i++) {
        cout << arr[i] << " ";
    }
    cout << endl;
    
    MaxHeap<int> heap_from_array(arr, arr_size);
    cout << "Max heap from array: ";
    heap_from_array.print();
    heap_from_array.print_structure();
    
    return 0;
}