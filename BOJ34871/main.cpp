#include <bits/stdc++.h>
using namespace std;

class FastScanner {
    private:
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
    public:
        template <typename T>
        bool nextInt(T &out) {
            char c;
            T sign = 1;
            T val = 0;

            c = readChar();
            if (!c) return false;

            while (c != '-' && (c < '0' || c > '9')) {
                c = readChar();
                if (!c) return false;
            }

            if (c == '-') {
                sign = -1;
                c = readChar();
            }

            while (c >= '0' && c <= '9') {
                val = val * 10 + (c - '0');
                c = readChar();
            }

            out = val * sign;
            return true;
        }
};

class FastOutput {
    private:
        static constexpr size_t BUFSIZE = 1 << 20;
        char buf[BUFSIZE];
        size_t idx = 0;

    public:
        ~FastOutput() { flush(); }
        inline void flush() {
            if (idx) {
                fwrite(buf, 1, idx, stdout);
                idx = 0;
            }
        }

        inline void pushChar(char c) {
            if (idx == BUFSIZE) flush();
            buf[idx++] = c;
        }

        void writeLongLong(long long x) {
            if (x == 0) {
                pushChar('0');
                pushChar('\n');
                return;
            }

            if (x < 0) {
                pushChar('-');
                x = -x;
            }

            char s[32];
            int n = 0;
            while (x > 0) {
                s[n++] = char('0' + (x % 10));
                x /= 10;
            }
            while (n--) pushChar(s[n]);
            pushChar('\n');
        }
};

int main() {
    FastScanner fs;
    FastOutput fo;

    int n, q;
    fs.nextInt(n);
    fs.nextInt(q);
    
    long long Lmax = 0;
    long long Rmin = (long long)4e18;

    for (int i = 0; i < n; ++i) {
        long long l, r, y;
        fs.nextInt(l);
        fs.nextInt(r);
        fs.nextInt(y);

        if (l > Lmax) Lmax = l;
        if (r < Rmin) Rmin = r;
    }

    for (int i = 0; i < q; ++i) {
        long long p;
        fs.nextInt(p);

        long long ans = 0;
        if (Lmax - p > ans) ans = Lmax - p;
        if (p - Rmin > ans) ans = p - Rmin;

        fo.writeLongLong(ans);
    }

    fo.flush();
    return 0;
}