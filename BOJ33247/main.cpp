#include <bits/stdc++.h>
using namespace std;

struct FastScanner {
    static constexpr size_t BUFSIZE = 1 << 20;
    char buf[BUFSIZE];
    size_t idx = 0, size = 0;

    inline char readChar() {
        if (idx >= size) {
            size = fread(buf, 1, BUFSIZE, stdin);
            idx = 0;
            if (size == 0) return 0;
        }
        return buf[idx++];
    }

    long long readLong() {
        char c = readChar();
        while (c == ' ' || c == '\n' || c == '\r' || c == '\t') c = readChar();

        long long sign = 1;
        if (c == '-') { sign = -1; c = readChar(); }

        long long x = 0;
        while (c >= '0' && c <= '9') {
            x = x * 10 + (c - '0');
            c = readChar();
        }

        return x * sign;
    }
};

int main() {
    FastScanner fs;
    int N = (int)fs.readLong();

    long long balance = 0;
    int prev_id = 0;
    bool invalid = false;
    bool no_money = false;

    for (int k = 1; k <= N; k++) {
        int id = (int)fs.readLong();
        int parent = (int)fs.readLong();
        long long amount = fs.readLong();

        if (k == 1) {
            if (parent != 0) invalid = true;
        } else {
            if (parent != prev_id) invalid = true;
        }

        balance += amount;
        if (balance < 0) no_money = true;
        prev_id = id;
    }

    if (no_money) cout << "NO_MONEY\n";
    else if (invalid) cout << "INVALID\n";
    else cout << balance << "\n";
    return 0;
}