#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

typedef struct {
    char *name;
    int score;
} Entry;

static int read_token(char **out) {
    int c;
    do {
        c = getchar();
        if (c == EOF) return 0;
    } while (isspace(c));

    size_t cap = 16;
    size_t len = 0;
    char *s = (char *)malloc(cap);
    if (!s) exit(1);

    while (c != EOF && !isspace(c)) {
        if (len + 1 >= cap) {
            cap *= 2;
            char *tmp = (char *)realloc(s, cap);
            if (!tmp) exit(1);
            s = tmp;
        }
        s[len++] = (char)c;
        c = getchar();
    }
    s[len] = '\0';
    *out = s;
    return 1;
}

static int cmp_entry(const void *a, const void *b) {
    const Entry *x = (const Entry *)a;
    const Entry *y = (const Entry *)b;
    return strcmp(x->name, y->name);
}

int main(void) {
    int n;
    if (scanf("%d", &n) != 1) return 0;

    Entry *arr = (Entry *)malloc((size_t)n * sizeof(Entry));
    if (!arr) return 0;

    for (int i = 0; i < n; ++i) {
        char *name;
        if (!read_token(&name)) return 0;

        int score;
        if (scanf("%d", &score) != 1) return 0;

        arr[i].name = name;
        arr[i].score = score;
    }

    qsort(arr, (size_t)n, sizeof(Entry), cmp_entry);

    for (int i = 0; i < n; ) {
        int j = i;
        long long sum = 0;

        while (j < n && strcmp(arr[i].name, arr[j].name) == 0) {
            sum += arr[j].score;
            ++j;
        }

        printf("%s %lld\n", arr[i].name, sum);
        i = j;
    }

    for (int i = 0; i < n; ++i) {
        free(arr[i].name);
    }
    free(arr);
    return 0;
}