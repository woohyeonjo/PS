Vuex - 상태 관리 라이브러리

>  개요
>
> - 복잡한 애플리케이션의 컴포넌트들을 효율적으로 관리하는 라이브러리
> - Flux 패턴
> - state, getters, mutations, actions
> - Helper
> - 프로젝트 구조화, 모듈 구조화



### Vuex란?

- 무수히 많은 컴포넌트의 데이터를 관리하기 위한 상태 관리 패턴이자 라이브러리
- React의 Flux 패턴에서 기인함
- Vue.js 중고급 개발자가 되기위한 필수 관문



### Flux란?

- MVC 패턴의 복잡한 데이터 흐름 문제를 해결하는 개발 패턴 - Unidirectional data flow
  - 단방향 흐름
- `Action` - `Dispatcher` - `Model` - `View` 
  - Action : 화면에서 발생하는 이벤트 또는 사용자의 입력
  - Dispatcher : 데이터를 변경하는 방법, 메서드
  - Model : 화면에 표시할 데이터
  - View : 사용자에게 비춰지는 화면



### MVC 패턴의 문제점

- 기능 추가 및 변경에 따라 생기는 문제점을 예측할 수가 없음. 예) 페이스북 채팅 화면
- 앱이 복잡해지면서 생기는 업데이트 루프



### Flux 패턴의 단방향 데이터 흐름

- 데이터의 흐름이 여러 갈래로 나뉘지 않고 단방향으로만 처리
- `Action` - `Dispatcher` - `Store` - `View` - `Action` - `Dispatcher`



### Vuex로 해결할 수 있는 문제

1. MVC 패턴에서 발생하는 구조적 오류
2. 컴포넌트 간 데이터 전달 명시
3. 여러 개의 컴포넌트에서 같은 데이터를 업데이트 할 때 동기화 문제



### Vuex 컨셉

- State : 컴포넌트 간에 공유하는 데이터 `data()`

- View : 데이터를 표시하는 화면 `template`

- Action : 사용자의 입력에 따라 데이터를 변경하는 `methods`

  ![img](https://vuex.vuejs.org/flow.png)



### Vuex 구조

![vuex](https://vuex.vuejs.org/vuex.png)

- Actions - 비동기
- Mutations - 동기



