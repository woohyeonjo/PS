#include <iostream>
#include <string>
#include <vector>
#include <unordered_map>
#include <functional>
using namespace std;

/**
 * 트라이 (Trie) - 접두사 트리
 * 문자열 집합을 저장하는 트리 자료구조
 * 각 노드는 문자 하나를 나타내며, 루트에서 리프까지의 경로가 하나의 문자열을 형성
 * 
 * 용도:
 * - 빠른 문자열 검색 및 접두사 검색
 * - 자동 완성 기능 구현
 * - 스펠 체커 및 사전 구현
 * - IP 라우팅 테이블 (네트워크 프리픽스 매칭)
 * - 문자열 압축 알고리즘
 * 
 * 특징:
 * - 해시테이블보다 메모리 효율적 (공통 접두사 공유)
 * - 이진 탐색 트리와 달리 문자열 길이에만 의존하는 시간복잡도
 * - 접두사 기반 연산에 특화 (접두사 매칭, 자동완성)
 * - 사전순 순회가 자연스럽게 가능
 * - 최악의 경우 메모리 사용량이 클 수 있음
 * 
 * 사용 시나리오:
 * - 검색 엔진의 검색어 자동완성
 * - 코드 에디터의 자동완성 기능
 * - 사전이나 단어 데이터베이스
 * - 네트워크 라우팅에서 IP 주소 매칭
 * - 문자열 집합에서 접두사 관련 쿼리가 빈번한 경우
 * 
 * 실제 사용 사례:
 * - Google 검색의 자동완성 (Autocomplete)
 * - IDE의 코드 자동완성 (IntelliSense)
 * - 모바일 키보드의 단어 예측
 * - DNS 서버의 도메인 이름 해석
 * - 라우터의 IP 라우팅 테이블
 * - 압축 알고리즘 (LZ77, LZW)
 * - 바이러스 백신의 시그니처 매칭
 * 
 * 원리:
 * - 문자열의 공통 접두사를 트리 노드로 공유
 * - 각 노드는 한 문자와 자식 노드들의 포인터 배열
 * - 단어의 끝을 표시하는 플래그로 완전한 단어 구분
 * - 깊이가 문자열 길이와 같아 시간복잡도가 문자열 길이에 비례
 * - 메모리 사용량은 저장된 문자열들의 접두사 중복도에 의존
 * 
 * 시간복잡도:
 * - 삽입: O(m) (m은 문자열 길이)
 * - 검색: O(m)
 * - 삭제: O(m)
 * - 접두사 검색: O(p) (p는 접두사 길이)
 * 
 * 공간복잡도: O(ALPHABET_SIZE * N * M) (최악의 경우)
 * 평균적으로는 공통 접두사 공유로 메모리 효율적
 */

class Trie {
private:
    static const int ALPHABET_SIZE = 26;  // 영문 소문자만 고려
    
    struct TrieNode {
        TrieNode* children[ALPHABET_SIZE];
        bool is_end_of_word;
        int word_count;  // 이 위치에서 끝나는 단어의 개수 (중복 허용 시)
        
        TrieNode() : is_end_of_word(false), word_count(0) {
            for (int i = 0; i < ALPHABET_SIZE; i++) {
                children[i] = nullptr;
            }
        }
    };
    
    TrieNode* root;
    
    // 문자를 인덱스로 변환
    int char_to_index(char c) const {
        return c - 'a';
    }
    
    // 인덱스를 문자로 변환
    char index_to_char(int index) const {
        return 'a' + index;
    }
    
    // 메모리 해제 (재귀)
    void destroy_trie(TrieNode* node) {
        if (node == nullptr) return;
        
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            destroy_trie(node->children[i]);
        }
        delete node;
    }
    
    // 모든 단어 수집 (재귀)
    void collect_all_words(TrieNode* node, string current_word, vector<string>& words) const {
        if (node == nullptr) return;
        
        if (node->is_end_of_word) {
            words.push_back(current_word);
        }
        
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            if (node->children[i] != nullptr) {
                collect_all_words(node->children[i], current_word + index_to_char(i), words);
            }
        }
    }
    
    // 접두사로 시작하는 모든 단어 수집
    void collect_words_with_prefix(TrieNode* node, string current_word, vector<string>& words) const {
        if (node == nullptr) return;
        
        if (node->is_end_of_word) {
            words.push_back(current_word);
        }
        
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            if (node->children[i] != nullptr) {
                collect_words_with_prefix(node->children[i], current_word + index_to_char(i), words);
            }
        }
    }
    
    // 노드가 자식을 가지고 있는지 확인
    bool has_children(TrieNode* node) const {
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            if (node->children[i] != nullptr) {
                return true;
            }
        }
        return false;
    }
    
    // 삭제 헬퍼 함수 (재귀)
    bool delete_helper(TrieNode* node, const string& word, int index) {
        if (index == word.length()) {
            // 단어의 끝에 도달
            if (!node->is_end_of_word) {
                return false;  // 단어가 존재하지 않음
            }
            
            node->is_end_of_word = false;
            node->word_count = 0;
            
            // 자식이 없다면 이 노드를 삭제할 수 있음
            return !has_children(node);
        }
        
        int char_index = char_to_index(word[index]);
        TrieNode* child = node->children[char_index];
        
        if (child == nullptr) {
            return false;  // 단어가 존재하지 않음
        }
        
        bool should_delete_child = delete_helper(child, word, index + 1);
        
        if (should_delete_child) {
            delete child;
            node->children[char_index] = nullptr;
            
            // 현재 노드가 단어의 끝이 아니고 자식이 없다면 삭제 가능
            return !node->is_end_of_word && !has_children(node);
        }
        
        return false;
    }
    
    // 트리 구조 출력 (재귀)
    void print_trie_structure(TrieNode* node, string prefix, int depth) const {
        if (node == nullptr) return;
        
        if (node->is_end_of_word) {
            for (int i = 0; i < depth; i++) cout << "  ";
            cout << prefix << " (END)" << endl;
        }
        
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            if (node->children[i] != nullptr) {
                for (int j = 0; j < depth; j++) cout << "  ";
                cout << index_to_char(i) << endl;
                print_trie_structure(node->children[i], prefix + index_to_char(i), depth + 1);
            }
        }
    }
    
