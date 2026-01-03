#include <stdio.h>

int main(void) {
    int B;
    if (scanf("%d", &B) != 1) return 0;
    
    for (int i = 0; i < B; i++) {
        int S, n, f, m;
        scanf("%d %d %d %d", &S, &n, &f, &m);
        
        int mn = n + m;
        int mx = n * f + m;
        
        if (mn <= S && S <= mx) puts("YES");
        else puts("NO");
    }
    
    return 0;
}