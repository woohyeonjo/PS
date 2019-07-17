### DOM (Document Object Model)

- Node와 Element 
  - Node는 태그 노드와 텍스트 노드 전체를 가리킨다.
  - Element는 텍스트 노드를 제외하고 태그만 가리킨다.



- 자식
  - tag.children은 텍스트 노드 제외
  - tag.childNodes 텍스트 노드 포함



### 스코프 (Scope)

- 자바스크립트는 변수의 범위를 호출한 함수의 지역 스코프 부터 전역 변수들이 있는 전역 스코프까지 점차 넓혀가며 찾는다.
- 스코프 체인 (Scope chain)
  - 내부 함수는 외부 함수의 변수에 접근 가능하지만, 외부 함수에서는 내부 함수의 변수에 접근할 수 없다.
  - 꼬리를 물고 계속 범위를 넓히면서 찬는 관계를 스코프 체인이라고 한다.



### 렉시컬 스코핑 (Lexical scoping)

- 스코프는 함수를 호출할 때가 아니라 선언할 때 생긴다.

  - 함수를 처음 선언하는 순간, 함수 내부의 변수는 자기 스코프로부터 가장 가까운 곳에 있는 변수를 계속 참조하게 된다.

    ```js
    let name = 'zero';
    function log() {
        console.log(name); // 선언시에 전역 스코프의 name을 참조함
    }
    
    function wrapper() {
        let name = 'nero';
        //  name = 'nero'; // 전역변수를 수정하였기 때문에 결과는 nero
        log();
    }
    wrapper();
    // zero
    ```



### 네임스페이스 생성

- 전역 변수를 생성하는 대신 한 번  함수 안에 넣어 지역변수로 만든다. 또는 객체 안의 속성으로 만든다.

  ```js
  let obj = {
      x: 'local',
      y: function() {
          alert(this.x);
      }
  }
  ```

  - 위와 같다면 접근시에 `obj.x` , `obj.y()`로 접근해야하기 때문에 다른 사람과 변수명이 겹칠 일이 없다.

- 대부분의 라이브러리가 네임스페이스를 사용한다.
  - naver = jindo, facebook = FB, jquery = jQuery or $

- 하지만 위와 같다면 `obj.x = hacked`와 같이 내부 변수를 변경할 수 있다.

  - 비공개 변수를 만드는 방법

    ```js
    let newScope = (() => {
        let x = 'local';
        return {
            y: function() {
                alert(x);
            }
        };
    })();
    ```

    IIFE(즉시 호출 함수 표현식) (= 모듈 패턴)을 이용하여 `x`에는 접근할 수 없도록 한다. return하는 변수에만 접근이 가능하다.

