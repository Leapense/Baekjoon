#include <bits/stdc++.h>
using namespace std;

struct BigInt {
  static constexpr uint32_t BASE = 1000000000u;
  vector<uint32_t> d;

  BigInt(uint64_t x = 0) { *this = x; }
  BigInt &operator=(uint64_t x) {
    d.clear();
    if (x == 0) {
      d.push_back(0);
      return *this;
    }
    while (x > 0) {
      d.push_back(static_cast<uint32_t>(x % BASE));
      x /= BASE;
    }

    return *this;
  }

  void normalize() {
    while (d.size() > 1 && d.back() == 0)
      d.pop_back();
  }

  friend BigInt operator+(const BigInt &a, const BigInt &b) {
    BigInt r;
    r.d.assign(max(a.d.size(), b.d.size()) + 1, 0);

    uint64_t carry = 0;
    size_t n = max(a.d.size(), b.d.size());
    for (size_t i = 0; i < n; i++) {
      uint64_t sum = carry;
      if (i < a.d.size())
        sum += a.d[i];
      if (i < b.d.size())
        sum += b.d[i];
      r.d[i] = static_cast<uint32_t>(sum % BigInt::BASE);
      carry = sum / BigInt::BASE;
    }
    r.d[n] = static_cast<uint32_t>(carry);
    r.normalize();
    return r;
  }

  friend ostream &operator<<(ostream &os, const BigInt &a) {
    int i = (int)a.d.size() - 1;
    os << a.d[i];
    for (i = i - 1; i >= 0; i--) {
      os << setw(9) << setfill('0') << a.d[i];
    }

    return os;
  }
};

int main() {
  ios::sync_with_stdio(false);
  cin.tie(nullptr);

  vector<BigInt> fib(201);
  fib[0] = BigInt(0);
  fib[1] = BigInt(1);
  for (int i = 2; i <= 200; i++) {
    fib[i] = fib[i - 1] + fib[i - 2];
  }

  int N;
  while (cin >> N) {
    cout << fib[2 * N] << "\n";
  }

  return 0;
}