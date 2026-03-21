#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
    char name[26];
    int x, y;
} Landmark;

static int cmp_landmark(const void *a, const void *b) {
    const Landmark *la = (const Landmark *)a;
    const Landmark *lb = (const Landmark *)b;
    return strcmp(la->name, lb->name);
}

static Landmark *find_landmark(Landmark *arr, int n, const char *target) {
    int left = 0, right = n - 1;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        int c = strcmp(arr[mid].name, target);
        if (c == 0) return &arr[mid];
        if (c < 0) left = mid + 1;
        else right = mid - 1;
    }
    return NULL;
}

static long long llabs_diff(int a, int b) {
    long long d = (long long)a - b;
    return d < 0 ? -d : d;
}

int main(void) {
    int n;
    if (scanf("%d", &n) != 1) return 0;

    Landmark *arr = (Landmark *)malloc(sizeof(Landmark) * (size_t)n);
    if (!arr) return 0;

    for (int i = 0; i < n; ++i) {
        scanf("%25s %d %d", arr[i].name, &arr[i].x, &arr[i].y);
    }

    qsort(arr, (size_t)n, sizeof(Landmark), cmp_landmark);
    char name[26];
    scanf("%25s", name);

    Landmark *prev = find_landmark(arr, n, name);
    long long answer = 0;
    for (int i = 1; i < n; ++i) {
        scanf("%25s", name);
        Landmark *cur = find_landmark(arr, n, name);

        answer += llabs_diff(prev->x, cur->x) + llabs_diff(prev->y, cur->y);
        prev = cur;
    }

    printf("%lld", answer);
    free(arr);
    return 0;
}