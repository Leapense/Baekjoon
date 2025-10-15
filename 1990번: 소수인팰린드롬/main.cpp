#include <bits/stdc++.h>
using namespace std;

bool is_prime(int n) {
    if (n < 2) return false;
    if (n == 2) return true;
    if (n % 2 == 0) return false;
    for (int i = 3; i * 1LL * i <= n; i += 2) {
        if (n % i == 0) return false;
    }
    return true;
}

int make_palindrome(int half, bool odd) {
    string s = to_string(half);
    string rev = s;
    reverse(rev.begin(), rev.end());
    if (odd) {
        rev.erase(0, 1);
    }
    return stoi(s + rev);
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int a, b;
    cin >> a >> b;

    vector<int> results;
    for (int len = 1; len <= 5; ++len) {
        int start = pow(10, len - 1);
        int end = pow(10, len);
        for (int i = start; i < end; ++i) {
            for (bool odd : {true, false}) {
                int pal = make_palindrome(i, odd);
                if (pal > b) break;
                if (pal >= a && is_prime(pal)) {
                    results.push_back(pal);
                }
            }
        }
    }

    sort(results.begin(), results.end());
    for (int x : results) cout << x << "\n";
    cout << -1 << "\n";

    return 0;
}