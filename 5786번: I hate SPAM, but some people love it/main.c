#include <stdio.h>
#include <string.h>

#define MAXN 20
#define MAX_ATTR_LEN 20
#define MAX_SPAM 100

#define MAX_RESULT_LEN (MAX_SPAM * (MAX_ATTR_LEN + 1) + 5)

int adj[MAXN][MAXN];
int deg_arr[MAXN];
int forwarded[MAXN];
int queue_arr[MAXN];

char result_attr[MAXN][MAX_RESULT_LEN];
char names[MAXN][MAX_ATTR_LEN + 1];

int main(void) {
    int N;
    while (scanf("%d", &N) == 1 && N != 0) {
        for (int i = 0; i < N; ++i) {
            deg_arr[i] = 0;
        }

        for (int i = 0; i < N; ++i) {
            int x;
            while (scanf("%d", &x) == 1 && x != 0) {
                adj[i][deg_arr[i]++] = x - 1;
            }
        }

        for (int i = 0; i < N; ++i) {
            result_attr[i][0] = '\0';
        }

        while (1) {
            int P;
            if (scanf("%d", &P) != 1) {
                return 0;
            }

            if (P == 0) break;

            int T1, T2;
            char A1[MAX_ATTR_LEN + 1];
            char A2[MAX_ATTR_LEN + 1];
            char A3[MAX_ATTR_LEN + 1];
            scanf("%d %d %20s %20s %20s", &T1, &T2, A1, A2, A3);

            for (int i = 0; i < N; ++i) {
                forwarded[i] = 0;
            }

            int origin = P - 1;
            forwarded[origin] = 1;
            int qh = 0, qt = 0;
            queue_arr[qt++] = origin;

            while (qh < qt) {
                int v = queue_arr[qh++];
                for (int k = 0; k < deg_arr[v]; ++k) {
                    int u = adj[v][k];
                    if (!forwarded[u]) {
                        forwarded[u] = 1;
                        queue_arr[qt++] = u;
                    }
                }
            }

            for (int i = 0; i < N; ++i) {
                int T = forwarded[i] ? deg_arr[i] : 0;
                const char* attr;
                if (T < T1) attr = A1;
                else if (T < T2) attr = A2;
                else attr = A3;
                strcat(result_attr[i], attr);
                strcat(result_attr[i], " ");
            }
        }
        for (int i = 0; i < N; ++i) {
            scanf("%20s", names[i]);
        }

        for (int i = 0; i < N; ++i) {
            printf("%s: %s\n", names[i], result_attr[i]);
        }
    }

    return 0;
}