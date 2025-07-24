#include <iostream>
#include <vector>
using namespace std;

/**
 * 펜윅 트리 (Fenwick Tree) / 바이너리 인덱스드 트리 (Binary Indexed Tree, BIT)
 * 
 * 용도:
 * - 배열의 구간 합을 효율적으로 계산하고 원소를 업데이트
 * - 동적으로 변하는 배열에서 구간 쿼리 처리
 * - 순서 통계량 계산 (k번째 작은 원소 찾기)
 * - 2D 배열의 구간 합 계산
 * 
 * 특징:
 * - 세그먼트 트리보다 메모리 효율적 (N vs 4N)
 * - 구현이 매우 간단하고 코드가 짧음
 * - 상수 인수가 작아 실제 성능이 우수
 * - 합 연산에 특화되어 있음 (min/max는 제한적)
 * - 1-based 인덱싱을 사용하는 것이 일반적
 * 
 * 사용 시나리오:
 * - 구간 합 쿼리가 빈번한 경우
 * - 메모리 제약이 있는 환경
 * - 단순한 구간 연산만 필요한 경우
 * - 코딩 테스트나 온라인 저지에서 구간 합 문제
 * - 순서 통계나 역 정렬 개수 계산
 * 
 * 실제 사용 사례:
 * - 온라인 저지 시스템의 구간 합 문제
 * - 실시간 통계 시스템 (매출 합계, 조회수 집계)
 * - 게임에서 순위 시스템 구현
 * - 데이터베이스의 집계 쿼리 최적화
 * - 금융 시스템의 거래량 집계
 * - 네트워크 트래픽 모니터링
 * 
 * 원리:
 * - 각 인덱스 i는 2의 거듭제곱 길이만큼의 구간 합을 저장
 * - LSB(Least Significant Bit)를 이용한 효율적인 구간 분할
 * - 업데이트 시 LSB만큼 점프하며 관련된 구간들만 갱신
 * - 구간 합 계산 시 LSB를 빼가며 필요한 구간들만 합산
 * - 이진수 표현의 비트 패턴을 활용한 트리 구조
 * 
 * 핵심 아이디어:
 * - 각 인덱스 i는 2의 거듭제곱 범위의 합을 저장
 * - i & (-i): i의 최하위 비트 (LSB, Least Significant Bit)
 * - tree[i]는 [i - (i & -i) + 1, i] 범위의 합을 저장
 * 
 * 시간복잡도:
 * - 구간 합 계산: O(log n)
 * - 점 업데이트: O(log n)
 * - 초기화: O(n log n)
 * 
 * 공간복잡도: O(n)
 */

class FenwickTree {
private:
    vector<long long> tree;
    int n;
    
    // 최하위 비트 (LSB) 계산
    int lowbit(int x) {
        return x & (-x);
    }
    
public:
    // 생성자 - 크기만 지정
    FenwickTree(int size) : n(size) {
        tree.assign(n + 1, 0);  // 1-indexed
    }
    
    // 생성자 - 배열로 초기화
    FenwickTree(const vector<int>& arr) : n(arr.size()) {
        tree.assign(n + 1, 0);
        
        // 배열의 각 원소를 하나씩 업데이트
        for (int i = 0; i < n; i++) {
            update(i + 1, arr[i]);  // 1-indexed
        }
    }
    
    // 점 업데이트: arr[idx] += delta
    void update(int idx, long long delta) {
        for (int i = idx; i <= n; i += lowbit(i)) {
            tree[i] += delta;
        }
    }
    
    // 점 설정: arr[idx] = value (기존 값과의 차이만큼 업데이트)
    void set(int idx, long long value) {
        long long current = query(idx, idx);  // 현재 값
        update(idx, value - current);
    }
    
    // 구간 합 계산: [1, idx]
    long long prefix_sum(int idx) {
        long long sum = 0;
        for (int i = idx; i > 0; i -= lowbit(i)) {
            sum += tree[i];
        }
        return sum;
    }
    
    // 구간 합 계산: [left, right]
    long long query(int left, int right) {
        if (left > right) return 0;
        if (left == 1) return prefix_sum(right);
        return prefix_sum(right) - prefix_sum(left - 1);
    }
    
    // 특정 인덱스의 값 조회
    long long get(int idx) {
        return query(idx, idx);
    }
    
    // 전체 합
    long long total_sum() {
        return prefix_sum(n);
    }
    
