#include <bits/stdc++.h>
using namespace std;

using ll = long long;

struct Frac {
  int p, q;
};

static ll gcdll(ll a, ll b) {
  if (a < 0)
    a = -a;
  if (b < 0)
    b = -b;
  while (b) {
    ll t = a % b;
    a = b;
    b = t;
  }
  return a;
}

int main() {
  ios::sync_with_stdio(false);
  cin.tie(nullptr);

  int n;
  while (cin >> n) {
    if (n == 0)
      break;
    int a, b;
    cin >> a >> b;

    vector<int> y(n);
    ll Y = 0;
    for (int i = 0; i < n; i++) {
      cin >> y[i];
      Y += y[i];
    }

    vector<Frac> cand;
    cand.reserve(3 * n);

    for (int yi : y) {
      for (int t = 1; t <= 3; t++) {
        int p = yi, q = t;
        int g = std::gcd(p, q);
        p /= g;
        q /= g;
        cand.push_back({p, q});
      }
    }

    auto cmpVal = [](const Frac &x, const Frac &y) {
      int lhs = x.p * y.q;
      int rhs = y.p * x.q;
      if (lhs != rhs)
        return lhs < rhs;
      if (x.p != y.p)
        return x.p < y.p;
      return x.q < y.q;
    };

    sort(cand.begin(), cand.end(), cmpVal);
    cand.erase(unique(cand.begin(), cand.end(),
                      [](const Frac &x, const Frac &y) {
                        return x.p == y.p && x.q == y.q;
                      }),
               cand.end());

    ll best_num = 0, best_den = 1;
    bool has_best = false;

    for (auto [p, q] : cand) {
      ll K = 0;
      bool ok = true;
      for (int yi : y) {
        int ki = (yi * q + p - 1) / p;
        if (ki > 3) {
          ok = false;
          break;
        }
        K += ki;
      }

      if (!ok)
        continue;

      ll num = (ll)a * (ll)p * K + (ll)q * ((ll)b * K - (ll)a * Y);
      ll den = (ll)q;
      ll g = gcdll(num, den);
      num /= g;
      den /= g;

      if (!has_best) {
        best_num = num;
        best_den = den;
        has_best = true;
      } else {
        __int128 left = (__int128)num * best_den;
        __int128 right = (__int128)best_num * den;
        if (left < right) {
          best_num = num;
          best_den = den;
        }
      }
    }

    if (best_den == 1) {
      cout << best_num << "\n";
    } else {
      cout << best_num << " / " << best_den << "\n";
    }
  }

  return 0;
}