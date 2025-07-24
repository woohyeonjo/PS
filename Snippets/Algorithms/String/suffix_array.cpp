#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
using namespace std;

/**
 * 접미사 배열 (Suffix Array)
 * 문자열의 모든 접미사를 사전순으로 정렬한 배열
 * 
 * 용도:
 * - 문자열에서 패턴 검색
 * - 가장 긴 공통 접두사(LCP) 계산
 * - 가장 긴 반복 부분 문자열 찾기
 * - 문자열 압축 알고리즘
 * - 바이오인포매틱스에서 서열 분석
 * 
 * 특징:
 * - 접미사 트리의 공간 효율적 대안
 * - 이진 탐색으로 패턴 검색 O(m log n)
 * - LCP 배열과 함께 사용하여 다양한 문자열 문제 해결
 * - 전처리 O(n log n), 검색 O(m log n)
 * - 메모리 사용량: O(n)
 * 
 * 사용 시나리오:
 * - 대용량 텍스트에서 반복적인 패턴 검색
 * - 유전자 서열 분석에서 반복 구조 찾기
 * - 문자열 압축에서 중복 부분 탐지
 * - 텍스트 마이닝에서 공통 패턴 분석
 * 
 * 실제 사용 사례:
 * - 바이오인포매틱스의 게놈 서열 분석
 * - 검색 엔진의 인덱스 구조
 * - 데이터 압축 알고리즘 (BWT, LZ77)
 * - 표절 검사 시스템
 * - 자연언어처리의 n-gram 분석
 * 
 * 원리:
 * - 모든 접미사를 사전순으로 정렬하여 인덱스 저장
 * - 방사 정렬(Radix Sort) 기반의 효율적 구현
 * - 배수 단위로 정렬하여 O(n log n) 시간 달성
 * - LCP 배열로 인접한 접미사들의 공통 접두사 길이 저장
 * 
 * 시간복잡도: 
 * - 구축: O(n log n) 또는 O(n) (advanced)
 * - 패턴 검색: O(m log n)
 * 공간복잡도: O(n)
 */

class SuffixArray {
private:
    string text;
    vector<int> sa;     // 접미사 배열
    vector<int> lcp;    // LCP 배열 (Longest Common Prefix)
    vector<int> rank;   // 각 접미사의 순위
    
    // O(n log^2 n) 기본 구현
    void build_suffix_array_basic() {
        int n = text.length();
        sa.resize(n);
        
        // 초기화: 인덱스만 저장
        for (int i = 0; i < n; i++) {
            sa[i] = i;
        }
        
        // 접미사들을 사전순으로 정렬
        sort(sa.begin(), sa.end(), [&](int i, int j) {
            return text.substr(i) < text.substr(j);
        });
    }
    
    // O(n log n) 효율적 구현 (Radix Sort 기반)
    void build_suffix_array_optimized() {
        int n = text.length();
        sa.resize(n);
        rank.resize(n);
        
        // 초기 순위 설정 (첫 문자 기준)
        for (int i = 0; i < n; i++) {
            sa[i] = i;
            rank[i] = text[i];
        }
        
        // 길이를 2배씩 늘려가며 정렬
        for (int k = 1; k < n; k *= 2) {
            // (rank[i], rank[i+k])를 기준으로 정렬
            sort(sa.begin(), sa.end(), [&](int i, int j) {
                if (rank[i] != rank[j]) {
                    return rank[i] < rank[j];
                }
                int ri = (i + k < n) ? rank[i + k] : -1;
                int rj = (j + k < n) ? rank[j + k] : -1;
                return ri < rj;
            });
            
            // 새로운 순위 계산
            vector<int> new_rank(n);
            new_rank[sa[0]] = 0;
            
            for (int i = 1; i < n; i++) {
                int prev = sa[i - 1];
                int curr = sa[i];
                
                if (rank[prev] == rank[curr] && 
                    ((prev + k < n ? rank[prev + k] : -1) == 
                     (curr + k < n ? rank[curr + k] : -1))) {
                    new_rank[curr] = new_rank[prev];
                } else {
                    new_rank[curr] = new_rank[prev] + 1;
                }
            }
            
            rank = new_rank;
        }
    }
    