    // 트리 상태 출력 (디버깅용)
    void print_tree() {
        cout << "Fenwick Tree internal state:" << endl;
        for (int i = 1; i <= n; i++) {
            cout << "tree[" << i << "] = " << tree[i] 
                 << " (covers range ending at " << i << ")" << endl;
        }
        cout << endl;
    }
    
    // 원본 배열 복원 (디버깅용)
    void print_array() {
        cout << "Reconstructed array: ";
        for (int i = 1; i <= n; i++) {
            cout << get(i) << " ";
        }
        cout << endl;
    }
};

/**
 * 2D 펜윅 트리
 * 2차원 배열의 구간 합을 효율적으로 계산
 */
class FenwickTree2D {
private:
    vector<vector<long long>> tree;
    int rows, cols;
    
    int lowbit(int x) {
        return x & (-x);
    }
    
public:
    FenwickTree2D(int r, int c) : rows(r), cols(c) {
        tree.assign(rows + 1, vector<long long>(cols + 1, 0));
    }
    
    // 점 업데이트: arr[row][col] += delta
    void update(int row, int col, long long delta) {
        for (int i = row; i <= rows; i += lowbit(i)) {
            for (int j = col; j <= cols; j += lowbit(j)) {
                tree[i][j] += delta;
            }
        }
    }
    
    // 구간 합 계산: [1,1] to [row,col]
    long long prefix_sum(int row, int col) {
        long long sum = 0;
        for (int i = row; i > 0; i -= lowbit(i)) {
            for (int j = col; j > 0; j -= lowbit(j)) {
                sum += tree[i][j];
            }
        }
        return sum;
    }
    
    // 구간 합 계산: [r1,c1] to [r2,c2]
    long long query(int r1, int c1, int r2, int c2) {
        return prefix_sum(r2, c2) - prefix_sum(r1 - 1, c2) 
               - prefix_sum(r2, c1 - 1) + prefix_sum(r1 - 1, c1 - 1);
    }
};

/**
 * 차분 배열을 이용한 구간 업데이트 펜윅 트리
 * 구간 업데이트와 점 쿼리를 모두 O(log n)에 처리
 */
class RangeUpdateFenwickTree {
private:
    FenwickTree diff_tree;  // 차분 배열의 펜윅 트리
    
public:
    RangeUpdateFenwickTree(int size) : diff_tree(size) {}
    
    RangeUpdateFenwickTree(const vector<int>& arr) : diff_tree(arr.size()) {
        // 차분 배열 구성
        for (int i = 0; i < arr.size(); i++) {
            int delta = arr[i];
            if (i > 0) delta -= arr[i-1];
            diff_tree.update(i + 1, delta);
        }
    }
    
    // 구간 업데이트: [left, right] 범위에 value 추가
    void range_update(int left, int right, long long value) {
        diff_tree.update(left, value);
        diff_tree.update(right + 1, -value);
    }
    
    // 점 쿼리: arr[idx] 값 조회
    long long point_query(int idx) {
        return diff_tree.prefix_sum(idx);
    }
};

/**
 * 최댓값/최솟값을 지원하는 펜윅 트리 (특수한 경우)
 * 일반적인 펜윅 트리는 합만 지원하지만, 특정 조건하에서 max/min도 가능
 * 여기서는 "증가만 하는" 업데이트의 경우 최댓값을 지원
 */
class MaxFenwickTree {
private:
    vector<long long> tree;
    int n;
    
    int lowbit(int x) {
        return x & (-x);
    }
    
public:
    MaxFenwickTree(int size) : n(size) {
        tree.assign(n + 1, 0);
    }
    
    // 업데이트: arr[idx] = max(arr[idx], value)
    void update(int idx, long long value) {
        for (int i = idx; i <= n; i += lowbit(i)) {
            tree[i] = max(tree[i], value);
        }
    }
    
    // 구간 최댓값: [1, idx]
    long long prefix_max(int idx) {
        long long result = 0;
        for (int i = idx; i > 0; i -= lowbit(i)) {
            result = max(result, tree[i]);
        }
        return result;
    }
};

