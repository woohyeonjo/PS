#include <iostream>
#include <vector>
using namespace std;

/**
 * 큐 (Queue) - FIFO(First In First Out) 자료구조
 * 먼저 들어온 데이터가 먼저 나가는 구조
 * BFS, 프로세스 스케줄링 등에 사용
 */
template<typename T>
class Queue {
private:
    vector<T> data;
    size_t front_idx;
    size_t rear_idx;
    
public:
    Queue() : front_idx(0), rear_idx(0) {}
    
    // 큐에 데이터 추가 (뒤쪽에 추가)
    void push(const T& item) {
        data.push_back(item);
        rear_idx++;
    }
    
    // 큐에서 데이터 제거 및 반환 (앞쪽에서 제거)
    T pop() {
        if (empty()) {
            throw runtime_error("Queue is empty");
        }
        return data[front_idx++];
    }
    
    // 맨 앞 데이터 조회 (제거하지 않음)
    T front() const {
        if (empty()) {
            throw runtime_error("Queue is empty");
        }
        return data[front_idx];
    }
    
    // 맨 뒤 데이터 조회 (제거하지 않음)
    T back() const {
        if (empty()) {
            throw runtime_error("Queue is empty");
        }
        return data[rear_idx - 1];
    }
    
    // 큐가 비어있는지 확인
    bool empty() const {
        return front_idx >= rear_idx;
    }
    
    // 큐 크기 반환
    size_t size() const {
        return rear_idx - front_idx;
    }
};

// 사용 예제
int main() {
    Queue<int> q;
    
    // 데이터 추가
    q.push(10);
    q.push(20);
    q.push(30);
    
    cout << "Queue size: " << q.size() << endl;
    
    // 데이터 제거하며 출력
    while (!q.empty()) {
        cout << q.pop() << " ";
    }
    cout << endl;
    
    return 0;
}