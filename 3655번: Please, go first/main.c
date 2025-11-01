#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <stdint.h>

int main(void) {
    int T;
    if (scanf("%d", &T) != 1) return 0;
    while (T--) {
        int n;
        char s[25005];
        scanf("%d", &n);
        scanf("%s", s);

        int cnt[128] = {0};
        bool used[128] = {0};
        for (int i = 0; i < n; ++i) cnt[(unsigned char)s[i]]++;

        long long ans = 0;
        int num_used = 0;

        for (int i = n - 1; i >= 0; --i) {
            unsigned char c = (unsigned char)s[i];
            if (!used[c]) {
                int right = (n - 1) - i;
                ans += (long long)(num_used - right) * cnt[c];
                num_used += cnt[c];
                used[c] = true;
            }
        }

        printf("%lld\n", ans * 5LL);
    }

    return 0;
}