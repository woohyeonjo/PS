#include <iostream>
#include <vector>
#include <climits>
using namespace std;

/**
 * 세그먼트 트리 (Segment Tree)
 * 구간 쿼리와 점 업데이트를 O(log n)에 처리하는 자료구조
 * 구간 합, 구간 최솟값, 구간 최댓값 등을 효율적으로 계산
 */

class SegmentTree {
private:
    vector<long long> tree;
    int n;
    
    // 트리 구성
    void build(const vector<int>& arr, int node, int start, int end) {
        if (start == end) {
            // 리프 노드
            tree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;
            // 왼쪽과 오른쪽 자식 노드 구성
            build(arr, 2*node, start, mid);
            build(arr, 2*node+1, mid+1, end);
            // 현재 노드 값은 자식 노드들의 합
            tree[node] = tree[2*node] + tree[2*node+1];
        }
    }
    
    // 구간 합 쿼리
    long long query(int node, int start, int end, int l, int r) {
        if (r < start || end < l) {
            // 범위를 벗어남
            return 0;
        }
        if (l <= start && end <= r) {
            // 완전히 포함됨
            return tree[node];
        }
        // 부분적으로 포함됨
        int mid = (start + end) / 2;
        long long left_sum = query(2*node, start, mid, l, r);
        long long right_sum = query(2*node+1, mid+1, end, l, r);
        return left_sum + right_sum;
    }
    
    // 점 업데이트
    void update(int node, int start, int end, int idx, int val) {
        if (start == end) {
            // 리프 노드
            tree[node] = val;
        } else {
            int mid = (start + end) / 2;
            if (idx <= mid) {
                // 왼쪽 자식 업데이트
                update(2*node, start, mid, idx, val);
            } else {
                // 오른쪽 자식 업데이트
                update(2*node+1, mid+1, end, idx, val);
            }
            // 현재 노드 값 갱신
            tree[node] = tree[2*node] + tree[2*node+1];
        }
    }
    
public:
    SegmentTree(const vector<int>& arr) {
        n = arr.size();
        tree.resize(4 * n);  // 안전하게 4배 크기로 할당
        build(arr, 1, 0, n-1);
    }
    
    // 구간 [l, r]의 합
    long long range_sum(int l, int r) {
        return query(1, 0, n-1, l, r);
    }
    
    // arr[idx] = val로 업데이트
    void point_update(int idx, int val) {
        update(1, 0, n-1, idx, val);
    }
    
    // 디버깅용 트리 출력
    void print_tree() {
        cout << "Segment Tree: ";
        for (int i = 1; i < tree.size() && tree[i] != 0; i++) {
            cout << tree[i] << " ";
        }
        cout << endl;
    }
};

/**
 * 최솟값 세그먼트 트리
 */
class MinSegmentTree {
private:
    vector<int> tree;
    int n;
    
    void build(const vector<int>& arr, int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;
            build(arr, 2*node, start, mid);
            build(arr, 2*node+1, mid+1, end);
            tree[node] = min(tree[2*node], tree[2*node+1]);
        }
    }
    
    int query(int node, int start, int end, int l, int r) {
        if (r < start || end < l) {
            return INT_MAX;  // 범위를 벗어남
        }
        if (l <= start && end <= r) {
            return tree[node];
        }
        int mid = (start + end) / 2;
        int left_min = query(2*node, start, mid, l, r);
        int right_min = query(2*node+1, mid+1, end, l, r);
        return min(left_min, right_min);
    }
    
    void update(int node, int start, int end, int idx, int val) {
        if (start == end) {
            tree[node] = val;
        } else {
            int mid = (start + end) / 2;
            if (idx <= mid) {
                update(2*node, start, mid, idx, val);
            } else {
                update(2*node+1, mid+1, end, idx, val);
            }
            tree[node] = min(tree[2*node], tree[2*node+1]);
        }
    }
    
public:
    MinSegmentTree(const vector<int>& arr) {
        n = arr.size();
        tree.resize(4 * n);
        build(arr, 1, 0, n-1);
    }
    
    int range_min(int l, int r) {
        return query(1, 0, n-1, l, r);
    }
    
    void point_update(int idx, int val) {
        update(1, 0, n-1, idx, val);
    }
};

/**
 * 지연 전파 세그먼트 트리 (Lazy Propagation)
 * 구간 업데이트를 O(log n)에 처리
 */
