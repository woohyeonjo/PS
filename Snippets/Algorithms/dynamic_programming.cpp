#include <iostream>
#include <vector>
#include <algorithm>
#include <climits>
using namespace std;

/**
 * 동적 계획법 (Dynamic Programming)
 * 큰 문제를 작은 하위 문제로 나누어 해결하는 알고리즘 설계 기법
 * 메모이제이션을 통해 중복 계산을 방지하여 효율성 향상
 */

/**
 * 1. 피보나치 수열
 * F(n) = F(n-1) + F(n-2), F(0) = 0, F(1) = 1
 * 시간복잡도: O(n), 공간복잡도: O(n)
 */
long long fibonacci_dp(int n) {
    if (n <= 1) return n;
    
    vector<long long> dp(n + 1);
    dp[0] = 0;
    dp[1] = 1;
    
    for (int i = 2; i <= n; i++) {
        dp[i] = dp[i-1] + dp[i-2];
    }
    
    return dp[n];
}

// 공간 최적화 버전
long long fibonacci_optimized(int n) {
    if (n <= 1) return n;
    
    long long prev2 = 0, prev1 = 1;
    long long current;
    
    for (int i = 2; i <= n; i++) {
        current = prev1 + prev2;
        prev2 = prev1;
        prev1 = current;
    }
    
    return current;
}

/**
 * 2. 계단 오르기
 * n개의 계단을 1칸 또는 2칸씩 올라갈 수 있을 때, 
 * n번째 계단에 도달하는 방법의 수
 */
int climb_stairs(int n) {
    if (n <= 2) return n;
    
    vector<int> dp(n + 1);
    dp[1] = 1;
    dp[2] = 2;
    
    for (int i = 3; i <= n; i++) {
        dp[i] = dp[i-1] + dp[i-2];
    }
    
    return dp[n];
}

/**
 * 3. 최장 증가 부분 수열 (LIS - Longest Increasing Subsequence)
 * 시간복잡도: O(n²)
 */
int longest_increasing_subsequence(const vector<int>& arr) {
    int n = arr.size();
    if (n == 0) return 0;
    
    vector<int> dp(n, 1);  // dp[i] = i번째 원소를 마지막으로 하는 LIS의 길이
    
    for (int i = 1; i < n; i++) {
        for (int j = 0; j < i; j++) {
            if (arr[j] < arr[i]) {
                dp[i] = max(dp[i], dp[j] + 1);
            }
        }
    }
    
    return *max_element(dp.begin(), dp.end());
}

// LIS O(n log n) 버전 (이진 탐색 사용)
int lis_binary_search(const vector<int>& arr) {
    vector<int> lis;
    
    for (int num : arr) {
        auto it = lower_bound(lis.begin(), lis.end(), num);
        if (it == lis.end()) {
            lis.push_back(num);
        } else {
            *it = num;
        }
    }
    
    return lis.size();
}

/**
 * 4. 배낭 문제 (0-1 Knapsack Problem)
 * n개의 물건과 용량이 W인 배낭이 있을 때,
 * 배낭에 넣을 수 있는 물건들의 최대 가치
 */
int knapsack(const vector<int>& weights, const vector<int>& values, int capacity) {
    int n = weights.size();
    vector<vector<int>> dp(n + 1, vector<int>(capacity + 1, 0));
    
    // dp[i][w] = i번째까지의 물건을 고려했을 때, 용량 w에서의 최대 가치
    for (int i = 1; i <= n; i++) {
        for (int w = 0; w <= capacity; w++) {
            // i번째 물건을 선택하지 않는 경우
            dp[i][w] = dp[i-1][w];
            
            // i번째 물건을 선택하는 경우 (용량이 충분하다면)
            if (weights[i-1] <= w) {
                dp[i][w] = max(dp[i][w], dp[i-1][w-weights[i-1]] + values[i-1]);
            }
        }
    }
    
    return dp[n][capacity];
}

// 공간 최적화 버전
int knapsack_optimized(const vector<int>& weights, const vector<int>& values, int capacity) {
    vector<int> dp(capacity + 1, 0);
    
    for (int i = 0; i < weights.size(); i++) {
        for (int w = capacity; w >= weights[i]; w--) {
            dp[w] = max(dp[w], dp[w - weights[i]] + values[i]);
        }
    }
    
    return dp[capacity];
}

