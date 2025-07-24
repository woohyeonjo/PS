#include <iostream>
using namespace std;

/**
 * 순수 C++ 동적 배열 구현 (STL vector와 유사)
 * 자동으로 크기가 조절되는 배열 자료구조
 */

template<typename T>
class DynamicArray {
private:
    T* data;           // 실제 데이터를 저장하는 배열
    size_t capacity;   // 현재 할당된 용량
    size_t count;      // 현재 저장된 원소 개수
    
    // 용량을 두 배로 증가
    void resize() {
        size_t new_capacity = (capacity == 0) ? 1 : capacity * 2;
        T* new_data = new T[new_capacity];
        
        // 기존 데이터 복사
        for (size_t i = 0; i < count; i++) {
            new_data[i] = data[i];
        }
        
        delete[] data;
        data = new_data;
        capacity = new_capacity;
    }
    
    // 용량 축소 (크기가 1/4 이하로 줄어들면 절반으로 축소)
    void shrink() {
        if (capacity > 4 && count <= capacity / 4) {
            size_t new_capacity = capacity / 2;
            T* new_data = new T[new_capacity];
            
            for (size_t i = 0; i < count; i++) {
                new_data[i] = data[i];
            }
            
            delete[] data;
            data = new_data;
            capacity = new_capacity;
        }
    }
    
public:
    // 기본 생성자
    DynamicArray() : data(nullptr), capacity(0), count(0) {}
    
    // 초기 크기를 지정하는 생성자
    explicit DynamicArray(size_t initial_capacity) 
        : capacity(initial_capacity), count(0) {
        data = new T[capacity];
    }
    
    // 크기와 초기값을 지정하는 생성자
    DynamicArray(size_t size, const T& value) 
        : capacity(size), count(size) {
        data = new T[capacity];
        for (size_t i = 0; i < count; i++) {
            data[i] = value;
        }
    }
    
    // 소멸자
    ~DynamicArray() {
        delete[] data;
    }
    
    // 복사 생성자
    DynamicArray(const DynamicArray& other) 
        : capacity(other.capacity), count(other.count) {
        data = new T[capacity];
        for (size_t i = 0; i < count; i++) {
            data[i] = other.data[i];
        }
    }
    
    // 대입 연산자
    DynamicArray& operator=(const DynamicArray& other) {
        if (this != &other) {
            delete[] data;
            
            capacity = other.capacity;
            count = other.count;
            data = new T[capacity];
            
            for (size_t i = 0; i < count; i++) {
                data[i] = other.data[i];
            }
        }
        return *this;
    }
    
    // 배열 연산자
    T& operator[](size_t index) {
        if (index >= count) {
            throw out_of_range("Index out of range");
        }
        return data[index];
    }
    
    const T& operator[](size_t index) const {
        if (index >= count) {
            throw out_of_range("Index out of range");
        }
        return data[index];
    }
    
    // 맨 뒤에 원소 추가
    void push_back(const T& value) {
        if (count >= capacity) {
            resize();
        }
        data[count++] = value;
    }
    
    // 맨 뒤 원소 제거
    void pop_back() {
        if (count == 0) {
            throw runtime_error("Array is empty");
        }
        count--;
        shrink();
    }
    
    // 특정 위치에 원소 삽입
    void insert(size_t index, const T& value) {
        if (index > count) {
            throw out_of_range("Index out of range");
        }
        
        if (count >= capacity) {
            resize();
        }
        
        // 뒤쪽 원소들을 한 칸씩 뒤로 이동
        for (size_t i = count; i > index; i--) {
            data[i] = data[i - 1];
        }
        
        data[index] = value;
        count++;
    }
    
    // 특정 위치의 원소 제거
    void erase(size_t index) {
        if (index >= count) {
            throw out_of_range("Index out of range");
        }
        
        // 앞쪽 원소들을 한 칸씩 앞으로 이동
        for (size_t i = index; i < count - 1; i++) {
            data[i] = data[i + 1];
        }
        
        count--;
        shrink();
    }
    
    // 특정 값을 가진 첫 번째 원소 제거
    bool remove(const T& value) {
        for (size_t i = 0; i < count; i++) {
            if (data[i] == value) {
                erase(i);
                return true;
            }
        }
        return false;
    }
    
    // 특정 값 검색
    int find(const T& value) const {
        for (size_t i = 0; i < count; i++) {
            if (data[i] == value) {
                return static_cast<int>(i);
            }
        }
        return -1;  // 찾지 못함
    }
    
    // 첫 번째 원소 참조
    T& front() {
        if (count == 0) {
            throw runtime_error("Array is empty");
        }
        return data[0];
    }
    
    const T& front() const {
        if (count == 0) {
            throw runtime_error("Array is empty");
        }
        return data[0];
    }
    
