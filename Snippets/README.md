# Algorithm & Data Structure Snippets

알고리즘 문제 해결을 위한 C++ 자료구조 및 알고리즘 구현 모음

## 📁 디렉토리 구조

### 🏗️ DataStructures/
자료구조 구현들

#### Basic/
기본적인 자료구조 (STL 사용)
- `stack.cpp` - 스택 구현
- `queue.cpp` - 큐 구현  
- `linked_list.cpp` - 연결 리스트 구현
- `binary_tree.cpp` - 이진 트리 및 순회 구현

#### Pure/
STL을 사용하지 않은 순수 C++ 구현
- `stack_pure.cpp` - 배열/링크드리스트 기반 스택
- `queue_pure.cpp` - 배열/원형큐/링크드리스트 기반 큐
- `dynamic_array_pure.cpp` - 동적 배열 (vector 유사)
- `heap_pure.cpp` - 최대힙/최소힙 구현
- `hash_table_pure.cpp` - 체이닝/오픈어드레싱 해시테이블

#### Advanced/
심화 트리 자료구조
- `red_black_tree.cpp` - 레드-블랙 트리 (STL map/set 기반)
- `avl_tree.cpp` - AVL 트리 (높이 균형 트리)
- `b_tree.cpp` - B-트리 (데이터베이스 인덱스용)
- `trie.cpp` - 트라이/접두사 트리 (문자열 검색)
- `segment_tree.cpp` - 세그먼트 트리 (구간 쿼리)
- `fenwick_tree.cpp` - 펜윅 트리/BIT (구간 합)

### 🧮 Algorithms/
알고리즘 구현들

#### Sorting/
정렬 알고리즘
- `bubble_sort.cpp` - 버블 정렬 O(n²)
- `selection_sort.cpp` - 선택 정렬 O(n²)
- `insertion_sort.cpp` - 삽입 정렬 O(n²)
- `merge_sort.cpp` - 병합 정렬 O(n log n)
- `quick_sort.cpp` - 퀵 정렬 평균 O(n log n)
- `heap_sort.cpp` - 힙 정렬 O(n log n)

#### Graph/
그래프 알고리즘
- `graph_dfs_bfs.cpp` - DFS/BFS 그래프 순회
- `dijkstra.cpp` - 다익스트라 최단경로
- `kruskal_mst.cpp` - 크루스칼 최소신장트리
- `prim_mst.cpp` - 프림 최소신장트리

#### Search/
검색 알고리즘
- `binary_search.cpp` - 이진탐색 및 변형들

#### String/
문자열 알고리즘
- `kmp.cpp` - KMP 패턴 매칭 O(n+m)
- `manacher.cpp` - 매나커 회문 탐지 O(n)
- `suffix_array.cpp` - 접미사 배열 구조

#### Other/
기타 알고리즘
- `dynamic_programming.cpp` - 동적계획법 대표 문제들

## 🚀 사용법

각 파일은 독립적으로 컴파일 및 실행 가능합니다:

```bash
# 컴파일
g++ -std=c++17 -Wall -Wextra -g DataStructures/Basic/stack.cpp -o stack

# 실행
./stack
```

VSCode 환경에서는 설정된 tasks.json을 사용하여 `Ctrl+Shift+P` → "Tasks: Run Task"로 빌드/실행 가능합니다.

## 📚 학습 가이드

### 초급자
1. **DataStructures/Basic/** - 기본 자료구조부터 시작
2. **Algorithms/Sorting/** - 간단한 정렬부터 학습
3. **Algorithms/Search/** - 이진탐색 이해

### 중급자  
1. **DataStructures/Pure/** - STL 없는 구현으로 내부 동작 이해
2. **Algorithms/Graph/** - 그래프 알고리즘 학습
3. **dynamic_programming.cpp** - DP 패턴 익히기

### 고급자
1. **DataStructures/Advanced/** - 균형 트리들 비교 학습
2. 실제 문제 해결에 적용하여 최적화 경험

## 🎯 특징

- **상세한 주석**: 각 알고리즘의 원리, 시간복잡도, 사용 사례 설명
- **실행 가능한 예제**: 모든 파일에 main() 함수와 테스트 코드 포함
- **실무 관점**: 실제 시스템에서 사용되는 사례와 선택 기준 제시
- **비교 분석**: 유사한 자료구조/알고리즘 간의 차이점 명시

## 📖 참고사항

- 모든 코드는 C++17 표준 사용
- 알고리즘 문제 해결에 최적화된 구현
- 교육 목적으로 가독성을 중시한 코드 스타일