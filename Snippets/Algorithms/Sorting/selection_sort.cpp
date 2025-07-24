#include <iostream>
#include <vector>
using namespace std;

/**
 * 선택 정렬 (Selection Sort)
 * 매번 최소값을 찾아서 앞자리와 교환하는 알고리즘
 * 시간복잡도: O(n²), 공간복잡도: O(1)
 * 교환 횟수가 적어서 메모리 쓰기가 비싼 경우 유리
 */
void selection_sort(vector<int>& arr) {
    int n = arr.size();
    
    // 배열의 각 위치에 대해
    for (int i = 0; i < n - 1; i++) {
        int min_idx = i;
        
        // 나머지 원소 중에서 최소값 찾기
        for (int j = i + 1; j < n; j++) {
            if (arr[j] < arr[min_idx]) {
                min_idx = j;
            }
        }
        
        // 최소값을 현재 위치와 교환
        if (min_idx != i) {
            swap(arr[i], arr[min_idx]);
        }
    }
}

// 사용 예제
int main() {
    vector<int> arr = {64, 25, 12, 22, 11};
    
    cout << "Original array: ";
    for (int x : arr) cout << x << " ";
    cout << endl;
    
    selection_sort(arr);
    
    cout << "Sorted array: ";
    for (int x : arr) cout << x << " ";
    cout << endl;
    
    return 0;
}