// 사용 예제
int main() {
    cout << "=== Basic Fenwick Tree Example ===" << endl;
    
    // 초기 배열로 펜윅 트리 생성
    vector<int> arr = {1, 3, 5, 7, 9, 11};
    FenwickTree ft(arr);
    
    cout << "Initial array: ";
    for (int x : arr) cout << x << " ";
    cout << endl;
    
    ft.print_array();
    
    // 구간 합 쿼리
    cout << "\nRange sum queries:" << endl;
    cout << "Sum [1, 3]: " << ft.query(1, 3) << endl;  // 1 + 3 + 5 = 9
    cout << "Sum [2, 5]: " << ft.query(2, 5) << endl;  // 3 + 5 + 7 + 9 = 24
    cout << "Sum [4, 6]: " << ft.query(4, 6) << endl;  // 7 + 9 + 11 = 27
    
    // 점 업데이트
    cout << "\nUpdating arr[3] += 10..." << endl;
    ft.update(3, 10);  // arr[3] = 5 + 10 = 15
    
    ft.print_array();
    
    cout << "Sum [1, 3] after update: " << ft.query(1, 3) << endl;  // 1 + 3 + 15 = 19
    cout << "Sum [2, 5] after update: " << ft.query(2, 5) << endl;  // 3 + 15 + 7 + 9 = 34
    
    // 점 설정
    cout << "\nSetting arr[2] = 20..." << endl;
    ft.set(2, 20);
    
    ft.print_array();
    cout << "Total sum: " << ft.total_sum() << endl;
    
    cout << "\n=== 2D Fenwick Tree Example ===" << endl;
    
    FenwickTree2D ft2d(4, 4);
    
    // 2D 배열에 값 설정
    ft2d.update(1, 1, 1);
    ft2d.update(1, 2, 2);
    ft2d.update(2, 1, 3);
    ft2d.update(2, 2, 4);
    ft2d.update(3, 3, 5);
    
    cout << "2D array updates completed" << endl;
    cout << "Sum of rectangle (1,1) to (2,2): " << ft2d.query(1, 1, 2, 2) << endl;  // 1+2+3+4 = 10
    cout << "Sum of rectangle (1,1) to (3,3): " << ft2d.query(1, 1, 3, 3) << endl;  // 1+2+3+4+5 = 15
    
    cout << "\n=== Range Update Fenwick Tree Example ===" << endl;
    
    vector<int> initial = {0, 0, 0, 0, 0};  // 0으로 초기화된 배열
    RangeUpdateFenwickTree ruft(initial);
    
    cout << "Initial array: ";
    for (int i = 1; i <= 5; i++) {
        cout << ruft.point_query(i) << " ";
    }
    cout << endl;
    
    // 구간 업데이트
    cout << "\nRange update [2, 4] += 3..." << endl;
    ruft.range_update(2, 4, 3);
    
    cout << "Array after range update: ";
    for (int i = 1; i <= 5; i++) {
        cout << ruft.point_query(i) << " ";
    }
    cout << endl;
    
    cout << "\nRange update [1, 3] += 5..." << endl;
    ruft.range_update(1, 3, 5);
    
    cout << "Array after second range update: ";
    for (int i = 1; i <= 5; i++) {
        cout << ruft.point_query(i) << " ";
    }
    cout << endl;
    
    cout << "\n=== Max Fenwick Tree Example ===" << endl;
    
    MaxFenwickTree max_ft(5);
    
    // 값들을 업데이트 (증가만)
    max_ft.update(1, 10);
    max_ft.update(2, 5);
    max_ft.update(3, 15);
    max_ft.update(2, 20);  // arr[2] = max(5, 20) = 20
    
    cout << "Max in range [1, 1]: " << max_ft.prefix_max(1) << endl;  // 10
    cout << "Max in range [1, 2]: " << max_ft.prefix_max(2) << endl;  // max(10, 20) = 20
    cout << "Max in range [1, 3]: " << max_ft.prefix_max(3) << endl;  // max(10, 20, 15) = 20
    
    cout << "\n=== Performance Comparison Info ===" << endl;
    cout << "Fenwick Tree vs Segment Tree:" << endl;
    cout << "- Memory: Fenwick Tree uses ~N, Segment Tree uses ~4N" << endl;
    cout << "- Implementation: Fenwick Tree is simpler" << endl;
    cout << "- Functionality: Segment Tree is more versatile" << endl;
    cout << "- Use Fenwick Tree for: range sum, point update problems" << endl;
    cout << "- Use Segment Tree for: range min/max, complex range operations" << endl;
    
    return 0;
}