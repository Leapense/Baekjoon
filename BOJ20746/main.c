#include <stdio.h>
#include <stdbool.h>

enum { TOTAL = 24 * 60 };

static int encode_time(int h, int m) {
    return h * 60 + m;
}

static void decode_time(int id, int *h, int *m) {
    *h = id / 60;
    *m = id % 60;
}

static bool valid_time(int h, int m) {
    return (0 <= h && h <= 23 && 0 <= m && m <= 59);
}

static int build_neighbor(int id, int pos, int delta) {
    int h, m;
    decode_time(id, &h, &m);

    int d[4];
    d[0] = h / 10;
    d[1] = h % 10;
    d[2] = m / 10;
    d[3] = m % 10;

    d[pos] = (d[pos] + delta + 10) % 10;

    int nh = d[0] * 10 + d[1];
    int nm = d[2] * 10 + d[3];

    if (!valid_time(nh, nm)) {
        return -1;
    }

    return encode_time(nh, nm);
}

int main(void) {
    int sh, sm, th, tm;

    if (scanf("%2d:%2d", &sh, &sm) != 2) {
        fprintf(stderr, "Invalid start time format.\n");
        return 1;
    }

    if (scanf("%2d:%2d", &th, &tm) != 2) {
        fprintf(stderr, "Invalid target time format.\n");
        return 1;
    }

    if (!valid_time(sh, sm) || !valid_time(th, tm)) {
        fprintf(stderr, "Time values must be in HH:MM range.\n");
        return 1;
    }

    int start = encode_time(sh, sm);
    int target = encode_time(th, tm);

    bool visited[TOTAL] = { false };
    int prev[TOTAL];
    for (int i = 0; i < TOTAL; ++i) {
        prev[i] = -1;
    }

    int queue[TOTAL];
    int head = 0;
    int tail = 0;

    visited[start] = true;
    queue[tail++] = start;
    
    while (head < tail) {
        int cur = queue[head++];

        if (cur == target) {
            break;
        }

        for (int pos = 0; pos < 4; ++pos) {
            for (int k = 0; k < 2; ++k) {
                int delta = (k == 0) ? -1 : +1;
                int nxt = build_neighbor(cur, pos, delta);

                if (nxt == -1 || visited[nxt]) {
                    continue;
                }

                if (tail >= TOTAL) {
                    fprintf(stderr, "Internal queue overflow.\n");
                    return 1;
                }

                visited[nxt] = true;
                prev[nxt] = cur;
                queue[tail++] = nxt;
            }
        }
    }

    if (!visited[target]) {
        fprintf(stderr, "No path found.\n");
        return 1;
    }

    int path[TOTAL];
    int len = 0;

    for (int cur = target; cur != -1; cur = prev[cur]) {
        if (len >= TOTAL) {
            fprintf(stderr, "Internal path overflow.\n");
            return 1;
        }
        path[len++] = cur;
    }

    printf("%d\n", len);
    for (int i = len - 1; i >= 0; --i) {
        int h, m;
        decode_time(path[i], &h, &m);
        printf("%02d:%02d\n", h, m);
    }

    return 0;
}