// TODO ===(equality)와 ==(loose equality)의 차이

// ===(equality)
// 두 값이 같은 객체를 가리키거나, 같은 타입이고 값도 같다면(원시타입) 이 값을 일치한다고 합니다.

// ==(loose equality)
// 두 값이 같은 객체를 가리키거나 같은 값을 갖도록 변환할 수 있다면 두 값을 동등하다고 합니다.
// "33" == 33 (true) 문자열 33은 숫자 33으로 변환 가능하기 때문에

// NaN === NaN (false), NaN == NaN (false) 대신 isNaN을 사용

// TODO 소수점이 있는 숫자를 비교할 때는 일치가 아니라 가까운지 확인한다.
/*
    let n = 0;
    while(true) {
        n += 0.1;
        if(n === 0.3) break;
    }
    console.log(`Stopped at ${n}`);

    이 루프는 0.3에서 멈추지 않고 그 값을 살짝 피한 다음 영원히 실행됩니다.
    0.1이 더블 형식으로 정확히 나타낼 수 없는 값이기 때문입니다.
    이 루프를 세 번째 반복할 때 n의 값은 0.30000000000000004가 된다.
    따라서 Number.EPSILON을 이용해서 '느슨하게' 비교하면 된다.

    let n = 0;
    while(true){
        n += 0.1;
        if(Math.abs(n - 0.3) < Number.EPSILON) break;
    }
    console.log(`Stopped at ${n}`);
*/

// 문자열 병합
// 3 + 5 + "8" = "88"
// "3" + 5 + 8 = "358"

// 거짓과 같은 값
// undefined
// null
// false
// 0
// NaN
// '' (빈 문자열)

// 참과 같은 값
// 거짓과 같은 값을 제외한 모든 값
// valueOf()를 호출했을 때 false를 반환하는 객체
// 배열, 빈 배열
// 공백만 있는 문자열 " "
// 문자열 "false"

// 단축평가가 발생하면 두 번째 피연산자에 부수효과가 일어나지 않는다.
// const options = suppliedOptions || { name : "Default" }
// suppliedOptions가 null 이나 undefined라면 options는 기본값을 갖게 된다.
// 객체는 항상 참과 같은 값이기 때문에 객체라면 options는 suppliedOptions가 된다.

// 쉼표 연산자 ( , )
// z = (x++, y++);
// x가 증가되지만 z 값은 y가 증가된 값이다.

// TODO 비트연산자 flag 사용법 익히기

// 해체 할당 [ES6]
const obj = {b : 2, c : 3, d : 4};
const {a, b, c} = obj;
a; // undefined : obj에는 "a" 프로퍼티가 없다.
b; // 2
c; // 3
d; // ReferenceError: "d"는 정의되지 않았다.

let a, b, c;
({a, b, c} = obj);

