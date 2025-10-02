#include <bits/stdc++.h>
using namespace std;

constexpr long long MOD = 1'000'000'007LL;
constexpr long long INV2 = 500'000'004LL;

long long mod_pow(long long base, long long exp) {
    long long res = 1;
    while (exp) {
        if (exp & 1) res = res * base % MOD;
        base = base * base % MOD;
        exp >>= 1;
    }
    return res;
}

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    long long N;
    if (!(cin >> N)) return 0;
    
    long long p = mod_pow(26, N);
    long long q = mod_pow(22, N);
    cout << ((p + q) % MOD * INV2 % MOD) << '\n';
    return 0;
}