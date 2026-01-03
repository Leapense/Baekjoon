#include <bits/stdc++.h>
using namespace std;

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

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    long long a, b, c;
    cin >> a >> b >> c;

    array<long long, 3> nums = {a, b, c};
    array<char, 3> ops = {'+', '-', '*'};

    long long best = LLONG_MIN;
    sort(nums.begin(), nums.end());
    do {
        for (int p = 0; p < 3; ++p) {
            for (int q = 0; q < 3; ++q) {
                if (q != p) {
                    long long val = eval3(nums[0], ops[p], nums[1], ops[q], nums[2]);
                    best = max(best, val);
                }
            }
        }
    } while (next_permutation(nums.begin(), nums.end()));

    cout << best << "\n";
    return 0;
}