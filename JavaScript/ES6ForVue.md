## ES6 란?

- ECMAScript 2015와 동일한 용어
- 2015년은 ES5(2009년)이래로 진행한 첫 메이저 업데이트가 승인된 해
- 최신 Front-End Framework인 React, Angular, Vue에서 권고하는 언어 형식
- ES5에 비해 문법이 간결해져서 익숙해지면 코딩을 훨씬 편하게 할 수 있음



## Babel

- 구 버전 브라우저 중에서는 ES6의 기능을 지원하지 않는 브라우저가 있으므로 transpiling이 필요
- ES6의 문법을 각 브라우저의 호환 가능한 ES5로 변환하는 컴파일러



## 개요 

- const  & let
- Arrow Function
- Enhanced Object Literals
- Modules
- etc...



### ES5의 특징

1. 변수의 Scope

   - 기존 자바스크립트(ES5)는 `{ }`에 상관없이 Scope가 설정됨

     ```javascript
     var sum = 0;
     for (var i = 1 ; i <= 5 ; ++i){
     	sum = sum + i;
     };
     console.log(sum); // 15
     console.log(i); // 6
     // for문 밖에서도 i에 대한 접근이 가능함 (`i` 선언시에 전역변수로 선언됨)
     ```

2. Hoisting

   - Hoisting 이란 선언한 함수와 변수를 해석기가 가장 상단에 있는 것처럼 인식한다.

   - JS 해석기는 코드의 라인 순서와 관계 없이 함수선언식과 변수를 위한 메모리 공간을 먼저 확보한다.

   - 따라서, `function a()`와 `var`는 코드의 최상단으로 끌어 올려진 것(hoisted) 처럼 보인다.

     ``` javascript
     function willBeOveridden() {
         return 10;
     };
     willBeOveridden(); // 5
     function willBeOveridden() {
         return 5;
     };
     ```

     ```javascript
     function sum() {
     	// function statement
         // 선언식만 메모리 공간이 먼저 확보된다.
     	return 10 + 20;
     };
     
     var sum = function() {
     	// function expression
     	return 10 + 20;
     };
     ```

   - 예제

     ```javascript
     var sum = 5;
     sum = sum + i;
     
     function sumAllNumbers() {
     	// ...
     };
     
     var i = 10;
     
     //// Hoisting ////
     
     var sum;
     function sumAllNumbers() {
     	// ...
     };
     var i;
     
     sum = 5;
     sum = sum + i;
     i = 10;
     ```

     

### const & let - 새로운 변수 선언 방식

- 블록 단위 `{}` 로 변수의 범위가 제한되었음

- `const` : 한번 선언한 값에 대해서 변경할 수 없음 (상수 개념)

- `let` : 한번 선언한 값에 대해서 다시 선언할 수 없음

  ```javascript
  let sum = 0;
  for (let i = 1 ; i <= 5; ++i){
  	sum = sum + i;
  };
  console.log(sum); // 10
  console.log(i); // Uncaught ReferenceError: i is not defined
  ```

  ```javascript
  const a = 10;
  a = 20; // Uncaught TypeError: Assignment to constant variable
  
  // 하지만, 객체나 배열의 내부는 변경할 수 있다.
  
  const a = {};
  a.num = 10;
  console.log(a); // { num: 10 }
  
  const a = [];
  a.push(20);
  console.log(a); // [20]
  ```

  ```javascript
  function f() {
  	{
  		let x;
  		{
  			// 새로운 블록안에 새로운 x의 Scope가 생김
              const x = "sneaky";
              x = "foo"; // 이미 const로 x를 선언했으므로 다시 값을 대입하면 에러
  		}
          // 이전 블록 범위로 돌아왔기 때문에 'let x'에 해당하는 메모리에 값을 대입
          x = "bar";
          let x = "inner"; // Uncaught SyntaxError: Identifier 'x' has 	
          				 // already been declared
  	}
  }
  ```

  

