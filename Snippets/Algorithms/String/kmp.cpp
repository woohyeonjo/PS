#include <iostream>
#include <vector>
#include <string>
using namespace std;

/**
 * KMP (Knuth-Morris-Pratt) 알고리즘
 * 문자열 패턴 매칭 알고리즘
 * 
 * 용도:
 * - 텍스트에서 패턴 문자열 검색
 * - 문자열 내 부분 문자열 찾기
 * - 텍스트 에디터의 검색 기능
 * - DNA 서열 분석
 * - 로그 파일 분석
 * 
 * 특징:
 * - 기존 brute force O(nm)을 O(n+m)으로 개선
 * - 실패 함수(failure function)를 이용한 효율적 검색
 * - 패턴에서 불일치 발생 시 불필요한 비교 생략
 * - 전처리 시간 O(m), 검색 시간 O(n)
 * - 메모리 사용량: O(m) (failure function 저장)
 * 
 * 사용 시나리오:
 * - 긴 텍스트에서 패턴 검색이 빈번한 경우
 * - 같은 패턴으로 여러 텍스트를 검색하는 경우
 * - 실시간 스트리밍 데이터에서 패턴 감지
 * - 바이러스 백신의 시그니처 검색
 * 
 * 실제 사용 사례:
 * - Linux grep 명령어의 내부 구현
 * - 텍스트 에디터 (vim, emacs 등)의 검색 기능
 * - 웹 브라우저의 페이지 내 검색
 * - 바이오인포매틱스의 DNA/RNA 서열 분석
 * - 데이터베이스의 LIKE 연산자 최적화
 * - 네트워크 침입 탐지 시스템
 * 
 * 원리:
 * - 실패 함수로 패턴의 접두사-접미사 일치 정보 저장
 * - 불일치 시 패턴을 처음부터 다시 비교하지 않고 효율적으로 이동
 * - 이미 일치한 부분의 정보를 활용하여 중복 비교 제거
 * - 텍스트의 문자를 한 번씩만 검사하여 선형 시간 달성
 * 
 * 시간복잡도: O(n + m) (n: 텍스트 길이, m: 패턴 길이)
 * 공간복잡도: O(m)
 */

class KMP {
private:
    string pattern;
    vector<int> failure;
    
    // 실패 함수(failure function) 계산
    void compute_failure_function() {
        int m = pattern.length();
        failure.assign(m, 0);
        
        int j = 0;
        for (int i = 1; i < m; i++) {
            // 불일치하는 동안 j를 줄여나감
            while (j > 0 && pattern[i] != pattern[j]) {
                j = failure[j - 1];
            }
            
            // 일치하면 j 증가
            if (pattern[i] == pattern[j]) {
                j++;
            }
            
            failure[i] = j;
        }
    }
    
public:
    // 생성자 - 패턴 설정 및 전처리
    KMP(const string& pat) : pattern(pat) {
        compute_failure_function();
    }
    
    // 패턴 변경
    void set_pattern(const string& pat) {
        pattern = pat;
        compute_failure_function();
    }
    
    // 텍스트에서 패턴의 첫 번째 출현 위치 찾기
    int find_first(const string& text) {
        int n = text.length();
        int m = pattern.length();
        
        if (m == 0) return 0;
        if (m > n) return -1;
        
        int j = 0;  // 패턴 인덱스
        
        for (int i = 0; i < n; i++) {  // 텍스트 인덱스
            // 불일치하는 동안 j를 failure function으로 조정
            while (j > 0 && text[i] != pattern[j]) {
                j = failure[j - 1];
            }
            
            // 일치하면 j 증가
            if (text[i] == pattern[j]) {
                j++;
            }
            
            // 패턴 전체가 일치하면 시작 위치 반환
            if (j == m) {
                return i - m + 1;
            }
        }
        
        return -1;  // 패턴을 찾지 못함
    }
    
    // 텍스트에서 패턴의 모든 출현 위치 찾기
    vector<int> find_all(const string& text) {
        vector<int> positions;
        int n = text.length();
        int m = pattern.length();
        
        if (m == 0) return positions;
        if (m > n) return positions;
        
        int j = 0;  // 패턴 인덱스
        
        for (int i = 0; i < n; i++) {  // 텍스트 인덱스
            // 불일치하는 동안 j를 failure function으로 조정
            while (j > 0 && text[i] != pattern[j]) {
                j = failure[j - 1];
            }
            
            // 일치하면 j 증가
            if (text[i] == pattern[j]) {
                j++;
            }
            
            // 패턴 전체가 일치하면 위치 저장
            if (j == m) {
                positions.push_back(i - m + 1);
                j = failure[j - 1];  // 다음 매칭을 위해 j 조정
            }
        }
        
        return positions;
    }
    
    // 패턴의 개수 세기
    int count_occurrences(const string& text) {
        return find_all(text).size();
    }
    
    // 실패 함수 출력 (디버깅용)
    void print_failure_function() {
        cout << "Pattern: " << pattern << endl;
        cout << "Failure function: ";
        for (int i = 0; i < failure.size(); i++) {
            cout << failure[i] << " ";
        }
        cout << endl;
    }
    
