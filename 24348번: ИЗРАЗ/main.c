#include <stdio.h>
#include <limits.h>

static long long apply_op(long long a, char op, long long b) {
    if (op == '+') return a + b;
    if (op == '-') return a - b;
    return a * b;
}

static long long eval3(long long x, char op1, long long y, char op2, long long z) {
    if (op1 == '*') {
        long long t = apply_op(x, '*', y);
        return apply_op(t, op2, z);
    }
    if (op2 == '*') {
        long long t = apply_op(y, '*', z);
        return apply_op(x, op1, t);
    }
    long long t = apply_op(x, op1, y);
    return apply_op(t, op2, z);
}

int main(void) {
    long long a, b, c;
    if (scanf("%lld %lld %lld", &a, &b, &c) != 3) return 0;

    long long nums[3] = {a, b, c};
    char ops[3] = {'+', '-', '*'};

    long long best = LLONG_MIN;

    for (int i = 0; i < 3; ++i) {
        for (int j = 0; j < 3; ++j) {
            if (j != i) {
                for (int k = 0; k < 3; ++k) {
                    if (k != i && k != j) {
                        long long x = nums[i];
                        long long y = nums[j];
                        long long z = nums[k];

                        for (int p = 0; p < 3; ++p) {
                            for (int q = 0; q < 3; ++q) {
                                if (q != p) {
                                    long long val = eval3(x, ops[p], y, ops[q], z);
                                    if (val > best) best = val;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    printf("%lld\n", best);
    return 0;
}