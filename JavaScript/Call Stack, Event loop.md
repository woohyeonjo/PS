### 호출 스택

```js
function first() {
  second();
  console.log('첫 번째');
}
function second() {
  third();
  console.log('두 번째');
}
function third() {
  console.log('세 번째');
}
first();
third();

// 세 번째 -> 두 번째 -> 첫 번째 -> 세 번째
```

위 구문은 아래와 같이 호출되고 실행된다.

![undefined](https://cdn.filestackcontent.com/SuD6onjdQMJuiUmfAULQ)



- Uncaught RangeError: Maximum call stack size exceeded

  호출 스택이 가득 찼을 때 발생하는 에러

  - 브라우저는 보통 만 개 정도의 호출 스택 크기를 가진다.



### 이벤트 루프

- 자바스크립트가 '단일 스레드'기반의 언어라는 말은 ' 자바스크립트 엔진이 단일 호출 스택을 사용한다'는 관점에서만 사실이다. 실제 자바스크립트가 구동되는 환경(브라우저, Node.js 등)에서는 주로 여러 개의 스레드가 사용되며, 이러한 구동 환경이 단일 호출 스택을 사용하는 자바 스크립트 엔진과 상호 연동하기 위해 사용하는 장치가 바로 '이벤트 루프'인 것이다.
- 이벤트 루프는 호출 스택이 비워질 때마다 큐에서 콜백 함수를 꺼내와서 실행하는 역할을 해 준다.
  - 이러한 반복을 틱(tick)라고 한다.



#### 단일 호출 스택과 Run-to-Completion

- 자바스크립트의 함수가 실행되는 방식을 보통 'Run-to-Completion'이라고 한다. 하나의 함수가 실행되면 이 함수의 실행이 끝날 때까지는 다른 어떤 작업도 중간에 끼어들지 못한다는 의미다. 현재 스택에 쌓여있는 모든 함수들이 실행을 마치고 스택에서 제거되기 전까지는 다른 어떠한 함수도 실행될 수 없다.

  ```js
  function delay() {
      for (var i = 0; i < 100000; i++);
  }
  function foo() {
      delay();
      bar();
      console.log('foo!'); // (3)
  }
  function bar() {
      delay();
      console.log('bar!'); // (2)
  }
  function baz() {
      console.log('baz!'); // (4)
  }
  
  setTimeout(baz, 10); // (1)
  foo();
  
  // bar! -> foo! -> baz!
  ```



#### 테스크 큐와 이벤트 루프

- 테스크 큐는 콜백 함수들이 대기하는 큐(FIFO)형태의 배열

- 이벤트 루프는 호출 스택이 비워질 때마다 큐에서 콜백 함수를 꺼내서 호출 스택에 쌓는다.

  ```js
  function delay() {
      for (var i = 0; i < 100000; i++);
  }
  function foo() {
      delay();
      console.log('foo!');
  }
  function bar() {
      delay();
      console.log('bar!');
  }
  function baz() {
      delay();
      console.log('baz!');
  }
  
  setTimeout(foo, 10);
  setTimeout(bar, 10);
  setTimeout(baz, 10);
  
  // foo -> bar -> baz
  ```

  - setTimeout 세 번 호출, 실행 완료 후 호출 스택 비워짐
  - 10ms 후 foo, bar, baz 순서로 테스크 큐에 추가
  - 이벤트 루프가 테스크 큐의 콜백함수들을 하나씩 꺼내서 호출스택에 넣는다.
    - 호출 스택에는 하나의 함수만 올라오고 실행이 완료된 후 즉, 호출스택이 비워지면 다음 순서가 올라온다.



- 다른 비동기 함수들( `addEventListener` , `XMLHttpRequest` ...)

  ```js
  $('.btn').click(function() { // (A)
      try {
          $.getJSON('/api/members', function (res) { // (B)
              // 에러 발생 코드
          });
      } catch (e) {
          console.log('Error : ' + e.message);
      }
  });
  ```

  버튼이 클릭되어 콜백 A가 실행될 때 `$.getJSON` 함수는 브라우저의 `XMLHttpRequest` API를 통해 서버로 비동기 요청을 보낸 후에 바로 실행을 마치고 호출 스택에서 제거된다. 이후에 서버에서 응답을 받은 브라우저는 콜백 B를 태스크 큐에 추가 하고 B는 이벤트 루프에 의해 실행되어 호출 스택에 추가된다. 하지만 이때 A는 이미 호출 스택에서 비워진 상태이기 때문에 호출 스택에는 B만 존재할 뿐이다. 즉 B는 A가 실행될 때와는 전혀 다른 독립적인 컨텍스트에서 실행이 되며, 그렇기 A 내부의 try-catch 문에 영향을 받지 않는다.

  *(마찬가지 이유로 에러가 발생했을 때 브라우저의 개발자 도구에서 호출 스택을 들여다봐도 B만 덩그라니 놓여있는 것을 볼 수 있을 것이다.)*

  *(이런 이유로 Node.js의 비동기 API들은 중첩된 콜백 호출에 대한 에러 처리를 위해 '첫 번째 인수는 에러 콜백 함수' 라는 컨벤션을 따르고 있다)*

  이를 해결하기 위해서는 콜백 B의 내부에서 try-catch를 실행해야 한다. (물론, 이렇게 해도 네트워크 에러나 서버 에러는 잡을 수 없다. 이를 위해서는 에러 콜백을 따로 제공해야 한다.)


  ```js
  $('.btn').click(function() { // (A)
      $.getJSON('/api/members', function (res) { // (B)
          try {
              // 에러 발생 코드
          } catch (e) {
              console.log('Error : ' + e.message);
          }
      });
  });
  ```



- setTimeout(fn, 0)

  ```js
  $('.btn').click(function() {
      showWaitingMessage();
      longTakingProcess();
      hideWaitingMessage();
      showResult();
  });
  // waithingMessage는 보여지지 않는다.
  ```

  - `showWaitingMessage` 함수의 실행이 끝나고 렌더링 엔진이 렌더링 요청을 보내도 해당 요청은 테스크 큐에서 이미 실행중인 테스크가 끝나기를 기다리고 있다.
  - 호출 스택이 비워지는 시점에는 이미 `showResult`까지 완료된 시점이다.
  - 즉, 렌더링이 진행되는 시점은 이미 `hideWaitingMessage`로 메시지가 숨겨진 상태다.

  

  ```js
  $('.btn').click(function() {
      showWaitingMessage();
      setTimeout(function() {
          longTakingProcess();
          hideWaitingMessage();
          showResult();
      }, 0);
  });
  ```

  - 테스크 큐에는 `렌더링 요청` - `longTakingProcess` 순서로 추가된다.

  - 실행이 너무 오래 걸리는 코드를 `setTimeout`을 사용하여 적절하게 다른 태스크로 나누어 주면 전체 어플리케이션이 멈추거나 스크립트가 너무 느리다며 경고창이 뜨는 상황을 방지할 수도 있을 것이다.

  - 한가지 짚고 넘어갈 사실은 '0' 이라는 숫자가 실제로 '즉시'를 의미하지 않는다는 점이다. 브라우저는 내부적으로 타이머의 최소단위(Tick)를 정하여 관리하기 때문에 실제로는 그 최소단위만큼 지난 후에 태스크 큐에 추가되게 된다. 그리고 이 최소단위는 브라우저별로 조금씩 다른데, 예를 들어 크롬 브라우저의 경우 최소단위로 4ms 사용하기 때문에 크롬에서 `setTimeout(fn, 0)`은 `setTimeout(fn, 4)`와 동일한 의미를 갖게 될 것이다.

    이런 문제를 해결하기 위해 [`setImmediate`](https://developer.mozilla.org/en/docs/Web/API/Window/setImmediate)라는 API가 [제안](https://dvcs.w3.org/hg/webperf/raw-file/tip/specs/setImmediate/Overview.html)되었지만, 안타깝게도 표준의 반열에 오르지는 못하고 IE10 이상에만 포함되어 있다. 실제로 이 메소드는 `setTimeout` 와 같은 최소단위 지연이 없이 바로 태스크 큐에 해당 콜백을 추가한다. [EsLint](https://eslint.org/)로 유명한 N.C.Zakas도 이 메소드가 표준화 되지 않은 것에 대해 [비판하는 글](https://www.nczonline.net/blog/2013/07/09/the-case-for-setimmediate/)을 올린 적이 있다. 비슷한 효과를 위해 [postMessage](https://developer.mozilla.org/en-US/docs/Web/API/Window/postMessage) 나 [MessageChanel](https://developer.mozilla.org/en-US/docs/Web/API/MessageChannel)을 사용하기도 하는데, 관련된 내용은 `setImmediate`의 [폴리필을 구현한 라이브러리](https://github.com/YuzuJS/setImmediate) 페이지에 잘 정리되어 있다.



#### 프라미스(Promise)와 이벤트 루프

```js
setTimeout(function() { // (A)
    console.log('A');
}, 0);
Promise.resolve().then(function() { // (B)
    console.log('B');
}).then(function() { // (C)
    console.log('C');
});

// B -> C -> A
```

- 프라미스는 마이크로 태스크를 사용한다.
- 마이크로 테스크는 일반 테스크보다 더 높은 우선순위를 갖는다.
- `setTimeout()` 함수는 콜백 A를 태스크 큐에 추가하고, 프라미스의 `then()` 메소드는 콜백 B를 태스크 큐가 아닌 별도의 **마이크로 태스크 큐**에 추가한다. 위의 코드의 실행이 끝나면 태스크 이벤트 루프는 (일반)태스크 큐 대신 마이크로 태스크 큐가 비었는지 먼저 확인하고, 큐에 있는 콜백 B를 실행한다. 콜백 B가 실행되고 나면 두번째 `then()` 메소드가 콜백 C를 마이크로 태스크 큐에 추가한다. 이벤트 루프는 다시 마이크로 태스크를 확인하고, 큐에 있는 콜백 C를 실행한다. 이후에 마이크로 태스크 큐가 비었음을 확인한 다음 (일반) 태스크 큐에서 콜백 A를 꺼내와 실행한다. (이런 일련의 작업은 HTML 스펙에서 [perform a microtask checkpoint](https://html.spec.whatwg.org/multipage/webappapis.html#perform-a-microtask-checkpoint) 라는 항목에 명시되어 있다.)
- 원문 글에서는 브라우저마다 프라미스의 호출 순서가 다른 문제를 지적하고 있는데, 이유는 프라미스가 ECMAScript에 정의되어 있는 반면, 마이크로 태스크는 HTML 스펙이 정의되어 있는데, 둘의 연관관계가 명확하지 않기 때문이다. *(ECMAScript에는 ES6부터 프라미스를 위해 잡 큐(Job Queue)라는 항목이 추가되었지만, HTML 스펙의 마이크로 태크스와는 별도의 개념이다.)*하지만 최근에 Living Standard 상태인 HTML 스펙을 보면 [자바스크립트의 잡큐를 어떻게 이벤트 루프와 연동하는지](https://html.spec.whatwg.org/multipage/webappapis.html#integration-with-the-javascript-job-queue)에 대한 항목이 포함되어 있다. 또한 현재는 대부분의 브라우저에서 해당 문제가 수정되어 있는 걸 확인할 수 있다.