#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int min3(int a, int b, int c) {
    int m = a;
    if (b < m) m = b;
    if (c < m) m = c;
    return m;
}

void computeZ(const char* s, int n, int* z) {
    z[0] = 0;
    int l = 0, r = 0;
    for (int i = 1; i < n; i++) {
        z[i] = (i < r) ? (r - i < z[i - l] ? r - i : z[i - l]) : 0;
        while (i + z[i] < n && s[z[i]] == s[i + z[i]]) z[i]++;
        if (i + z[i] > r) { l = i; r = i + z[i]; }
    }
}

int main(void) {
    int T;
    scanf("%d", &T);

    char* s = malloc(1000001);
    int* z = malloc(1000001 * sizeof(int));

    for (int t = 1; t <= T; t++) {
        scanf("%s", s);
        int n = strlen(s);
        computeZ(s, n, z);

        int M = 0;
        for (int i = 1; i < n; i++) {
            int cap = min3(z[i], i, (n - i) / 2);
            if (cap > M) M = cap;
        }

        int answerL = 0;
        for (int i = 1; i < n; i++) {
            if (i + z[i] == n && z[i] <= M && z[i] > answerL)
                answerL = z[i];
        }

        printf("Case %d: ", t);
        if (answerL > 0) {
            for (int i = 0; i < answerL; i++) putchar(s[i]);
            putchar('\n');
        } else printf("-1\n");
    }

    free(s);
    free(z);
    return 0;
}