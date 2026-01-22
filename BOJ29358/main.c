#include <stdio.h>

int main(void) {
    int n;
    if (scanf("%d", &n) != 1) return 0;

    int a[3] = {0, 0, 0};
    for (int i = 0; i < n; i++) scanf("%d", &a[i]);
    
    int b[3] = {0, 0, 0}, c[3] = {0, 0, 0};
    for (int i = 0; i < n; i++) scanf("%d", &b[i]);
    for (int i = 0; i < n; i++) scanf("%d", &c[i]);

    int ans = 0;
    for (int i = 0; i < n; i++) {
        int L = b[i] < c[i] ? b[i] : c[i];
        int R = b[i] < c[i] ? c[i] : b[i];

        if (L > 0) ans++;
        if (R < a[i]) ans++;
    }

    printf("%d\n", ans);
    return 0;
}