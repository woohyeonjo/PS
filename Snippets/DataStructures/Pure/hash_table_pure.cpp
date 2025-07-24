#include <iostream>
using namespace std;

/**
 * 순수 C++ 해시 테이블 구현 (STL 없이)
 * 체이닝(Chaining)과 오픈 어드레싱(Open Addressing) 방식 구현
 */

/**
 * 1. 체이닝 방식 해시 테이블
 * 충돌이 발생하면 연결 리스트로 처리
 */
template<typename K, typename V>
class HashTableChaining {
private:
    struct Node {
        K key;
        V value;
        Node* next;
        
        Node(const K& k, const V& v) : key(k), value(v), next(nullptr) {}
    };
    
    Node** table;      // 해시 테이블 (포인터 배열)
    size_t capacity;   // 테이블 크기
    size_t count;      // 저장된 키-값 쌍의 개수
    
    // 간단한 해시 함수 (문자열용)
    size_t hash_function(const string& key) const {
        size_t hash = 0;
        for (char c : key) {
            hash = hash * 31 + c;  // 31은 소수
        }
        return hash % capacity;
    }
    
    // 정수용 해시 함수
    size_t hash_function(int key) const {
        return static_cast<size_t>(key) % capacity;
    }
    
    // 일반적인 해시 함수 (템플릿 특수화 필요 시 사용)
    size_t hash_function(const K& key) const {
        // 기본적으로 키를 size_t로 변환하여 사용
        return static_cast<size_t>(key) % capacity;
    }
    
    // 재해싱 (load factor가 너무 높을 때)
    void rehash() {
        Node** old_table = table;
        size_t old_capacity = capacity;
        
        capacity *= 2;
        count = 0;
        table = new Node*[capacity];
        
        // 새 테이블 초기화
        for (size_t i = 0; i < capacity; i++) {
            table[i] = nullptr;
        }
        
        // 기존 모든 원소를 새 테이블에 재삽입
        for (size_t i = 0; i < old_capacity; i++) {
            Node* current = old_table[i];
            while (current != nullptr) {
                Node* next = current->next;
                insert(current->key, current->value);
                delete current;
                current = next;
            }
        }
        
        delete[] old_table;
    }
    
public:
    // 생성자
    HashTableChaining(size_t initial_capacity = 16) 
        : capacity(initial_capacity), count(0) {
        table = new Node*[capacity];
        for (size_t i = 0; i < capacity; i++) {
            table[i] = nullptr;
        }
    }
    
    // 소멸자
    ~HashTableChaining() {
        clear();
        delete[] table;
    }
    
    // 복사 생성자
    HashTableChaining(const HashTableChaining& other) 
        : capacity(other.capacity), count(0) {
        table = new Node*[capacity];
        for (size_t i = 0; i < capacity; i++) {
            table[i] = nullptr;
        }
        
        // 모든 원소 복사
        for (size_t i = 0; i < other.capacity; i++) {
            Node* current = other.table[i];
            while (current != nullptr) {
                insert(current->key, current->value);
                current = current->next;
            }
        }
    }
    
    // 키-값 쌍 삽입
    void insert(const K& key, const V& value) {
        // Load factor가 0.75를 초과하면 재해싱
        if (static_cast<double>(count) / capacity > 0.75) {
            rehash();
        }
        
        size_t index = hash_function(key);
        Node* current = table[index];
        
        // 키가 이미 존재하는지 확인
        while (current != nullptr) {
            if (current->key == key) {
                current->value = value;  // 값 업데이트
                return;
            }
            current = current->next;
        }
        
        // 새 노드를 체인의 맨 앞에 삽입
        Node* new_node = new Node(key, value);
        new_node->next = table[index];
        table[index] = new_node;
        count++;
    }
    
    // 키로 값 검색
    bool search(const K& key, V& value) const {
        size_t index = hash_function(key);
        Node* current = table[index];
        
        while (current != nullptr) {
            if (current->key == key) {
                value = current->value;
                return true;
            }
            current = current->next;
        }
        
        return false;
    }
    
    // 키 존재 여부 확인
    bool contains(const K& key) const {
        V dummy;
        return search(key, dummy);
    }
    
