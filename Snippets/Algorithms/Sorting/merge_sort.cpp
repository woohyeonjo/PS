#include <iostream>
#include <vector>
using namespace std;

/**
 * 병합 정렬 (Merge Sort)
 * 분할 정복 방식으로 배열을 반으로 나누어 각각 정렬한 후 병합
 * 시간복잡도: O(n log n), 공간복잡도: O(n)
 * 안정 정렬이며 최악의 경우에도 O(n log n) 보장
 */

// 두 개의 정렬된 배열을 병합
void merge(vector<int>& arr, int left, int mid, int right) {
    // 임시 배열 생성
    vector<int> temp(right - left + 1);
    int i = left;    // 왼쪽 부분 배열의 시작 인덱스
    int j = mid + 1; // 오른쪽 부분 배열의 시작 인덱스
    int k = 0;       // 임시 배열의 인덱스
    
    // 두 부분 배열을 비교하며 병합
    while (i <= mid && j <= right) {
        if (arr[i] <= arr[j]) {
            temp[k++] = arr[i++];
        } else {
            temp[k++] = arr[j++];
        }
    }
    
    // 왼쪽 부분 배열의 남은 원소들 복사
    while (i <= mid) {
        temp[k++] = arr[i++];
    }
    
    // 오른쪽 부분 배열의 남은 원소들 복사
    while (j <= right) {
        temp[k++] = arr[j++];
    }
    
    // 임시 배열의 내용을 원본 배열로 복사
    for (int i = 0; i < k; i++) {
        arr[left + i] = temp[i];
    }
}

// 병합 정렬 메인 함수
void merge_sort(vector<int>& arr, int left, int right) {
    if (left < right) {
        int mid = left + (right - left) / 2;
        
        // 왼쪽 절반 정렬
        merge_sort(arr, left, mid);
        
        // 오른쪽 절반 정렬
        merge_sort(arr, mid + 1, right);
        
        // 두 절반을 병합
        merge(arr, left, mid, right);
    }
}

// 편의용 래퍼 함수
void merge_sort(vector<int>& arr) {
    merge_sort(arr, 0, arr.size() - 1);
}

// 사용 예제
int main() {
    vector<int> arr = {38, 27, 43, 3, 9, 82, 10};
    
    cout << "Original array: ";
    for (int x : arr) cout << x << " ";
    cout << endl;
    
    merge_sort(arr);
    
    cout << "Sorted array: ";
    for (int x : arr) cout << x << " ";
    cout << endl;
    
    return 0;
}