    // 마지막 원소 참조
    T& back() {
        if (count == 0) {
            throw runtime_error("Array is empty");
        }
        return data[count - 1];
    }
    
    const T& back() const {
        if (count == 0) {
            throw runtime_error("Array is empty");
        }
        return data[count - 1];
    }
    
    // 안전한 원소 접근
    T& at(size_t index) {
        if (index >= count) {
            throw out_of_range("Index out of range");
        }
        return data[index];
    }
    
    const T& at(size_t index) const {
        if (index >= count) {
            throw out_of_range("Index out of range");
        }
        return data[index];
    }
    
    // 배열이 비어있는지 확인
    bool empty() const {
        return count == 0;
    }
    
    // 현재 원소 개수
    size_t size() const {
        return count;
    }
    
    // 현재 용량
    size_t get_capacity() const {
        return capacity;
    }
    
    // 모든 원소 제거
    void clear() {
        count = 0;
        // 메모리는 유지 (성능상 이유)
    }
    
    // 용량 예약
    void reserve(size_t new_capacity) {
        if (new_capacity > capacity) {
            T* new_data = new T[new_capacity];
            
            for (size_t i = 0; i < count; i++) {
                new_data[i] = data[i];
            }
            
            delete[] data;
            data = new_data;
            capacity = new_capacity;
        }
    }
    
    // 크기 조정
    void resize(size_t new_size, const T& value = T()) {
        if (new_size > capacity) {
            reserve(new_size);
        }
        
        if (new_size > count) {
            // 크기 증가 - 새 원소들을 value로 초기화
            for (size_t i = count; i < new_size; i++) {
                data[i] = value;
            }
        }
        
        count = new_size;
    }
    
    // 배열 출력
    void print() const {
        cout << "Array: [";
        for (size_t i = 0; i < count; i++) {
            cout << data[i];
            if (i < count - 1) cout << ", ";
        }
        cout << "] (size: " << count << ", capacity: " << capacity << ")" << endl;
    }
    
    // 반복자 지원을 위한 포인터 반환
    T* begin() { return data; }
    T* end() { return data + count; }
    const T* begin() const { return data; }
    const T* end() const { return data + count; }
};

// 사용 예제
int main() {
    cout << "=== Dynamic Array Test ===" << endl;
    
    // 1. 기본 생성자
    DynamicArray<int> arr;
    cout << "Initial array: ";
    arr.print();
    
    // 2. 원소 추가
    cout << "\nAdding elements:" << endl;
    for (int i = 1; i <= 8; i++) {
        arr.push_back(i * 10);
        cout << "Added " << i * 10 << ": ";
        arr.print();
    }
    
    // 3. 원소 접근
    cout << "\nElement access:" << endl;
    cout << "arr[2] = " << arr[2] << endl;
    cout << "arr.at(3) = " << arr.at(3) << endl;
    cout << "front() = " << arr.front() << endl;
    cout << "back() = " << arr.back() << endl;
    
    // 4. 원소 삽입
    cout << "\nInserting 99 at index 2:" << endl;
    arr.insert(2, 99);
    arr.print();
    
    // 5. 원소 제거
    cout << "\nRemoving element at index 3:" << endl;
    arr.erase(3);
    arr.print();
    
    cout << "\nRemoving first occurrence of 50:" << endl;
    bool removed = arr.remove(50);
    cout << "Removed: " << (removed ? "Yes" : "No") << endl;
    arr.print();
    
    // 6. 검색
    cout << "\nSearching for 70:" << endl;
    int index = arr.find(70);
    cout << "Found at index: " << index << endl;
    
    // 7. 크기 조정
    cout << "\nResizing to 12 with default value 0:" << endl;
    arr.resize(12, 0);
    arr.print();
    
    cout << "\nResizing to 6:" << endl;
    arr.resize(6);
    arr.print();
    
    // 8. 복사 생성자 테스트
    cout << "\nCopy constructor test:" << endl;
    DynamicArray<int> arr_copy = arr;
    cout << "Original: ";
    arr.print();
    cout << "Copy: ";
    arr_copy.print();
    
    // 9. 원본 수정 후 복사본 확인
    arr.push_back(999);
    cout << "\nAfter adding 999 to original:" << endl;
    cout << "Original: ";
    arr.print();
    cout << "Copy: ";
    arr_copy.print();
    
    // 10. 초기값으로 생성
    cout << "\nArray with initial values:" << endl;
    DynamicArray<int> arr_init(5, 42);
    arr_init.print();
    
    // 11. 반복자 스타일 접근
    cout << "\nIterator-style access:" << endl;
    cout << "Elements: ";
    for (int* it = arr.begin(); it != arr.end(); ++it) {
        cout << *it << " ";
    }
    cout << endl;
    
    return 0;
}