#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

/**
 * 이진 탐색 (Binary Search)
 * 정렬된 배열에서 특정 값을 찾는 알고리즘
 * 시간복잡도: O(log n), 공간복잡도: O(1)
 * 매번 검색 범위를 절반으로 줄여나감
 */

/**
 * 기본 이진 탐색 - 반복문 버전
 * target이 존재하면 인덱스 반환, 없으면 -1 반환
 */
int binary_search_iterative(const vector<int>& arr, int target) {
    int left = 0;
    int right = arr.size() - 1;
    
    while (left <= right) {
        int mid = left + (right - left) / 2;  // 오버플로우 방지
        
        if (arr[mid] == target) {
            return mid;  // 찾음
        } else if (arr[mid] < target) {
            left = mid + 1;  // 오른쪽 절반에서 검색
        } else {
            right = mid - 1;  // 왼쪽 절반에서 검색
        }
    }
    
    return -1;  // 찾지 못함
}

/**
 * 기본 이진 탐색 - 재귀 버전
 */
int binary_search_recursive(const vector<int>& arr, int target, int left, int right) {
    if (left > right) {
        return -1;  // 찾지 못함
    }
    
    int mid = left + (right - left) / 2;
    
    if (arr[mid] == target) {
        return mid;
    } else if (arr[mid] < target) {
        return binary_search_recursive(arr, target, mid + 1, right);
    } else {
        return binary_search_recursive(arr, target, left, mid - 1);
    }
}

int binary_search_recursive(const vector<int>& arr, int target) {
    return binary_search_recursive(arr, target, 0, arr.size() - 1);
}

/**
 * Lower Bound
 * target 이상의 값이 처음 나타나는 위치를 반환
 * target이 배열에 없다면 target이 삽입될 위치를 반환
 */
int lower_bound(const vector<int>& arr, int target) {
    int left = 0;
    int right = arr.size();
    
    while (left < right) {
        int mid = left + (right - left) / 2;
        
        if (arr[mid] < target) {
            left = mid + 1;
        } else {
            right = mid;
        }
    }
    
    return left;
}

/**
 * Upper Bound
 * target 초과의 값이 처음 나타나는 위치를 반환
 */
int upper_bound(const vector<int>& arr, int target) {
    int left = 0;
    int right = arr.size();
    
    while (left < right) {
        int mid = left + (right - left) / 2;
        
        if (arr[mid] <= target) {
            left = mid + 1;
        } else {
            right = mid;
        }
    }
    
    return left;
}

/**
 * 특정 값의 개수 구하기
 * upper_bound - lower_bound 이용
 */
int count_occurrences(const vector<int>& arr, int target) {
    return upper_bound(arr, target) - lower_bound(arr, target);
}

/**
 * 가장 왼쪽/오른쪽에 있는 target의 인덱스 찾기
 */
int find_first_occurrence(const vector<int>& arr, int target) {
    int pos = lower_bound(arr, target);
    if (pos < arr.size() && arr[pos] == target) {
        return pos;
    }
    return -1;
}

int find_last_occurrence(const vector<int>& arr, int target) {
    int pos = upper_bound(arr, target) - 1;
    if (pos >= 0 && arr[pos] == target) {
        return pos;
    }
    return -1;
}

/**
 * 이진 탐색의 응용 - 조건을 만족하는 값 찾기
 * 예: 제곱근, 최적화 문제 등
 */
double binary_search_real(double left, double right, double target, double epsilon = 1e-9) {
    while (right - left > epsilon) {
        double mid = (left + right) / 2.0;
        double value = mid * mid;  // 제곱근을 구하는 예시
        
        if (value < target) {
            left = mid;
        } else {
            right = mid;
        }
    }
    
    return (left + right) / 2.0;
}

/**
 * 파라메트릭 서치 (Parametric Search)
 * 조건을 만족하는 최대/최소값을 찾는 이진 탐색
 */
bool check_condition(int value, int target) {
    // 예시: value가 target 이상인지 확인
    return value >= target;
}

int parametric_search(int left, int right, int target) {
    int result = -1;
    
    while (left <= right) {
        int mid = left + (right - left) / 2;
        
        if (check_condition(mid, target)) {
            result = mid;  // 조건을 만족하는 값 저장
            right = mid - 1;  // 더 작은 값이 있는지 확인
        } else {
            left = mid + 1;
        }
    }
    
    return result;
}

// 사용 예제
int main() {
    vector<int> arr = {1, 2, 2, 3, 3, 3, 4, 5, 6, 7};
    
    cout << "Array: ";
    for (int x : arr) cout << x << " ";
    cout << endl << endl;
    
    int target = 3;
    
    // 기본 이진 탐색
    cout << "Binary search for " << target << ":" << endl;
    cout << "Iterative: " << binary_search_iterative(arr, target) << endl;
    cout << "Recursive: " << binary_search_recursive(arr, target) << endl;
    
    // Lower/Upper bound
    cout << "\nLower bound of " << target << ": " << lower_bound(arr, target) << endl;
    cout << "Upper bound of " << target << ": " << upper_bound(arr, target) << endl;
    
    // STL 함수와 비교
    cout << "STL lower_bound: " << (lower_bound(arr.begin(), arr.end(), target) - arr.begin()) << endl;
    cout << "STL upper_bound: " << (upper_bound(arr.begin(), arr.end(), target) - arr.begin()) << endl;
    
    // 개수 세기
    cout << "\nCount of " << target << ": " << count_occurrences(arr, target) << endl;
    
    // 첫 번째/마지막 발생 위치
    cout << "First occurrence of " << target << ": " << find_first_occurrence(arr, target) << endl;
    cout << "Last occurrence of " << target << ": " << find_last_occurrence(arr, target) << endl;
    
    // 실수 이진 탐색 (제곱근)
    double num = 25.0;
    cout << "\nSquare root of " << num << ": " << binary_search_real(0, num, num) << endl;
    
    return 0;
}