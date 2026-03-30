#include <stdio.h>
#include <stdbool.h>
#include <string.h>

int main(void) {
    int N;
    scanf("%d", &N);

    bool has_rule[26] = {false};
    bool allowed[26][26] = {{false}};

    for (int i = 0; i < N; ++i) {
        char X;
        int M;
        scanf(" %c %d", &X, &M);

        int x = X - 'a';
        has_rule[x] = true;

        for (int j = 0; j < M; ++j) {
            char Y;
            scanf(" %c", &Y);
            allowed[x][Y - 'a'] = true;
        }
    }

    char S[1005];
    scanf("%s", S);

    int len = (int)strlen(S);

    for (int i = 0; i < len; ++i) {
        int x = S[i] - 'a';
        if (!has_rule[x]) continue;
        if (i == len - 1) continue;

        int y = S[i + 1] - 'a';
        if (!allowed[x][y]) {
            printf("no");
            return 0;
        }
    }

    printf("yes");
    return 0;
}