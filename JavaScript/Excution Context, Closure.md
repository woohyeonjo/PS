### 실행 컨텍스트

```js
let name = 'zero'; // (1)변수 선언 (6)변수 대입
function wow(word) { // (2)변수 선언 (3)변수 대입
  console.log(word + ' ' + name); // (11)
}
function say () { // (4)변수 선언 (5)변수 대입
  let name = 'nero'; // (8)
  console.log(name); // (9)
  wow('hello'); // (10)
}
say(); // (7)
```

1. 처음 코드를 실행하는 순간 <b>전역 컨텍스트</b>가 생성
   - 모든 것을 관리하는 환경으로 페이지가 종료될 때까지 유지된다.

2. 함수를 호출할 때마다 <b>함수 컨텍스트</b>가 하나씩 더 생긴다.
   - 컨텍스트 생성 시 컨텍스트 안에 변수객체(arguments, variable), scope chain, this가 생성된다.
   - 컨텍스트 생성 후 함수가 실행, 변수들은 객체 안에서 스코프 체이닝을 수행한다.
   - 함수 실행이 마무리되면 해당 컨텍스트는 사라진다. (클로저 제외)
   - 페이지가 종료되면 전역 컨텍스트가 사라진다.



### 전역 컨텍스트

```js
'전역 컨텍스트': {
  변수객체: {
    arguments: null,
    variable: ['name', 'wow', 'say'],
  },
  scopeChain: ['전역 변수객체'],
  this: window,
}
```

```js
variable: [{ name: 'zero'}, { wow: Function}, { say: Function }]
```

- 전역 컨텍스트는 arguments(함수의 인자)가 없다. variable은 해당 스코프의 변수
- scope chain은 자기 자신
- this는 따로 설정되어 있지 않다면 window
  - new 호출 또는 다른 this 값을 bind하면 바뀐다.



### 함수 컨텍스트

```js
'say 컨텍스트': {
  변수객체: {
    arguments: null,
    variable: ['name'], // 초기화 후 [{ name: 'nero' }]가 됨
  },
  scopeChain: ['say 변수객체', '전역 변수객체'],
  this: window,
}
```

- `say()`를 하는 순간 say 함수 컨텍스트가 생성된다.
- say 컨텍스트 안에서 wow 변수를 찾을 수 없다. scope chain을 따라 올라가 전역 변수객체의 variable에서 wow 함수를 찾아 호출한다.
- wow 함수가 호출되는 순간 wow 함수 컨텍스트가 생성된다.

```js
'wow 컨텍스트': {
  변수객체: {
    arguments: [{ word : 'hello' }],
    variable: null,
  },
  scopeChain: ['wow 변수객체', '전역 변수객체'],
  this: window,
}
```



### 클로저

```js
let makeClosure = function() {
  let name = 'zero'; // 비공개 변수
  return function () {
    console.log(name);
  }
};
let closure = makeClosure(); // function () { console.log(name); }
closure(); // 'zero';
```

- 비공개 변수를 가질 수 있는 환경에 있는 함수가 클로저다.
- 비공개 변수는 클로저 함수 내부에 생성한 변수도 아니고, 매개변수도 아닌 변수를 의미한다.
- <b>클로저를 말할 때는 스코프, 컨텍스트, 비공개 변수와 함수의 관계를 항상 같이 말해야 한다.</b>



```js
// closure = makeClosure()할 때의 상황 function을 return하는데
// 그 function 선언 시의 scope chain은 lexical scoping을 따른다.

"전역 컨텍스트": {
  변수객체: {
    arguments: null,
    variable: [{ makeClosure: Function }, 'closure'],
  },
  scopeChain: ['전역 변수객체'],
  this: window,
}
"makeClosure 컨텍스트": {
  변수객체: {
    arguments: null,
    variable: [{ name: 'zero' }],
  },
  scopeChain: ['makeClosure 변수객체', '전역 변수객체'],
  this: window,
}
```

```js
"closure 컨텍스트":  {
  변수객체: {
    arguments: null,
    variable: null,
  scopeChain: ['closure 변수객체', 'makeClosure 변수객체', '전역 변수객체'],
  this: window,
}
```

- 



- <b>클로저의 단점</b>

  성능 문제와 메모리 문제를 야기한다. closure의 비공개 변수는 자바스크립트에서 언제 메모리 관리를 해야할 지 모르기 때문에 자칫 메모리 낭비로 이어질 수 있다. 프로그램을 만들면서 메모리 문제가 발생한다면 클로저를 의심해야한다. 또한 scope chain을 거슬러 올라가기 때문에 느리다.

  

### Counter 예제

```js
var counter = function() {
  var count = 0;
  function changeCount(number) {
    count += number;
  }
  return {
    increase: function() {
      changeCount(1);
    },
    decrease: function() {
      changeCount(-1);
    },
    show: function() {
      alert(count);
    }
  }
};
var counterClosure = counter();
counterClosure.increase();
counterClosure.show(); // 1
counterClosure.decrease();
counterClosure.show(); // 0
```

- counter 함수는 호출 시 return을 통해 counterClosure 컨텍스트에 비공개 변수인 count에 접근할 수 있는 scope chain을 반환한다. 따라서 counterClosure를 통해 계속해서 count로 접근할 수 있다.
- 즉, return 안에 들어 있는 함수들은 count 변수나, changeCount 함수 또는 그것들을 포함하고 있는 스코프에 대한 클로저라고 부를 수 있다.