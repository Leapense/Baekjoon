#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void reverse_string(char *str, int length) {
    int start = 0;
    int end = length - 1;
    while (start < end) {
        char temp = str[start];
        str[start] = str[end];
        str[end] = temp;
        start++;
        end--;
    }
}

void solve() {
    long long a, b;
    if (scanf("%lld/%lld", &a, &b) != 2) {
        return;
    }

    char path[10001];
    int path_len = 0;

    while (a != 1 || b != 1) {
        if (a < b) {
            path[path_len++] = 'L';
            b = b - a;
            long long temp = a;
            a = b;
            b = temp;
        } else {
            path[path_len++] = 'R';
            a = a - b;
            long long temp = a;
            a = b;
            b = temp;
        }
    }
    path[path_len] = '\0';
    reverse_string(path, path_len);
    printf("%s\n", path);
}

int main() {
    solve();
    return 0;
}