/**
 * 5. 최장 공통 부분 수열 (LCS - Longest Common Subsequence)
 * 두 문자열의 가장 긴 공통 부분 수열의 길이
 */
int longest_common_subsequence(const string& text1, const string& text2) {
    int m = text1.length();
    int n = text2.length();
    
    vector<vector<int>> dp(m + 1, vector<int>(n + 1, 0));
    
    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
            if (text1[i-1] == text2[j-1]) {
                dp[i][j] = dp[i-1][j-1] + 1;
            } else {
                dp[i][j] = max(dp[i-1][j], dp[i][j-1]);
            }
        }
    }
    
    return dp[m][n];
}

/**
 * 6. 편집 거리 (Edit Distance / Levenshtein Distance)
 * 한 문자열을 다른 문자열로 변환하는데 필요한 최소 연산 수
 */
int edit_distance(const string& word1, const string& word2) {
    int m = word1.length();
    int n = word2.length();
    
    vector<vector<int>> dp(m + 1, vector<int>(n + 1));
    
    // 초기화: 빈 문자열에서 변환하는 경우
    for (int i = 0; i <= m; i++) dp[i][0] = i;
    for (int j = 0; j <= n; j++) dp[0][j] = j;
    
    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
            if (word1[i-1] == word2[j-1]) {
                dp[i][j] = dp[i-1][j-1];  // 문자가 같으면 변환 불필요
            } else {
                dp[i][j] = 1 + min({
                    dp[i-1][j],    // 삭제
                    dp[i][j-1],    // 삽입
                    dp[i-1][j-1]   // 대체
                });
            }
        }
    }
    
    return dp[m][n];
}

/**
 * 7. 동전 교환 문제 (Coin Change)
 * 주어진 동전들로 목표 금액을 만드는 최소 동전 개수
 */
int coin_change(const vector<int>& coins, int amount) {
    vector<int> dp(amount + 1, INT_MAX);
    dp[0] = 0;
    
    for (int i = 1; i <= amount; i++) {
        for (int coin : coins) {
            if (coin <= i && dp[i - coin] != INT_MAX) {
                dp[i] = min(dp[i], dp[i - coin] + 1);
            }
        }
    }
    
    return dp[amount] == INT_MAX ? -1 : dp[amount];
}

// 사용 예제
int main() {
    // 1. 피보나치
    cout << "=== Fibonacci ===" << endl;
    cout << "F(10) = " << fibonacci_dp(10) << endl;
    cout << "F(10) optimized = " << fibonacci_optimized(10) << endl;
    
    // 2. 계단 오르기
    cout << "\n=== Climb Stairs ===" << endl;
    cout << "Ways to climb 5 stairs: " << climb_stairs(5) << endl;
    
    // 3. LIS
    cout << "\n=== Longest Increasing Subsequence ===" << endl;
    vector<int> arr = {10, 9, 2, 5, 3, 7, 101, 18};
    cout << "Array: ";
    for (int x : arr) cout << x << " ";
    cout << endl;
    cout << "LIS length (O(n²)): " << longest_increasing_subsequence(arr) << endl;
    cout << "LIS length (O(n log n)): " << lis_binary_search(arr) << endl;
    
    // 4. 배낭 문제
    cout << "\n=== Knapsack Problem ===" << endl;
    vector<int> weights = {1, 3, 4, 5};
    vector<int> values = {1, 4, 5, 7};
    int capacity = 7;
    cout << "Max value: " << knapsack(weights, values, capacity) << endl;
    cout << "Max value (optimized): " << knapsack_optimized(weights, values, capacity) << endl;
    
    // 5. LCS
    cout << "\n=== Longest Common Subsequence ===" << endl;
    string text1 = "abcde";
    string text2 = "ace";
    cout << "LCS of '" << text1 << "' and '" << text2 << "': " 
         << longest_common_subsequence(text1, text2) << endl;
    
    // 6. 편집 거리
    cout << "\n=== Edit Distance ===" << endl;
    string word1 = "horse";
    string word2 = "ros";
    cout << "Edit distance between '" << word1 << "' and '" << word2 << "': "
         << edit_distance(word1, word2) << endl;
    
    // 7. 동전 교환
    cout << "\n=== Coin Change ===" << endl;
    vector<int> coins = {1, 3, 4};
    int amount = 6;
    cout << "Min coins for amount " << amount << ": " << coin_change(coins, amount) << endl;
    
    return 0;
}