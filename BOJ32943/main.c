#include <stdio.h>
#include <stdlib.h>

typedef struct {
    int t, s, n;
} Log;

static int cmp_log(const void *a, const void *b) {
    const Log *x = (const Log *)a;
    const Log *y = (const Log *)b;
    if (x->t < y->t) return -1;
    if (x->t > y->t) return 1;
    return 0;
}

int main(void) {
    int X, C, K;
    scanf("%d %d %d", &X, &C, &K);

    Log *logs = (Log *)malloc((size_t)K * sizeof(Log));
    int *studentSeat = (int *)calloc((size_t)X + 1, sizeof(int));
    int *seatOwner = (int *)calloc((size_t)C + 1, sizeof(int));

    for (int i = 0; i < K; ++i) {
        scanf("%d %d %d", &logs[i].t, &logs[i].s, &logs[i].n);
    }

    qsort(logs, (size_t)K, sizeof(Log), cmp_log);

    for (int i = 0; i < K; ++i) {
        int s = logs[i].s;
        int n = logs[i].n;

        if (seatOwner[s] != 0) {
            continue;
        }

        int oldSeat = studentSeat[n];
        if (oldSeat != 0) {
            seatOwner[oldSeat] = 0;
        }

        studentSeat[n] = s;
        seatOwner[s] = n;
    }

    for (int i = 1; i <= X; ++i) {
        if (studentSeat[i] != 0) {
            printf("%d %d\n", i, studentSeat[i]);
        }
    }

    free(logs);
    free(studentSeat);
    free(seatOwner);

    return 0;
}