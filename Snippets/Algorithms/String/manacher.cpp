#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
using namespace std;

/**
 * 매나커(Manacher) 알고리즘
 * 문자열의 모든 회문(Palindrome)을 선형 시간에 찾는 알고리즘
 * 
 * 용도:
 * - 문자열에서 가장 긴 회문 부분 문자열 찾기
 * - 모든 회문 부분 문자열의 위치와 길이 계산
 * - 회문 관련 문제 최적화
 * - DNA 서열 분석에서 회문 구조 찾기
 * 
 * 특징:
 * - 기존 O(n³) 또는 O(n²) 회문 검사를 O(n)으로 최적화
 * - 홀수 길이와 짝수 길이 회문을 통합하여 처리
 * - 이전에 계산된 정보를 활용하여 중복 계산 제거
 * - 중심 확장법의 효율적 구현
 * 
 * 사용 시나리오:
 * - 긴 문자열에서 회문 검색이 필요한 경우
 * - 회문의 개수나 길이 통계가 필요한 경우
 * - 문자열 압축이나 패턴 분석
 * - 유전자 서열 분석
 * 
 * 실제 사용 사례:
 * - 바이오인포매틱스의 DNA 회문 분석
 * - 자연어 처리의 회문 단어 검색
 * - 암호학의 회문 기반 해시
 * - 텍스트 마이닝의 패턴 분석
 * - 온라인 저지의 회문 문제
 * 
 * 원리:
 * - 문자열을 변환하여 홀수/짝수 길이 회문 통합 처리
 * - 각 위치에서 회문 반지름 정보 저장
 * - 이미 계산된 회문 정보를 활용하여 효율적으로 확장
 * - 중심점과 우경계를 추적하여 중복 계산 방지
 * 
 * 시간복잡도: O(n)
 * 공간복잡도: O(n)
 */

class Manacher {
private:
    string processed;  // 전처리된 문자열
    vector<int> radius; // 각 위치의 회문 반지름
    
    // 문자열 전처리: "abc" -> "^#a#b#c#$"
    string preprocess(const string& s) {
        string result = "^";
        for (char c : s) {
            result += "#";
            result += c;
        }
        result += "#$";
        return result;
    }
    
    // 매나커 알고리즘 메인 로직
    void compute_palindromes() {
        int n = processed.length();
        radius.assign(n, 0);
        
        int center = 0;     // 현재 회문의 중심
        int right = 0;      // 현재 회문의 우경계
        
        for (int i = 1; i < n - 1; i++) {
            // i가 우경계 안에 있다면 대칭 정보 활용
            if (i < right) {
                int mirror = 2 * center - i;  // i의 대칭점
                radius[i] = min(right - i, radius[mirror]);
            }
            
            // 중심 확장으로 회문 길이 계산
            while (processed[i + radius[i] + 1] == processed[i - radius[i] - 1]) {
                radius[i]++;
            }
            
            // 새로운 회문이 더 오른쪽까지 확장되면 업데이트
            if (i + radius[i] > right) {
                center = i;
                right = i + radius[i];
            }
        }
    }
    
public:
    // 생성자
    Manacher(const string& s) {
        processed = preprocess(s);
        compute_palindromes();
    }
    
    // 가장 긴 회문 부분 문자열 찾기
    string longest_palindrome(const string& s) {
        processed = preprocess(s);
        compute_palindromes();
        
        int max_len = 0;
        int center_index = 0;
        
        // 가장 긴 회문 찾기
        for (int i = 1; i < processed.length() - 1; i++) {
            if (radius[i] > max_len) {
                max_len = radius[i];
                center_index = i;
            }
        }
        
        // 원본 문자열에서의 시작 위치 계산
        int start = (center_index - max_len) / 2;
        return s.substr(start, max_len);
    }
    
    // 특정 위치가 회문의 중심인지 확인
    bool is_palindrome_center(int center, int len) {
        if (len <= 0) return false;
        
        // 원본 인덱스를 처리된 인덱스로 변환
        int processed_center = 2 * center + 1;
        if (len % 2 == 0) {
            processed_center = 2 * center + 2;
        }
        
        return processed_center < radius.size() && 
               radius[processed_center] >= len / 2;
    }
    
    // 모든 회문 부분 문자열 찾기
    vector<pair<int, int>> find_all_palindromes(const string& s) {
        processed = preprocess(s);
        compute_palindromes();
        
        vector<pair<int, int>> palindromes;
        
        for (int i = 1; i < processed.length() - 1; i++) {
            if (radius[i] > 0) {
                // 원본 문자열에서의 시작 위치와 길이 계산
                int start = (i - radius[i]) / 2;
                int length = radius[i];
                palindromes.push_back({start, length});
            }
        }
        
        return palindromes;
    }
    
    // 회문 개수 세기
    int count_palindromes() {
        int count = 0;
        for (int i = 1; i < radius.size() - 1; i++) {
            count += (radius[i] + 1) / 2;  // 각 위치에서 가능한 회문 개수
        }
        return count;
    }
    
    // 특정 범위가 회문인지 O(1)에 확인
    bool is_palindrome(const string& s, int left, int right) {
        processed = preprocess(s);
        compute_palindromes();
        
        int len = right - left + 1;
        int center = (left + right) / 2;
        
        // 홀수 길이 회문
        if (len % 2 == 1) {
            int processed_center = 2 * center + 1;
            return processed_center < radius.size() && 
                   radius[processed_center] >= len / 2;
        }
        // 짝수 길이 회문
        else {
            int processed_center = 2 * center + 2;
            return processed_center < radius.size() && 
                   radius[processed_center] >= len / 2;
        }
    }
    
