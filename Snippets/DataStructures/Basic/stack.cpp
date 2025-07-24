#include <iostream>
#include <vector>
using namespace std;

/**
 * 스택 (Stack) - LIFO(Last In First Out) 자료구조
 * 마지막에 들어온 데이터가 가장 먼저 나가는 구조
 * 함수 호출, 괄호 검사, 계산기 등에 사용
 */
template<typename T>
class Stack {
private:
    vector<T> data;
    
public:
    // 스택에 데이터 추가
    void push(const T& item) {
        data.push_back(item);
    }
    
    // 스택에서 데이터 제거 및 반환
    T pop() {
        if (empty()) {
            throw runtime_error("Stack is empty");
        }
        T top = data.back();
        data.pop_back();
        return top;
    }
    
    // 최상단 데이터 조회 (제거하지 않음)
    T top() const {
        if (empty()) {
            throw runtime_error("Stack is empty");
        }
        return data.back();
    }
    
    // 스택이 비어있는지 확인
    bool empty() const {
        return data.empty();
    }
    
    // 스택 크기 반환
    size_t size() const {
        return data.size();
    }
};

// 사용 예제
int main() {
    Stack<int> s;
    
    // 데이터 추가
    s.push(10);
    s.push(20);
    s.push(30);
    
    cout << "Stack size: " << s.size() << endl;
    
    // 데이터 제거하며 출력
    while (!s.empty()) {
        cout << s.pop() << " ";
    }
    cout << endl;
    
    return 0;
}