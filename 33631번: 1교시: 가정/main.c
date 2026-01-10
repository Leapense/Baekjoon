#include <stdio.h>

int main(void) {
    long long F, C, E, B;
    long long Fn, Cn, En, Bn;
    int Q;

    if (scanf("%lld %lld %lld %lld", &F, &C, &E, &B) != 4) return 0;
    if (scanf("%lld %lld %lld %lld", &Fn, &Cn, &En, &Bn) != 4) return 0;
    if (scanf("%d", &Q) != 1) return 0;

    long long made = 0;
    for (int qi = 0; qi < Q; qi++) {
        int type;
        long long i;
        scanf("%d %lld", &type, &i);

        if (type == 1) {
            long long needF = i * Fn;
            long long needC = i * Cn;
            long long needE = i * En;
            long long needB = i * Bn;
            if (F >= needF && C >= needC && E >= needE && B >= needB) {
                F -= needF;
                C -= needC;
                E -= needE;
                B -= needB;
                made += i;
                printf("%lld\n", made);
            } else {
                printf("Hello, siumii\n");
            }
        } else if (type == 2) {
            F += i;
            printf("%lld\n", F);
        } else if (type == 3) {
            C += i;
            printf("%lld\n", C);
        } else if (type == 4) {
            E += i;
            printf("%lld\n", E);
        } else if (type == 5) {
            B += i;
            printf("%lld\n", B);
        }
    }

    return 0;
}