    // 각 위치의 회문 정보 출력 (디버깅용)
    void print_debug_info(const string& s) {
        cout << "Original: " << s << endl;
        cout << "Processed: " << processed << endl;
        cout << "Radius: ";
        for (int i = 0; i < radius.size(); i++) {
            cout << radius[i] << " ";
        }
        cout << endl;
        
        cout << "Palindromes found:" << endl;
        for (int i = 1; i < processed.length() - 1; i++) {
            if (radius[i] > 0) {
                int start = (i - radius[i]) / 2;
                int length = radius[i];
                cout << "Position " << start << ", Length " << length 
                     << ": \"" << s.substr(start, length) << "\"" << endl;
            }
        }
    }
};

/**
 * 단순한 회문 검사 알고리즘들 (비교용)
 */
class SimplePalindrome {
public:
    // O(n²) 모든 부분 문자열 회문 검사
    string longest_palindrome_naive(const string& s) {
        int n = s.length();
        int max_len = 1;
        int start = 0;
        
        // 모든 부분 문자열 검사
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (is_palindrome_simple(s, i, j) && j - i + 1 > max_len) {
                    max_len = j - i + 1;
                    start = i;
                }
            }
        }
        
        return s.substr(start, max_len);
    }
    
    // O(n) 단순 회문 검사
    bool is_palindrome_simple(const string& s, int left, int right) {
        while (left < right) {
            if (s[left] != s[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    
    // O(n²) 중심 확장법
    string longest_palindrome_expand(const string& s) {
        int n = s.length();
        int max_len = 1;
        int start = 0;
        
        for (int i = 0; i < n; i++) {
            // 홀수 길이 회문
            int len1 = expand_around_center(s, i, i);
            // 짝수 길이 회문
            int len2 = expand_around_center(s, i, i + 1);
            
            int len = max(len1, len2);
            if (len > max_len) {
                max_len = len;
                start = i - (len - 1) / 2;
            }
        }
        
        return s.substr(start, max_len);
    }
    
private:
    int expand_around_center(const string& s, int left, int right) {
        while (left >= 0 && right < s.length() && s[left] == s[right]) {
            left--;
            right++;
        }
        return right - left - 1;
    }
};

// 사용 예제
int main() {
    cout << "=== Manacher Algorithm Example ===" << endl;
    
    string text = "bababcbabcbaccba";
    cout << "Text: " << text << endl << endl;
    
    Manacher manacher(text);
    
    // 가장 긴 회문 찾기
    string longest = manacher.longest_palindrome(text);
    cout << "Longest palindrome: \"" << longest << "\"" << endl;
    cout << "Length: " << longest.length() << endl << endl;
    
    // 모든 회문 찾기
    vector<pair<int, int>> all_palindromes = manacher.find_all_palindromes(text);
    cout << "All palindromes:" << endl;
    for (auto& p : all_palindromes) {
        int start = p.first;
        int length = p.second;
        cout << "Position " << start << ", Length " << length 
             << ": \"" << text.substr(start, length) << "\"" << endl;
    }
    
    cout << "\nTotal palindromes: " << manacher.count_palindromes() << endl;
    
    // 특정 범위 회문 검사
    cout << "\nPalindrome checks:" << endl;
    cout << "Range [1,5]: " << (manacher.is_palindrome(text, 1, 5) ? "Yes" : "No") << endl;
    cout << "Range [6,10]: " << (manacher.is_palindrome(text, 6, 10) ? "Yes" : "No") << endl;
    
    // 디버그 정보
    cout << "\n=== Debug Information ===" << endl;
    manacher.print_debug_info(text);
    
    cout << "\n=== Performance Comparison ===" << endl;
    
    string test_cases[] = {
        "a",
        "aa", 
        "aba",
        "abba",
        "racecar",
        "abcdefghijklmnop",
        "abacabad"
    };
    
    SimplePalindrome simple;
    
    cout << "Test Case              | Manacher | Naive | Expand" << endl;
    cout << "---------------------- | -------- | ----- | ------" << endl;
    
    for (const string& test : test_cases) {
        Manacher m(test);
        string result1 = m.longest_palindrome(test);
        string result2 = simple.longest_palindrome_naive(test);
        string result3 = simple.longest_palindrome_expand(test);
        
        cout << "\"" << test << "\"";
        for (int i = test.length(); i < 20; i++) cout << " ";
        cout << " | " << result1.length();
        cout << "        | " << result2.length();
        cout << "     | " << result3.length() << endl;
    }
    
    cout << "\n=== Algorithm Comparison ===" << endl;
    cout << "Algorithm     | Time Complexity | Space Complexity | Notes" << endl;
    cout << "------------- | --------------- | ---------------- | -----" << endl;
    cout << "Naive         | O(n³)          | O(1)             | Check all substrings" << endl;
    cout << "Expand Center | O(n²)          | O(1)             | Expand around each center" << endl;
    cout << "Manacher      | O(n)           | O(n)             | Linear time, optimal" << endl;
    cout << "DP            | O(n²)          | O(n²)            | Dynamic programming" << endl;
    
    return 0;
}