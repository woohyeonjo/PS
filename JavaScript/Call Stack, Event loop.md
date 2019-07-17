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



