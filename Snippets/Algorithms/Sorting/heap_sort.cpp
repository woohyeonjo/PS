#include <iostream>
#include <vector>
using namespace std;

/**
 * 힙 정렬 (Heap Sort)
 * 최대 힙을 구성한 후 루트를 제거하며 정렬하는 알고리즘
 * 시간복잡도: O(n log n), 공간복잡도: O(1)
 * 최악의 경우에도 O(n log n) 보장, 제자리 정렬
 */

// 힙 속성을 유지하는 함수 (heapify)
void heapify(vector<int>& arr, int n, int i) {
    int largest = i;      // 루트를 가장 큰 값으로 초기화
    int left = 2 * i + 1; // 왼쪽 자식
    int right = 2 * i + 2; // 오른쪽 자식
    
    // 왼쪽 자식이 루트보다 큰 경우
    if (left < n && arr[left] > arr[largest]) {
        largest = left;
    }
    
    // 오른쪽 자식이 현재 가장 큰 값보다 큰 경우
    if (right < n && arr[right] > arr[largest]) {
        largest = right;
    }
    
    // 가장 큰 값이 루트가 아닌 경우
    if (largest != i) {
        swap(arr[i], arr[largest]);
        
        // 영향을 받은 서브트리를 재귀적으로 heapify
        heapify(arr, n, largest);
    }
}

// 힙 정렬 메인 함수
void heap_sort(vector<int>& arr) {
    int n = arr.size();
    
    // 최대 힙 구성 (bottom-up 방식)
    // 마지막 비단말 노드부터 루트까지
    for (int i = n / 2 - 1; i >= 0; i--) {
        heapify(arr, n, i);
    }
    
    // 힙에서 원소를 하나씩 제거
    for (int i = n - 1; i > 0; i--) {
        // 현재 루트(최대값)를 배열의 끝으로 이동
        swap(arr[0], arr[i]);
        
        // 힙 크기를 줄이고 루트에 대해 heapify 호출
        heapify(arr, i, 0);
    }
}

// 힙 구조 출력 (디버깅용)
void print_heap_structure(const vector<int>& arr) {
    int n = arr.size();
    int level = 0;
    int level_size = 1;
    int current = 0;
    
    cout << "Heap structure:" << endl;
    while (current < n) {
        cout << "Level " << level << ": ";
        for (int i = 0; i < level_size && current < n; i++) {
            cout << arr[current++] << " ";
        }
        cout << endl;
        level++;
        level_size *= 2;
    }
}

// 사용 예제
int main() {
    vector<int> arr = {12, 11, 13, 5, 6, 7};
    
    cout << "Original array: ";
    for (int x : arr) cout << x << " ";
    cout << endl;
    
    // 힙 구성 과정 보기
    vector<int> temp = arr;
    int n = temp.size();
    for (int i = n / 2 - 1; i >= 0; i--) {
        heapify(temp, n, i);
    }
    print_heap_structure(temp);
    
    heap_sort(arr);
    
    cout << "Sorted array: ";
    for (int x : arr) cout << x << " ";
    cout << endl;
    
    return 0;
}