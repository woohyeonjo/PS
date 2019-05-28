// TODO var와 let의 차이
var a = 10; // ES5
let b = 10; // ES6

console.log(typeof(a));
console.log(typeof(b));

// const [ES6]
const C = 10;
const D = 'CONSTANT';

console.log(typeof(C));
console.log(typeof(D));

// TODO 식별자(identifier)와 리터럴(literal)의 정의
// 식별자는 변수와 상수, 함수 이름
// 리터럴은 값을 프로그램 안에서 직접 지정한다는 의미, 값을 만드는 방법
// 숫자 리터럴 - 식별자는 숫자로 시작하지 못하기 때문에 숫자 리터럴은 따옴표가 필요없다.
// 밑줄(_) 한개 또는 두 개로 시작하는 식별자는 아주 특별한 상황, 또는 '내부'변수에서만 사용

// TODO 템플릿 문자열 [ES6]
// 템플릿 문자열은 ( ` ) 백틱(backtick)을 사용한다.
let currentTemp = 19.5;
const MESSAGE = `The current temperature is ${currentTemp}\u00b0C`;
console.log(MESSAGE);

// 심볼(symbol)은 유일한 토큰을 나타내기 위한 데이터 타입 [ES6]
const RED = Symbol("The color of a sunset!");
const ORANGE = Symbol("The color of a sunset!");
console.log(RED === ORANGE);

// 숫자로 변환
let numStr = "33.3";
let num = Number(numStr);
console.log(typeof(numStr));
console.log(typeof(num));

// 숫자로 바꿀 수 없는 문자열은 Number로 형변환시 타입은 변하나 값은 Nan이다.
num = Number(numStr + "str");
console.log(typeof(num));
console.log(num);

// parseInt 또는 parseFloat로 형변환시 변환 불가능한 부분을 빼고 변환한다.
num = parseFloat(numStr + "str");
console.log(typeof(num));
console.log(num);

// Date는 valueOf()로 변환



