const EventEmitter = require('events').EventEmitter;

class Countdown extends EventEmitter {
    constructor(seconds, superstitious){
        super();
        this.seconds = seconds;
        //this.superstitious = superstitious;
        this.superstitious = !!superstitious;
    }
    go() {
        const countdown = this;

        const timeoutIds = [];

        return new Promise(function(resolve, reject) {
            for(let i = countdown.seconds ; i >= 0 ; --i){
                timeoutIds.push(setTimeout(function() {
                   if(countdown.superstitious && i === 13){
                       // 대기중인 타임아웃을 모두 취소합니다.
                       timeoutIds.forEach(clearTimeout);
                       return reject(new Error("Oh my god"));
                   }
                   countdown.emit('tick', i);
                   if(i === 0) resolve();
                }, (countdown.seconds - i) * 1000));
            }
        });
    }
}

function launch() {
    return new Promise(function(resolve, reject) {
        if(Math.random() < 0.5) return; // 로켓 문제 발생
        // 프라미스가 결정되지 않은채로 계속 진행됨
        console.log("Lift off!");
        setTimeout(function() {
            resolve("In orbit!");
        }, 2 * 1000);
    })
}

function addTimeout (fn, timeout) {
    if(timeout === undefined) timeout = 1000; // 타임아웃 기본값
    return function(...args) {
        return new Promise(function(resolve, reject) {
            const tid = setTimeout(reject, timeout, new Error("promise timed out"));
            fn(...args)
                .then(function(...args) {
                    clearTimeout(tid);
                    resolve(...args);
                })
                .catch(function(...args) {
                    clearTimeout(tid);
                    reject(...args);
                });
        });
    }
}

const c = new Countdown(5, true);

c.on('tick', function(i) {
    if(i > 0) console.log(i + "...");
});
c.go()
    .then(addTimeout(launch, 11 * 1000))
    .then(function(msg) {
        console.log(msg);
    })
    .catch(function(err) {
        console.error(err.message + "!! Houston, we have a problem....");
    })