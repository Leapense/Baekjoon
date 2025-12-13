#include <stdio.h>

static int min_int(int a, int b) { return a < b ? a : b; }

int main(void) {
    int N;
    if (scanf("%d", &N) != 1) return 0;

    int a[1000];
    int c1 = 0, c2 = 0, c3 = 0;
    for (int i = 0; i < N; i++) {
        scanf("%d", &a[i]);
        if (a[i] == 1) c1++;
        else if (a[i] == 2) c2++;
    }

    int seg[3][4] = {0};
    int end1 = c1;
    int end2 = c1 + c2;
    for (int i = 0; i < N; i++) {
        int region;
        if (i < end1) region = 0;
        else if (i < end2) region = 1;
        else region = 2;
        seg[region][a[i]]++;
    }

    int ans = 0;
    int x12 = min_int(seg[0][2], seg[1][1]);
    ans += x12;
    seg[0][2] -= x12;
    seg[1][1] -= x12;

    int x13 = min_int(seg[0][3], seg[2][1]);
    ans += x13;
    seg[0][3] -= x13;
    seg[2][1] -= x13;

    int x23 = min_int(seg[1][3], seg[2][2]);
    ans += x23;
    seg[1][3] -= x23;
    seg[2][2] -= x23;

    int remaining = seg[0][2] + seg[0][3] + seg[1][1] + seg[1][3] + seg[2][1] + seg[2][2];

    ans += 2 * (remaining / 3);
    printf("%d\n", ans);
    return 0;
}