    // 키-값 쌍 제거
    bool remove(const K& key) {
        size_t index = hash_function(key);
        Node* current = table[index];
        Node* prev = nullptr;
        
        while (current != nullptr) {
            if (current->key == key) {
                if (prev == nullptr) {
                    // 첫 번째 노드 제거
                    table[index] = current->next;
                } else {
                    prev->next = current->next;
                }
                delete current;
                count--;
                return true;
            }
            prev = current;
            current = current->next;
        }
        
        return false;
    }
    
    // 모든 원소 제거
    void clear() {
        for (size_t i = 0; i < capacity; i++) {
            Node* current = table[i];
            while (current != nullptr) {
                Node* next = current->next;
                delete current;
                current = next;
            }
            table[i] = nullptr;
        }
        count = 0;
    }
    
    // 해시 테이블이 비어있는지 확인
    bool empty() const {
        return count == 0;
    }
    
    // 크기 반환
    size_t size() const {
        return count;
    }
    
    // Load factor 계산
    double load_factor() const {
        return static_cast<double>(count) / capacity;
    }
    
    // 해시 테이블 출력
    void print() const {
        cout << "Hash Table (Chaining):" << endl;
        cout << "Size: " << count << ", Capacity: " << capacity 
             << ", Load Factor: " << load_factor() << endl;
        
        for (size_t i = 0; i < capacity; i++) {
            cout << "Bucket " << i << ": ";
            Node* current = table[i];
            if (current == nullptr) {
                cout << "empty";
            } else {
                while (current != nullptr) {
                    cout << "(" << current->key << ", " << current->value << ")";
                    if (current->next != nullptr) cout << " -> ";
                    current = current->next;
                }
            }
            cout << endl;
        }
        cout << endl;
    }
};

/**
 * 2. 오픈 어드레싱 방식 해시 테이블 (선형 탐사)
 * 충돌이 발생하면 다음 빈 슬롯을 찾아서 저장
 */
template<typename K, typename V>
class HashTableOpenAddressing {
private:
    enum State { EMPTY, OCCUPIED, DELETED };
    
    struct Entry {
        K key;
        V value;
        State state;
        
        Entry() : state(EMPTY) {}
        Entry(const K& k, const V& v) : key(k), value(v), state(OCCUPIED) {}
    };
    
    Entry* table;
    size_t capacity;
    size_t count;
    size_t deleted_count;
    
    size_t hash_function(const K& key) const {
        return static_cast<size_t>(key) % capacity;
    }
    
    // 선형 탐사
    size_t linear_probe(size_t start_index, const K& key) const {
        size_t index = start_index;
        
        while (table[index].state != EMPTY) {
            if (table[index].state == OCCUPIED && table[index].key == key) {
                return index;  // 키 발견
            }
            index = (index + 1) % capacity;
            
            if (index == start_index) {
                break;  // 테이블을 한 바퀴 돌았음
            }
        }
        
        return index;  // 빈 슬롯 또는 삭제된 슬롯
    }
    
    void rehash() {
        Entry* old_table = table;
        size_t old_capacity = capacity;
        
        capacity *= 2;
        count = 0;
        deleted_count = 0;
        table = new Entry[capacity];
        
        // 기존 원소들을 새 테이블에 재삽입
        for (size_t i = 0; i < old_capacity; i++) {
            if (old_table[i].state == OCCUPIED) {
                insert(old_table[i].key, old_table[i].value);
            }
        }
        
        delete[] old_table;
    }
    
public:
    HashTableOpenAddressing(size_t initial_capacity = 16) 
        : capacity(initial_capacity), count(0), deleted_count(0) {
        table = new Entry[capacity];
    }
    
    ~HashTableOpenAddressing() {
        delete[] table;
    }
    
    void insert(const K& key, const V& value) {
        // Load factor가 0.5를 초과하면 재해싱
        if (static_cast<double>(count + deleted_count) / capacity > 0.5) {
            rehash();
        }
        
        size_t index = hash_function(key);
        index = linear_probe(index, key);
        
        if (table[index].state == OCCUPIED) {
            // 키가 이미 존재 - 값 업데이트
            table[index].value = value;
        } else {
            // 새 키-값 쌍 삽입
            if (table[index].state == DELETED) {
                deleted_count--;
            }
            table[index] = Entry(key, value);
            count++;
        }
    }
    
