#include <stdio.h>

int main(void) {
    int N, W;
    if (scanf("%d", &N) != 1) return 0;
    if (scanf("%d", &W) != 1) return 0;
    
    int score = N * 10;
    
    if (N >= 3) score += 20;
    if (N == 5) score += 50;
    if (W > 1000) score -= 15;
    
    if (score < 0) score = 0;
    
    printf("%d\n", score);
    return 0;
}