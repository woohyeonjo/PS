function countdown(seconds){
    return new Promise(function(resolve, reject) {
        for(let i = seconds ; i >= 0 ; --i){
            setTimeout(function() {
                if(i === 13) return reject(new Error("Oh my god"));
                if(i > 0) console.log(i + '...');
                else resolve(console.log("GO!"));
            }, (seconds - i) * 1000);
        }
    });
}
// 성공(resolve) 또는 실패(reject)가 일어나도 함수는 계속된다.

/*
scope(5).then(
    function() {
        console.log("scope completed successfully");
    },
    function(err){
        console.log("scope experienced an error: " + err.message);
    }
);
*/
const p = countdown(13);
p.then(function() {
    console.log("scope completed successfully");
});
p.catch(function(err) {
    console.log("scope experienced an error: " + err.message);
});
