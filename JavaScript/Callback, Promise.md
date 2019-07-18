### 콜백 

- 오류 우선 콜백 패턴 ( error-first callback )

  프라미스를 사용하지 않으면 오류 우선 콜백은 노드 개발의 표준이나 다름없다.

  ```js
  const fs = require('fs');
  
  const fname = 'may_or_may_not_exist.txt';
  fs.readFile(fname, function(err, data) {
  	if (err) return console.error('error reading file ${fname}: ${err.message}');
  	console.log('${fname} contents: ${data}');
  })
  ```

  콜백에서 가장 먼저 하는 일은 err이 참 같은 값인지 확인하는 것이다. err 가 참 같은 값이라면 오류를 보고하고 즉시 빠져나온다. ( 에러가 null이나 undefined이면 에러가 없는 것이다. )



### 프라미스

- 프라미스 기반 비동기적 함수를 호출하면 그 함수는 Promise 인스턴스를 반환한다. 

- 프라미스는 성공(fulfilled)하거나, 실패(rejected)하는 두 가지뿐이다.

- 프라미스는 단 한 번만 일어난다. 결정된다.(settled)

  ```js
  // 프로미스 생성
  const promise1 = function(param){
    return new Promise(function(resolve,reject){
      if(param){
        resolve("바보");
      }
      else{
        reject("아닌데");
      }
    });
  }
  // 프로미스 실행
  promise1(true).then(function(result){
    console.log(result);//바보
  },function(err){
    console.log(err);//아닌데
  });
  
  // 에러 처리
  asyncThing1()
      .then(function() { return asyncThing2();})
      .then(function() { return asyncThing3();})
      .catch(function(err) { return asyncRecovery1();})
   
      .then(function() { return asyncThing4();}, function(err) { return asyncRecovery2(); })
      .catch(function(err) { console.log("Don't worry about it");})
   
      .then(function() { console.log("All done!");});
  ```

- Promise.all

  ```js
  const param = true;
  const promise1 = new Promise(function(resolve,reject){
      if(param){
        resolve("바보");
      }
      else{
        reject("아닌데");
      }
  });
  const promise2 = new Promise(function(resolve,reject){
      if(param){
        resolve("바보2");
      }
      else{
        reject("아닌데2");
      }
  });
  Promise.all([promise1,promise2]).then(function(values){
      console.log("1,2,3 모두완료",values);
  });
  ```

  

### Countdown

- 함수 전체 스코프

  ```js
  function countdown() {
      let i;
  	console.log('Countdown:');
      for(i = 5 ; i >= 0 ; --i){
  		setTimeout(function() {
  			console.log(i === 0 ? "GO!" : i);
          }, (5 - i) * 1000);
      }
  }
  // -1 -1 -1 -1 -1 -1
  ```

- 블록 내부 스코프

  ```js
  function countdown() {
  	console.log('Countdown:');
      for(let i = 5 ; i >= 0 ; --i){
  		setTimeout(function() {
  			console.log(i === 0 ? "GO!" : i);
          }, (5 - i) * 1000);
      }
  }
  
  // 5 4 3 2 1 0 GO!
  ```

- 프라미스

  ```js
  function countdown(seconds) {
      return new Promise(function(resolve, reject) {
          for( let i = seconds ; i >= 0 ; --i){
  			setTimeout(function() {
                  if( i > 0 ) console.log(i + '...');
                  else resolve(console.log("GO!"));
              }, (seconds - i) * 1000);
          }
      });
  }
  ```

  - <b>reject나 resolve는 함수를 멈추지 않는다. 다만 프라미스의 상태를 관리한다.</b>

  