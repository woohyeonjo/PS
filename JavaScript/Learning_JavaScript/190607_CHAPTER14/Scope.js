function scope() {
    //let i;

    // countdown을 호출하면 변수 i가 들어있는 클로저가 만들어집니다.
    // for 루프 안에서 만드는 콜백은 모두 i에 접근할 수 있고, 그들이 접근하는 i는 똑같은 i입니다.

    console.log("Countdown : ");
    for(let i = 5 ; i >= 0 ; --i){

        // 콜백이 어느 스코프에서 선언됐느냐가 중요하다.
        // 콜백은 자신을 선언한 스코프(클로저)에 있는 것에 접근할 수 있다.

        setTimeout(function(){
            console.log(i === 0 ? "GO!" : i);
        }, (5 - i) * 1000);
    }
}
scope();

