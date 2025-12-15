#include <stdint.h>
#include <stdio.h>

#define BASE 1000000000u
#define MAXLIMBS 64

typedef struct {
  int len;
  uint32_t d[MAXLIMBS];
} BigInt;

static BigInt big_from_u32(uint32_t x) {
  BigInt a;
  if (x == 0) {
    a.len = 1;
    a.d[0] = 0;
    return a;
  }

  a.len = 0;
  while (x > 0) {
    a.d[a.len++] = x % BASE;
    x /= BASE;
  }

  return a;
}

static BigInt big_add(const BigInt *a, const BigInt *b) {
  BigInt r;
  uint64_t carry = 0;
  int n = (a->len > b->len) ? a->len : b->len;

  for (int i = 0; i < n; i++) {
    uint64_t sum = carry;
    if (i < a->len)
      sum += a->d[i];
    if (i < b->len)
      sum += b->d[i];
    r.d[i] = (uint32_t)(sum % BASE);
    carry = sum / BASE;
  }
  r.len = n;
  if (carry)
    r.d[r.len++] = (uint32_t)carry;

  while (r.len > 1 && r.d[r.len - 1] == 0)
    r.len--;
  return r;
}

static void big_print(const BigInt *a) {
  int i = a->len - 1;
  printf("%u", a->d[i]);
  for (i = i - 1; i >= 0; i--) {
    printf("%09u", a->d[i]);
  }
}

int main(void) {
  BigInt fib[201];
  fib[0] = big_from_u32(0);
  fib[1] = big_from_u32(1);
  for (int i = 2; i <= 200; i++) {
    fib[i] = big_add(&fib[i - 1], &fib[i - 2]);
  }

  int n;
  while (scanf("%d", &n) == 1) {
    int idx = 2 * n;
    big_print(&fib[idx]);
    putchar('\n');
  }

  return 0;
}