public:
    // 생성자
    Trie() {
        root = new TrieNode();
    }
    
    // 소멸자
    ~Trie() {
        destroy_trie(root);
    }
    
    // 단어 삽입
    void insert(const string& word) {
        if (word.empty()) return;
        
        TrieNode* current = root;
        
        for (char c : word) {
            if (c < 'a' || c > 'z') {
                cout << "Warning: Only lowercase letters are supported. Skipping '" << c << "'" << endl;
                continue;
            }
            
            int index = char_to_index(c);
            
            if (current->children[index] == nullptr) {
                current->children[index] = new TrieNode();
            }
            
            current = current->children[index];
        }
        
        current->is_end_of_word = true;
        current->word_count++;
    }
    
    // 단어 검색
    bool search(const string& word) const {
        if (word.empty()) return false;
        
        TrieNode* current = root;
        
        for (char c : word) {
            if (c < 'a' || c > 'z') {
                return false;
            }
            
            int index = char_to_index(c);
            
            if (current->children[index] == nullptr) {
                return false;
            }
            
            current = current->children[index];
        }
        
        return current->is_end_of_word;
    }
    
    // 접두사가 존재하는지 확인
    bool starts_with(const string& prefix) const {
        if (prefix.empty()) return true;
        
        TrieNode* current = root;
        
        for (char c : prefix) {
            if (c < 'a' || c > 'z') {
                return false;
            }
            
            int index = char_to_index(c);
            
            if (current->children[index] == nullptr) {
                return false;
            }
            
            current = current->children[index];
        }
        
        return true;
    }
    
    // 단어 삭제
    bool remove(const string& word) {
        if (word.empty()) return false;
        
        return delete_helper(root, word, 0);
    }
    
    // 모든 단어 반환
    vector<string> get_all_words() const {
        vector<string> words;
        collect_all_words(root, "", words);
        return words;
    }
    
    // 특정 접두사로 시작하는 모든 단어 반환 (자동 완성)
    vector<string> get_words_with_prefix(const string& prefix) const {
        vector<string> words;
        
        if (prefix.empty()) {
            return get_all_words();
        }
        
        TrieNode* current = root;
        
        // 접두사까지 이동
        for (char c : prefix) {
            if (c < 'a' || c > 'z') {
                return words;  // 빈 벡터 반환
            }
            
            int index = char_to_index(c);
            
            if (current->children[index] == nullptr) {
                return words;  // 접두사가 존재하지 않음
            }
            
            current = current->children[index];
        }
        
        // 접두사로 시작하는 모든 단어 수집
        collect_words_with_prefix(current, prefix, words);
        return words;
    }
    
    // 트라이가 비어있는지 확인
    bool empty() const {
        return !has_children(root);
    }
    
    // 저장된 단어 개수 (중복 제거)
    int size() const {
        return get_all_words().size();
    }
    
    // 트라이 구조 출력
    void print_structure() const {
        cout << "Trie structure:" << endl;
        if (empty()) {
            cout << "Empty trie" << endl;
        } else {
            print_trie_structure(root, "", 0);
        }
        cout << endl;
    }
    
    // 통계 정보 출력
    void print_stats() const {
        vector<string> words = get_all_words();
        cout << "Trie Statistics:" << endl;
        cout << "- Total words: " << words.size() << endl;
        if (!words.empty()) {
            int total_length = 0;
            int max_length = 0;
            int min_length = words[0].length();
            
            for (const string& word : words) {
                total_length += word.length();
                max_length = max(max_length, static_cast<int>(word.length()));
                min_length = min(min_length, static_cast<int>(word.length()));
            }
            
            cout << "- Average word length: " << static_cast<double>(total_length) / words.size() << endl;
            cout << "- Longest word length: " << max_length << endl;
            cout << "- Shortest word length: " << min_length << endl;
        }
    }
};

