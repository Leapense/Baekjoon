#include <stdio.h>

int main(void) {
    int N;
    scanf("%d", &N);

    long long sum = 0;
    long long minPrefix = 0;

    for (int i = 0; i < N; ++i) {
        char type;
        long long A;
        scanf(" %c %lld", &type, &A);

        if (type == 'T') sum -= A;
        else sum += A;

        if (sum < minPrefix) minPrefix = sum;
    }

    printf("%lld\n", -minPrefix);
    return 0;
}
