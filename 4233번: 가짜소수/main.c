#include <stdio.h>
#include <stdint.h>
#include <stdbool.h>

static bool is_prime_u32(uint32_t n) {
    if (n < 2) return false;
    if (n <= 3) return true;
    if (n % 2 == 0 || n % 3 == 0) return false;
    for (uint32_t i = 5; (uint64_t)i * i <= n; i += 6) {
        if (n % i == 0 || n % (i + 2) == 0) return false;
    }
    return true;
}

static inline uint64_t mul_mod_u64(uint64_t a, uint64_t b, uint64_t mod) {
    return (a * b) % mod;
}

static uint64_t pow_mod_u64(uint64_t a, uint64_t e, uint64_t mod) {
    uint64_t r = 1 % mod;
    a %= mod;
    while (e > 0) {
        if (e & 1) r = mul_mod_u64(r, a, mod);
        a = mul_mod_u64(a, a, mod);
        e >>= 1;
    }
    return r;
}

int main(void) {
    uint32_t p, a;
    while (scanf("%u %u", &p, &a) == 2) {
        if (p == 0 && a == 0) break;

        if (is_prime_u32(p)) {
            puts("no");
            continue;
        }
        uint64_t res = pow_mod_u64((uint64_t)a, (uint64_t)p, (uint64_t)p);
        puts(res == (uint64_t)a ? "yes" : "no");
    }
    return 0;
}
