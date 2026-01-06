#include <stdio.h>
#include <string.h>

static int win(char a, char b) {
    return (a == 'R' && b == 'S') ||
           (a == 'S' && b == 'P') ||
           (a == 'P' && b == 'R');
}

int main(void) {
    int n;
    if (scanf("%d", &n) != 1) return 0;

    char me[10005], guile[10005];
    if (scanf("%10004s", me) != 1) return 0;
    if (scanf("%10004s", guile) != 1) return 0;

    int myWins = 0, guileWins = 0;

    for (int i = 0; i < n; i++) {
        char a = me[i], b = guile[i];
        if (a == b) continue;
        if (win(a, b)) myWins++;
        else guileWins++;
    }

    puts(myWins > guileWins ? "victory" : "defeat");
    return 0;
}