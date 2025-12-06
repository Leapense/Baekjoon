#include <stdio.h>
#include <stdlib.h>

long long min_dist(long long a, long long b) {
    return (a < b) ? a : b;
}

long long absolute(long long a) {
    return (a < 0) ? -a : a;
}

int compare(const void* a, const void* b) {
    long long arg1 = *(const long long*)a;
    long long arg2 = *(const long long*)b;
    if (arg1 < arg2) return -1;
    if (arg1 > arg2) return 1;
    return 0;
}

int main() {
    long long d;
    int n, m;

    if (scanf("%lld", &d) != 1) return 0;
    if (scanf("%d", &n) != 1) return 0;
    if (scanf("%d", &m) != 1) return 0;

    long long* stores = (long long*)malloc(sizeof(long long) * n);
    stores[0] = 0;

    for (int i = 1; i < n; i++) {
        scanf("%lld", &stores[i]);
    }

    qsort(stores, n, sizeof(long long), compare);

    long long total_sum = 0;

    for (int i = 0; i < m; i++) {
        long long k;
        scanf("%lld", &k);

        int left = 0;
        int right = n - 1;
        int idx = 0;

        if (k > stores[n - 1]) {
            idx = 0;
        } else {
            int ans = n;
            int l = 0, r = n - 1;
            while (l <= r) {
                int mid = l + (r - l) / 2;
                if (stores[mid] >= k) {
                    ans = mid;
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            idx = ans;
        }

        long long dist = 0;
        
        long long right_store_pos = stores[idx];
        long long left_store_pos;
        
        if (idx == 0) {
            left_store_pos = stores[n - 1];
        } else {
            left_store_pos = stores[idx - 1];
        }

        long long dist1 = absolute(right_store_pos - k);
        if (right_store_pos < k) { 
            dist1 = (d + right_store_pos) - k; 
        }

        long long dist2 = absolute(left_store_pos - k);
        if (left_store_pos > k) {
             dist2 = (d - left_store_pos) + k;
        }

        total_sum += min_dist(dist1, dist2);
    }

    printf("%lld\n", total_sum);

    free(stores);
    return 0;
}