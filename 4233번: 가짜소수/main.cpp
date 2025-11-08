#include <bits/stdc++.h>
using namespace std;

using u64 = unsigned long long;
using u128 = unsigned __int128;

static bool is_prime_u32(uint32_t n) {
    if (n < 2) return false;
    if (n <= 3) return true;
    if (n % 2 == 0 || n % 3 == 0) return false;

    for (uint32_t i = 5; (u64)i * i <= n; i += 6) {
        if (n % i == 0 || n % (i + 2) == 0) return false;
    }
    return true;
}

static u64 mul_mod(u64 a, u64 b, u64 mod) {
    return (u64)((u128)a * b % mod);
}

static u64 pow_mod(u64 a, u64 e, u64 mod) {
    u64 r = 1 % mod;
    a %= mod;
    while (e > 0) {
        if (e & 1) r = mul_mod(r, a, mod);
        a = mul_mod(a, a, mod);
        e >>= 1;
    }
    return r;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    uint32_t p, a;
    while (cin >> p >> a) {
        if (p == 0 && a == 0) break;
        if (is_prime_u32(p)) {
            cout << "no\n";
            continue;
        }

        u64 res = pow_mod(a, p, p);
        cout << (res == a ? "yes" : "no") << '\n';
    }
    return 0;
}
