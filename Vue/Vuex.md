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



### Vuex 설치하기

- Vuex는 싱글 파일 컴포넌트 체계에서 npm 방식으로 라이브러리를 설치하는 것이 좋다.

  ```
  npm install vuex --save
  ```

  - ES6와 함께 사용해야 더 많은 기능과 이점을 제공받을 수 있음

- npm audit

  - npm 모듈의 취약점을 점검해주는 기능

  

### Vuex 등록

- src/store/store.js

  ```javascript
  import Vue from 'vue'
  import Vuex from 'vuex'
  
  Vue.use(Vuex);
  // use는 vue의 plug-in 기능으로 글로벌하게 사용하겠다는 의미
  
  export const store = new Vuex.Store({
      
  });
  ```

  

- main.js

  ```js
  import {store} from './store/store'
  // 변수라서 {store}
  
  new Vue({
  	el: '#app',
  	store
  	// store: stroe 변수와 선언이 같기 때문에 축약 가능
  })
  ```



### Vuex 기술 요소

- state : 여러 컴포넌트에 공유되는 데이터 `data`
- getters : 연산된 state 값을 접근하는 속성 `computed` 
- mutations : state 값을 변경하는 이벤트 로직 메서드 `methods`
- actions : 비동기 처리 로직을 선언하는 메서드 `aysnc methods`



### state란?

- 여러 컴포넌트 간에 공유할 데이터 - <b>상태</b>

  ```js
  // Vue
  data: {
  	message: 'Hello Vue.js!'
  }
  
  // Vuex
  state: {
  	message: 'Hello Vue.js!'
  }
  ```

  ```html
  <!-- Vue -->
  <p>{{ message }}</p>
  
  <!-- Vuex -->
  <p>{{ this.$store.state.message }}</p>
  ```

  

### getters란?

- state 값을 접근하는 속성이자 `computed()`처럼 미리 연산된 값을 접근하는 속성

  ```js
  // store.js
  state: {
  	num: 10
  },
  getters: {
  	getNumber(state) {
  		return state.num;
  	},
  	doubleNumber(state) {
  		return state.num * 2;
  	}
  }
  ```

  ```html
  <p>{{ this.$store.getters.getNumber}}</p>
  <p>{{ this.$store.getters.doubleNumber }}</p>
  ```

  

