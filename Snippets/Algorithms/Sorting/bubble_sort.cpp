#include <iostream>
#include <vector>
using namespace std;

/**
 * 버블 정렬 (Bubble Sort)
 * 인접한 두 원소를 비교하여 정렬하는 알고리즘
 * 시간복잡도: O(n²), 공간복잡도: O(1)
 * 구현이 간단하지만 효율성이 낮음
 */
void bubble_sort(vector<int>& arr) {
    int n = arr.size();
    
    // n-1번의 패스 수행
    for (int i = 0; i < n - 1; i++) {
        bool swapped = false;
        
        // 각 패스에서 가장 큰 원소를 맨 뒤로 이동
        for (int j = 0; j < n - 1 - i; j++) {
            if (arr[j] > arr[j + 1]) {
                swap(arr[j], arr[j + 1]);
                swapped = true;
            }
        }
        
        // 교환이 발생하지 않았다면 이미 정렬됨
        if (!swapped) {
            break;
        }
    }
}

// 사용 예제
int main() {
    vector<int> arr = {64, 34, 25, 12, 22, 11, 90};
    
    cout << "Original array: ";
    for (int x : arr) cout << x << " ";
    cout << endl;
    
    bubble_sort(arr);
    
    cout << "Sorted array: ";
    for (int x : arr) cout << x << " ";
    cout << endl;
    
    return 0;
}