    // KMP 매칭 과정 시각화
    void visualize_matching(const string& text) {
        int n = text.length();
        int m = pattern.length();
        
        if (m == 0 || m > n) return;
        
        cout << "KMP Matching Process:" << endl;
        cout << "Text:    " << text << endl;
        
        int j = 0;
        for (int i = 0; i < n; i++) {
            while (j > 0 && text[i] != pattern[j]) {
                j = failure[j - 1];
            }
            
            if (text[i] == pattern[j]) {
                j++;
            }
            
            // 현재 상태 출력
            cout << "Step " << i + 1 << ": ";
            for (int k = 0; k < i - j + 1; k++) cout << " ";
            cout << pattern;
            for (int k = 0; k < n - (i - j + 1) - m; k++) cout << " ";
            cout << " (j=" << j << ")";
            
            if (j == m) {
                cout << " MATCH!";
                j = failure[j - 1];
            }
            cout << endl;
        }
    }
};

/**
 * 문자열 해싱을 이용한 라빈-카프 알고리즘
 * 롤링 해시를 사용한 빠른 문자열 매칭
 */
class RabinKarp {
private:
    static const int BASE = 256;      // 문자 종류 수
    static const int MOD = 1000000007; // 큰 소수
    
    long long compute_hash(const string& str, int len) {
        long long hash_value = 0;
        long long base_power = 1;
        
        for (int i = 0; i < len; i++) {
            hash_value = (hash_value + (str[i] * base_power) % MOD) % MOD;
            if (i < len - 1) {
                base_power = (base_power * BASE) % MOD;
            }
        }
        
        return hash_value;
    }
    
public:
    vector<int> find_all(const string& text, const string& pattern) {
        vector<int> positions;
        int n = text.length();
        int m = pattern.length();
        
        if (m > n) return positions;
        
        long long pattern_hash = compute_hash(pattern, m);
        long long text_hash = compute_hash(text, m);
        long long base_power = 1;
        
        // base^(m-1) 계산
        for (int i = 0; i < m - 1; i++) {
            base_power = (base_power * BASE) % MOD;
        }
        
        // 첫 번째 윈도우 확인
        if (pattern_hash == text_hash && text.substr(0, m) == pattern) {
            positions.push_back(0);
        }
        
        // 롤링 해시로 나머지 윈도우들 확인
        for (int i = 1; i <= n - m; i++) {
            // 이전 문자 제거하고 새 문자 추가
            text_hash = (text_hash - (text[i - 1] * base_power) % MOD + MOD) % MOD;
            text_hash = (text_hash * BASE + text[i + m - 1]) % MOD;
            
            // 해시가 같으면 실제 문자열 비교
            if (pattern_hash == text_hash && text.substr(i, m) == pattern) {
                positions.push_back(i);
            }
        }
        
        return positions;
    }
};

// 사용 예제
int main() {
    cout << "=== KMP Algorithm Example ===" << endl;
    
    string text = "ABABDABACDABABCABCABCABCABC";
    string pattern = "ABABCABCABCABC";
    
    cout << "Text: " << text << endl;
    cout << "Pattern: " << pattern << endl << endl;
    
    KMP kmp(pattern);
    
    // 실패 함수 출력
    kmp.print_failure_function();
    cout << endl;
    
    // 첫 번째 매칭 위치
    int first_pos = kmp.find_first(text);
    if (first_pos != -1) {
        cout << "First occurrence at position: " << first_pos << endl;
    } else {
        cout << "Pattern not found" << endl;
    }
    
    // 모든 매칭 위치
    vector<int> all_positions = kmp.find_all(text);
    cout << "All occurrences at positions: ";
    for (int pos : all_positions) {
        cout << pos << " ";
    }
    cout << endl;
    
    cout << "Total occurrences: " << kmp.count_occurrences(text) << endl;
    
    cout << "\n=== Simpler Example ===" << endl;
    
    string simple_text = "AABAACAADAABAABA";
    string simple_pattern = "AABA";
    
    cout << "Text: " << simple_text << endl;
    cout << "Pattern: " << simple_pattern << endl << endl;
    
    KMP simple_kmp(simple_pattern);
    simple_kmp.print_failure_function();
    
    // 매칭 과정 시각화
    simple_kmp.visualize_matching(simple_text);
    
    cout << "\n=== Rabin-Karp Comparison ===" << endl;
    
    RabinKarp rk;
    vector<int> rk_positions = rk.find_all(simple_text, simple_pattern);
    
    cout << "Rabin-Karp results: ";
    for (int pos : rk_positions) {
        cout << pos << " ";
    }
    cout << endl;
    
    cout << "\n=== Performance Comparison ===" << endl;
    cout << "Algorithm     | Time Complexity | Space Complexity | Notes" << endl;
    cout << "------------- | --------------- | ---------------- | -----" << endl;
    cout << "Brute Force   | O(nm)          | O(1)             | Simple but slow" << endl;
    cout << "KMP           | O(n+m)         | O(m)             | Fast, reliable" << endl;
    cout << "Rabin-Karp    | O(n+m) avg     | O(1)             | Hash collisions possible" << endl;
    cout << "Boyer-Moore   | O(n/m) best    | O(m+σ)           | Very fast for large alphabets" << endl;
    
    return 0;
}