    // LCP 배열 구축 (Kasai 알고리즘)
    void build_lcp_array() {
        int n = text.length();
        lcp.resize(n - 1);
        
        vector<int> inverse_sa(n);
        for (int i = 0; i < n; i++) {
            inverse_sa[sa[i]] = i;
        }
        
        int h = 0;
        for (int i = 0; i < n; i++) {
            if (inverse_sa[i] > 0) {
                int j = sa[inverse_sa[i] - 1];
                
                while (i + h < n && j + h < n && text[i + h] == text[j + h]) {
                    h++;
                }
                
                lcp[inverse_sa[i] - 1] = h;
                
                if (h > 0) {
                    h--;
                }
            }
        }
    }
    
    // 이진 탐색으로 패턴의 범위 찾기
    pair<int, int> find_pattern_range(const string& pattern) {
        int n = sa.size();
        int m = pattern.length();
        
        // 왼쪽 경계 찾기
        int left = 0, right = n;
        while (left < right) {
            int mid = (left + right) / 2;
            if (text.substr(sa[mid], min(m, (int)text.length() - sa[mid])) < pattern) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        int start = left;
        
        // 오른쪽 경계 찾기
        left = 0; right = n;
        while (left < right) {
            int mid = (left + right) / 2;
            if (text.substr(sa[mid], min(m, (int)text.length() - sa[mid])) <= pattern) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        int end = left;
        
        return {start, end - 1};
    }
    
public:
    // 생성자
    SuffixArray(const string& s) : text(s) {
        build_suffix_array_optimized();
        build_lcp_array();
    }
    
    // 패턴 검색
    vector<int> search(const string& pattern) {
        vector<int> positions;
        auto range = find_pattern_range(pattern);
        
        if (range.first <= range.second) {
            for (int i = range.first; i <= range.second; i++) {
                positions.push_back(sa[i]);
            }
        }
        
        sort(positions.begin(), positions.end());
        return positions;
    }
    
    // 패턴 개수 세기
    int count_pattern(const string& pattern) {
        auto range = find_pattern_range(pattern);
        return max(0, range.second - range.first + 1);
    }
    
    // 가장 긴 반복 부분 문자열 찾기
    string longest_repeated_substring() {
        int max_lcp = 0;
        int max_index = 0;
        
        for (int i = 0; i < lcp.size(); i++) {
            if (lcp[i] > max_lcp) {
                max_lcp = lcp[i];
                max_index = i;
            }
        }
        
        if (max_lcp == 0) {
            return "";
        }
        
        return text.substr(sa[max_index], max_lcp);
    }
    
    // 가장 긴 공통 부분 문자열 (두 문자열)
    string longest_common_substring(const string& other) {
        string combined = text + "#" + other + "$";
        SuffixArray combined_sa(combined);
        
        int max_lcp = 0;
        int max_index = 0;
        int text_len = text.length();
        
        for (int i = 0; i < combined_sa.lcp.size(); i++) {
            int pos1 = combined_sa.sa[i];
            int pos2 = combined_sa.sa[i + 1];
            
            // 한쪽은 첫 번째 문자열, 다른 쪽은 두 번째 문자열에서 온 접미사
            if ((pos1 < text_len && pos2 > text_len) || 
                (pos1 > text_len && pos2 < text_len)) {
                if (combined_sa.lcp[i] > max_lcp) {
                    max_lcp = combined_sa.lcp[i];
                    max_index = i;
                }
            }
        }
        
        if (max_lcp == 0) {
            return "";
        }
        
        return combined.substr(combined_sa.sa[max_index], max_lcp);
    }
    
    // 고유한 부분 문자열 개수
    long long count_unique_substrings() {
        int n = text.length();
        long long total = (long long)n * (n + 1) / 2;  // 전체 부분 문자열 개수
        
        // LCP로 중복 제거
        for (int i = 0; i < lcp.size(); i++) {
            total -= lcp[i];
        }
        
        return total;
    }
    
    // 접미사 배열 출력
    void print_suffix_array() {
        cout << "Suffix Array:" << endl;
        cout << "Index | Suffix" << endl;
        cout << "------|-------" << endl;
        
        for (int i = 0; i < sa.size(); i++) {
            cout << sa[i] << "     | " << text.substr(sa[i]) << endl;
        }
    }
    
    // LCP 배열 출력
    void print_lcp_array() {
        cout << "\nLCP Array:" << endl;
        cout << "Index | LCP | Suffix 1               | Suffix 2" << endl;
        cout << "------|-----|------------------------|------------------------" << endl;
        
        for (int i = 0; i < lcp.size(); i++) {
            string suffix1 = text.substr(sa[i]);
            string suffix2 = text.substr(sa[i + 1]);
            
            // 길이 제한
            if (suffix1.length() > 20) suffix1 = suffix1.substr(0, 20) + "...";
            if (suffix2.length() > 20) suffix2 = suffix2.substr(0, 20) + "...";
            
            cout << i << "     | " << lcp[i] << "   | " << suffix1;
            for (int j = suffix1.length(); j < 23; j++) cout << " ";
            cout << "| " << suffix2 << endl;
        }
    }
    
    // 통계 정보
    void print_statistics() {
        cout << "\nSuffix Array Statistics:" << endl;
        cout << "Text length: " << text.length() << endl;
        cout << "Unique substrings: " << count_unique_substrings() << endl;
        cout << "Longest repeated substring: \"" << longest_repeated_substring() << "\"" << endl;
        
        // LCP 통계
        if (!lcp.empty()) {
            int max_lcp = *max_element(lcp.begin(), lcp.end());
            int min_lcp = *min_element(lcp.begin(), lcp.end());
            double avg_lcp = 0;
            for (int x : lcp) avg_lcp += x;
            avg_lcp /= lcp.size();
            
            cout << "LCP - Max: " << max_lcp << ", Min: " << min_lcp 
                 << ", Avg: " << avg_lcp << endl;
        }
    }
};

// 사용 예제
int main() {
    cout << "=== Suffix Array Example ===" << endl;
    
    string text = "banana";
    cout << "Text: " << text << endl << endl;
    
    SuffixArray sa(text);
    
    // 접미사 배열 출력
    sa.print_suffix_array();
    
    // LCP 배열 출력
    sa.print_lcp_array();
    
    // 통계 정보
    sa.print_statistics();
    
    cout << "\n=== Pattern Search ===" << endl;
    
    vector<string> patterns = {"an", "na", "ban", "ana", "xyz"};
    
    for (const string& pattern : patterns) {
        vector<int> positions = sa.search(pattern);
        cout << "Pattern \"" << pattern << "\": ";
        
        if (positions.empty()) {
            cout << "Not found" << endl;
        } else {
            cout << "Found at positions: ";
            for (int pos : positions) {
                cout << pos << " ";
            }
            cout << "(" << positions.size() << " occurrences)" << endl;
        }
    }
    
    cout << "\n=== Advanced Queries ===" << endl;
    
    // 가장 긴 반복 부분 문자열
    cout << "Longest repeated substring: \"" << sa.longest_repeated_substring() << "\"" << endl;
    
    // 두 문자열의 가장 긴 공통 부분 문자열
    string other = "ananas";
    cout << "Longest common substring with \"" << other << "\": \"" 
         << sa.longest_common_substring(other) << "\"" << endl;
    
    // 고유한 부분 문자열 개수
    cout << "Number of unique substrings: " << sa.count_unique_substrings() << endl;
    
    cout << "\n=== Larger Example ===" << endl;
    
    string large_text = "abracadabra";
    cout << "Text: " << large_text << endl;
    
    SuffixArray large_sa(large_text);
    large_sa.print_suffix_array();
    large_sa.print_statistics();
    
    cout << "\n=== Performance Comparison ===" << endl;
    cout << "Operation           | Suffix Array | Naive Method | Notes" << endl;
    cout << "------------------- | ------------ | ------------ | -----" << endl;
    cout << "Build               | O(n log n)   | -            | One-time cost" << endl;
    cout << "Pattern search      | O(m log n)   | O(nm)        | Much faster for multiple queries" << endl;
    cout << "All occurrences     | O(m log n+k) | O(nm)        | k = number of occurrences" << endl;
    cout << "Longest repeated    | O(n)         | O(n²)        | Using LCP array" << endl;
    cout << "Unique substrings   | O(n)         | O(n³)        | Using LCP array" << endl;
    
    return 0;
}