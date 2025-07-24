#include <iostream>
#include <vector>
using namespace std;

/**
 * 삽입 정렬 (Insertion Sort)
 * 배열을 정렬된 부분과 정렬되지 않은 부분으로 나누어
 * 정렬되지 않은 부분의 원소를 정렬된 부분의 적절한 위치에 삽입
 * 시간복잡도: O(n²), 공간복잡도: O(1)
 * 작은 데이터셋이나 거의 정렬된 데이터에 효율적
 */
void insertion_sort(vector<int>& arr) {
    int n = arr.size();
    
    // 두 번째 원소부터 시작 (첫 번째는 이미 정렬된 것으로 간주)
    for (int i = 1; i < n; i++) {
        int key = arr[i];  // 삽입할 원소
        int j = i - 1;     // 정렬된 부분의 마지막 인덱스
        
        // key보다 큰 원소들을 오른쪽으로 이동
        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j];
            j--;
        }
        
        // key를 적절한 위치에 삽입
        arr[j + 1] = key;
    }
}

// 사용 예제
int main() {
    vector<int> arr = {12, 11, 13, 5, 6};
    
    cout << "Original array: ";
    for (int x : arr) cout << x << " ";
    cout << endl;
    
    insertion_sort(arr);
    
    cout << "Sorted array: ";
    for (int x : arr) cout << x << " ";
    cout << endl;
    
    return 0;
}