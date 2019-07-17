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
```

위 구문은 아래와 같이 호출되고 실행된다.

![undefined](https://cdn.filestackcontent.com/SuD6onjdQMJuiUmfAULQ)



- Uncaught RangeError: Maximum call stack size exceeded

  호출 스택이 가득 찼을 때 발생하는 에러

  - 브라우저는 보통 만 개 정도의 호출 스택 크기를 가진다.



### 이벤트 루프

- 자바스크립트가 '단일 스레드'기반의 언어라는 말은 ' 자바스크립트 엔진이 단일 호출 스택을 사용한다'는 관점에서만 사실이다. 실제 자바스크립트가 구동되는 환경(브라우저, Node.js 등)에서는 주로 여러 개의 스레드가 사용되며, 이러한 구동 환경이 단일 호출 스택을 사용하는 자바 스크립트 엔진과 상호 연동하기 위해 사용하는 장치가 바로 '이벤트 루프'인 것이다.
- 이벤트 루프는 호출 스택이 비워질 때마다 큐에서 콜백 함수를 꺼내와서 실행하는 역할을 해 준다.

