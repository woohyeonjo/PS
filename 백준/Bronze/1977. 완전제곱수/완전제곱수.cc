#include<cstdio>

int M, N, min, sum, temp;

int main() {
    scanf("%d", &M);
    scanf("%d", &N);

    min = 10000; sum = 0;

    for(int i = 1 ; i <= 100 ; ++i){
        temp = i * i;
        if(temp >= M && temp <= N) {
            if(temp < min) min = temp;
            sum += temp;
        }
    }

    if(sum == 0) printf("%d", -1);
    else {
        printf("%d\n%d", sum, min);
    }

    return 0;
}
