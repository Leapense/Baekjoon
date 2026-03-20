#include <stdio.h>

int main(void) {
    int N, M;
    if (scanf("%d %d", &N, &M) != 2) {
        return 0;
    }

    int cnt[10001] = {0};
    for (int i = 0; i < N; ++i) {
        for (int j = 0; j < M; ++j) {
            int x;
            scanf("%d", &x);
            cnt[x]++;
        }
    }

    if (M % 2 == 0) {
        for (int x = 1; x <= 10000; ++x) {
            if (cnt[x] % 2 != 0) {
                printf("NO");
                return 0;
            }
        }

        printf("YES");
    } else {
        int odd = 0;
        for (int x = 1; x <= 10000; ++x) {
            if (cnt[x] % 2 != 0) {
                odd++;
            }
        }

        if (odd <= N) {
            printf("YES");
        } else {
            printf("NO");
        }
    }

    return 0;
}