/**
 * 확장된 트라이 - 임의의 문자 지원
 */
class ExtendedTrie {
private:
    struct TrieNode {
        unordered_map<char, TrieNode*> children;
        bool is_end_of_word;
        string stored_word;  // 실제 저장된 단어 (대소문자 보존용)
        
        TrieNode() : is_end_of_word(false) {}
    };
    
    TrieNode* root;
    
    void destroy_trie(TrieNode* node) {
        if (node == nullptr) return;
        
        for (auto& pair : node->children) {
            destroy_trie(pair.second);
        }
        delete node;
    }
    
public:
    ExtendedTrie() {
        root = new TrieNode();
    }
    
    ~ExtendedTrie() {
        destroy_trie(root);
    }
    
    void insert(const string& word) {
        if (word.empty()) return;
        
        TrieNode* current = root;
        
        for (char c : word) {
            char lower_c = tolower(c);  // 대소문자 무시하고 저장
            
            if (current->children.find(lower_c) == current->children.end()) {
                current->children[lower_c] = new TrieNode();
            }
            
            current = current->children[lower_c];
        }
        
        current->is_end_of_word = true;
        current->stored_word = word;  // 원본 단어 저장
    }
    
    bool search(const string& word) const {
        if (word.empty()) return false;
        
        TrieNode* current = root;
        
        for (char c : word) {
            char lower_c = tolower(c);
            
            if (current->children.find(lower_c) == current->children.end()) {
                return false;
            }
            
            current = current->children[lower_c];
        }
        
        return current->is_end_of_word;
    }
    
    vector<string> get_words_with_prefix(const string& prefix) const {
        vector<string> words;
        
        TrieNode* current = root;
        
        // 접두사까지 이동
        for (char c : prefix) {
            char lower_c = tolower(c);
            
            if (current->children.find(lower_c) == current->children.end()) {
                return words;
            }
            
            current = current->children[lower_c];
        }
        
        // DFS로 모든 단어 수집
        function<void(TrieNode*)> dfs = [&](TrieNode* node) {
            if (node == nullptr) return;
            
            if (node->is_end_of_word) {
                words.push_back(node->stored_word);
            }
            
            for (auto& pair : node->children) {
                dfs(pair.second);
            }
        };
        
        dfs(current);
        return words;
    }
};

// 사용 예제
int main() {
    cout << "=== Basic Trie Example ===" << endl;
    
    Trie trie;
    
    // 단어 삽입
    vector<string> words = {"cat", "car", "card", "care", "careful", "cats", "dog", "dodge", "door"};
    cout << "Inserting words: ";
    for (const string& word : words) {
        cout << word << " ";
        trie.insert(word);
    }
    cout << endl << endl;
    
    // 트라이 구조 출력
    trie.print_structure();
    
    // 검색 테스트
    cout << "Search tests:" << endl;
    vector<string> search_words = {"car", "card", "care", "cat", "dog", "do", "doors"};
    for (const string& word : search_words) {
        cout << "Search '" << word << "': " << (trie.search(word) ? "Found" : "Not found") << endl;
    }
    cout << endl;
    
    // 접두사 테스트
    cout << "Prefix tests:" << endl;
    vector<string> prefixes = {"ca", "car", "do", "z"};
    for (const string& prefix : prefixes) {
        cout << "Starts with '" << prefix << "': " << (trie.starts_with(prefix) ? "Yes" : "No") << endl;
    }
    cout << endl;
    
    // 자동 완성 테스트
    cout << "Auto-completion tests:" << endl;
    for (const string& prefix : prefixes) {
        vector<string> completions = trie.get_words_with_prefix(prefix);
        cout << "Words starting with '" << prefix << "': ";
        for (const string& word : completions) {
            cout << word << " ";
        }
        cout << endl;
    }
    cout << endl;
    
    // 모든 단어 출력
    cout << "All words in trie: ";
    vector<string> all_words = trie.get_all_words();
    for (const string& word : all_words) {
        cout << word << " ";
    }
    cout << endl;
    
    // 통계 정보
    trie.print_stats();
    
    // 삭제 테스트
    cout << "\nDeleting 'car' and 'cats'..." << endl;
    trie.remove("car");
    trie.remove("cats");
    
    cout << "Words after deletion: ";
    all_words = trie.get_all_words();
    for (const string& word : all_words) {
        cout << word << " ";
    }
    cout << endl;
    
    cout << "\nAuto-completion for 'ca' after deletion: ";
    vector<string> ca_words = trie.get_words_with_prefix("ca");
    for (const string& word : ca_words) {
        cout << word << " ";
    }
    cout << endl;
    
    return 0;
}