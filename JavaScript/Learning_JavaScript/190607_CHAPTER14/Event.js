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

                /*
                setTimeout(function() {
                    if(countdown.superstitious && i === 13){
                        return reject(new Error("Oh my god"));
                    }
                    countdown.emit('tick', i);
                    if(i === 0) {
                        resolve();
                    }
                }, (countdown.seconds - i) * 1000);
                */
            }
        });
    }
}

const c = new Countdown(5, true);

c.on('tick', function(i) {
    if(i > 0) console.log(i + "...");
});
c.go()
    .then(function() {
        console.log('GO!');
    })
    .catch(function(err) {
        console.error(err.message);
    })