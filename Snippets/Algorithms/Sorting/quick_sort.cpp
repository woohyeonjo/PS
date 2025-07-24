#include <iostream>
#include <vector>
using namespace std;

/**
 * 퀵 정렬 (Quick Sort)
 * 피벗을 선택하여 피벗보다 작은 원소는 왼쪽, 큰 원소는 오른쪽으로 분할
 * 시간복잡도: 평균 O(n log n), 최악 O(n²), 공간복잡도: O(log n)
 * 평균적으로 가장 빠른 정렬 알고리즘 중 하나
 */

// 파티션 함수 - 피벗을 기준으로 배열을 분할
int partition(vector<int>& arr, int low, int high) {
    int pivot = arr[high];  // 마지막 원소를 피벗으로 선택
    int i = low - 1;        // 작은 원소들의 인덱스
    
    for (int j = low; j < high; j++) {
        // 현재 원소가 피벗보다 작거나 같으면
        if (arr[j] <= pivot) {
            i++;
            swap(arr[i], arr[j]);
        }
    }
    
    // 피벗을 올바른 위치에 배치
    swap(arr[i + 1], arr[high]);
    return i + 1;
}

// 퀵 정렬 메인 함수
void quick_sort(vector<int>& arr, int low, int high) {
    if (low < high) {
        // 파티션 인덱스를 구함
        int pi = partition(arr, low, high);
        
        // 피벗을 기준으로 왼쪽과 오른쪽을 재귀적으로 정렬
        quick_sort(arr, low, pi - 1);   // 피벗 이전 부분
        quick_sort(arr, pi + 1, high);  // 피벗 이후 부분
    }
}

// 편의용 래퍼 함수
void quick_sort(vector<int>& arr) {
    if (!arr.empty()) {
        quick_sort(arr, 0, arr.size() - 1);
    }
}

// 랜덤 피벗을 사용하는 버전 (최악의 경우 방지)
int randomized_partition(vector<int>& arr, int low, int high) {
    // 랜덤 인덱스를 선택하여 마지막 원소와 교환
    int random_index = low + rand() % (high - low + 1);
    swap(arr[random_index], arr[high]);
    return partition(arr, low, high);
}

void randomized_quick_sort(vector<int>& arr, int low, int high) {
    if (low < high) {
        int pi = randomized_partition(arr, low, high);
        randomized_quick_sort(arr, low, pi - 1);
        randomized_quick_sort(arr, pi + 1, high);
    }
}

// 사용 예제
int main() {
    vector<int> arr = {10, 7, 8, 9, 1, 5};
    
    cout << "Original array: ";
    for (int x : arr) cout << x << " ";
    cout << endl;
    
    quick_sort(arr);
    
    cout << "Sorted array: ";
    for (int x : arr) cout << x << " ";
    cout << endl;
    
    return 0;
}