class LazySegmentTree {
private:
    vector<long long> tree, lazy;
    int n;
    
    void build(const vector<int>& arr, int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;
            build(arr, 2*node, start, mid);
            build(arr, 2*node+1, mid+1, end);
            tree[node] = tree[2*node] + tree[2*node+1];
        }
    }
    
    void push(int node, int start, int end) {
        if (lazy[node] != 0) {
            tree[node] += lazy[node] * (end - start + 1);
            if (start != end) {
                lazy[2*node] += lazy[node];
                lazy[2*node+1] += lazy[node];
            }
            lazy[node] = 0;
        }
    }
    
    void update_range(int node, int start, int end, int l, int r, int val) {
        push(node, start, end);
        if (start > r || end < l) {
            return;
        }
        if (start >= l && end <= r) {
            lazy[node] += val;
            push(node, start, end);
            return;
        }
        int mid = (start + end) / 2;
        update_range(2*node, start, mid, l, r, val);
        update_range(2*node+1, mid+1, end, l, r, val);
        push(2*node, start, mid);
        push(2*node+1, mid+1, end);
        tree[node] = tree[2*node] + tree[2*node+1];
    }
    
    long long query(int node, int start, int end, int l, int r) {
        if (start > r || end < l) {
            return 0;
        }
        push(node, start, end);
        if (start >= l && end <= r) {
            return tree[node];
        }
        int mid = (start + end) / 2;
        long long left_sum = query(2*node, start, mid, l, r);
        long long right_sum = query(2*node+1, mid+1, end, l, r);
        return left_sum + right_sum;
    }
    
public:
    LazySegmentTree(const vector<int>& arr) {
        n = arr.size();
        tree.resize(4 * n);
        lazy.resize(4 * n);
        build(arr, 1, 0, n-1);
    }
    
    void range_update(int l, int r, int val) {
        update_range(1, 0, n-1, l, r, val);
    }
    
    long long range_sum(int l, int r) {
        return query(1, 0, n-1, l, r);
    }
};

// 사용 예제
int main() {
    vector<int> arr = {1, 3, 5, 7, 9, 11};
    
    cout << "Original array: ";
    for (int x : arr) cout << x << " ";
    cout << endl << endl;
    
    // 1. 기본 세그먼트 트리 (구간 합)
    cout << "=== Sum Segment Tree ===" << endl;
    SegmentTree st(arr);
    
    cout << "Sum of range [1, 3]: " << st.range_sum(1, 3) << endl;  // 3+5+7 = 15
    cout << "Sum of range [2, 4]: " << st.range_sum(2, 4) << endl;  // 5+7+9 = 21
    
    st.point_update(1, 10);  // arr[1] = 3 -> 10
    cout << "After updating arr[1] to 10:" << endl;
    cout << "Sum of range [1, 3]: " << st.range_sum(1, 3) << endl;  // 10+5+7 = 22
    
    // 2. 최솟값 세그먼트 트리
    cout << "\n=== Min Segment Tree ===" << endl;
    MinSegmentTree min_st(arr);
    
    cout << "Min of range [1, 3]: " << min_st.range_min(1, 3) << endl;  // min(3,5,7) = 3
    cout << "Min of range [2, 4]: " << min_st.range_min(2, 4) << endl;  // min(5,7,9) = 5
    
    min_st.point_update(2, 2);  // arr[2] = 5 -> 2
    cout << "After updating arr[2] to 2:" << endl;
    cout << "Min of range [1, 3]: " << min_st.range_min(1, 3) << endl;  // min(3,2,7) = 2
    
    // 3. 지연 전파 세그먼트 트리
    cout << "\n=== Lazy Propagation Segment Tree ===" << endl;
    LazySegmentTree lazy_st(arr);
    
    cout << "Sum of range [1, 3]: " << lazy_st.range_sum(1, 3) << endl;
    
    lazy_st.range_update(1, 3, 5);  // arr[1], arr[2], arr[3]에 각각 5 추가
    cout << "After adding 5 to range [1, 3]:" << endl;
    cout << "Sum of range [1, 3]: " << lazy_st.range_sum(1, 3) << endl;
    cout << "Sum of range [0, 5]: " << lazy_st.range_sum(0, 5) << endl;
    
    return 0;
}