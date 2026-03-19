#include <stdio.h>
#include <string.h>

int main() {
    int t;
    scanf("%d", &t);

    while (t--) {
        int n;
        char s[200005];

        scanf("%d", &n);
        scanf("%s", s);

        if (s[0] == 'R' || s[n - 1] == 'L') {
            printf("YES\n");
        } else {
            printf("NO\n");
        }
    }

    return 0;
}