    bool search(const K& key, V& value) const {
        size_t index = hash_function(key);
        size_t start_index = index;
        
        while (table[index].state != EMPTY) {
            if (table[index].state == OCCUPIED && table[index].key == key) {
                value = table[index].value;
                return true;
            }
            index = (index + 1) % capacity;
            
            if (index == start_index) {
                break;
            }
        }
        
        return false;
    }
    
    bool contains(const K& key) const {
        V dummy;
        return search(key, dummy);
    }
    
    bool remove(const K& key) {
        size_t index = hash_function(key);
        size_t start_index = index;
        
        while (table[index].state != EMPTY) {
            if (table[index].state == OCCUPIED && table[index].key == key) {
                table[index].state = DELETED;
                count--;
                deleted_count++;
                return true;
            }
            index = (index + 1) % capacity;
            
            if (index == start_index) {
                break;
            }
        }
        
        return false;
    }
    
    void clear() {
        for (size_t i = 0; i < capacity; i++) {
            table[i] = Entry();
        }
        count = 0;
        deleted_count = 0;
    }
    
    bool empty() const {
        return count == 0;
    }
    
    size_t size() const {
        return count;
    }
    
    double load_factor() const {
        return static_cast<double>(count) / capacity;
    }
    
    void print() const {
        cout << "Hash Table (Open Addressing):" << endl;
        cout << "Size: " << count << ", Capacity: " << capacity 
             << ", Load Factor: " << load_factor() << endl;
        
        for (size_t i = 0; i < capacity; i++) {
            cout << "Slot " << i << ": ";
            if (table[i].state == EMPTY) {
                cout << "empty";
            } else if (table[i].state == DELETED) {
                cout << "deleted";
            } else {
                cout << "(" << table[i].key << ", " << table[i].value << ")";
            }
            cout << endl;
        }
        cout << endl;
    }
};

// 사용 예제
int main() {
    cout << "=== Hash Table with Chaining ===" << endl;
    HashTableChaining<int, string> hash_chain(4);  // 작은 크기로 시작
    
    // 데이터 삽입
    hash_chain.insert(1, "One");
    hash_chain.insert(2, "Two");
    hash_chain.insert(5, "Five");
    hash_chain.insert(9, "Nine");  // 충돌 발생 (1과 같은 버킷)
    hash_chain.insert(13, "Thirteen");  // 또 다른 충돌
    
    hash_chain.print();
    
    // 검색
    string value;
    if (hash_chain.search(9, value)) {
        cout << "Key 9 found: " << value << endl;
    }
    
    // 제거
    cout << "Removing key 5..." << endl;
    hash_chain.remove(5);
    hash_chain.print();
    
    // 더 많은 데이터 추가 (재해싱 발생)
    cout << "Adding more data to trigger rehashing..." << endl;
    hash_chain.insert(17, "Seventeen");
    hash_chain.insert(21, "Twenty-one");
    hash_chain.insert(25, "Twenty-five");
    
    hash_chain.print();
    
    cout << "\n=== Hash Table with Open Addressing ===" << endl;
    HashTableOpenAddressing<int, string> hash_open(8);
    
    // 데이터 삽입
    hash_open.insert(10, "Ten");
    hash_open.insert(22, "Twenty-two");
    hash_open.insert(31, "Thirty-one");
    hash_open.insert(4, "Four");
    hash_open.insert(15, "Fifteen");
    
    hash_open.print();
    
    // 검색
    if (hash_open.search(22, value)) {
        cout << "Key 22 found: " << value << endl;
    }
    
    // 제거
    cout << "Removing key 22..." << endl;
    hash_open.remove(22);
    hash_open.print();
    
    // 삭제된 슬롯에 새 데이터 삽입
    cout << "Inserting key 38..." << endl;
    hash_open.insert(38, "Thirty-eight");
    hash_open.print();
    
    return 0;
}