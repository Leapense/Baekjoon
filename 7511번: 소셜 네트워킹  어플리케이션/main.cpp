#include <bits/stdc++.h>
using namespace std;

class FastInput {
    static constexpr size_t BUFSIZE = 1 << 20;
    unsigned char buf[BUFSIZE];
    size_t idx = 0, size = 0;

    inline unsigned char readByte() {
        if (idx >= size) {
            size = fread(buf, 1, BUFSIZE, stdin);
            idx = 0;
            if (size == 0) return 0;
        }
        return buf[idx++];
    }

    public:
    template <class T>
    bool readInt(T &out) {
        unsigned char c;
        do {
            c = readByte();
            if (!c) return false;
        } while (c <= ' ');

        bool neg = false;
        if (c == '-') {
            neg = true;
            c = readByte();
        }
        T val = 0;
        while (c > ' ') {
            val = val * 10 + (c - '0');
            c = readByte();
        }
        out = neg ? -val : val;
        return true;
    }
};

struct DSU {
    vector<int> p;
    vector<unsigned char> r;

    explicit DSU(int n = 0) { init(n); }
    void init(int n) {
        p.resize(n);
        r.assign(n, 0);
        iota(p.begin(), p.end(), 0);
    }

    int find(int x) {
        while (p[x] != x) {
            p[x] = p[p[x]];
            x = p[x];
        }
        return x;
    }

    void unite(int a, int b) {
        a = find(a); b = find(b);
        if (a == b) return;
        if (r[a] < r[b]) swap(a, b);
        p[b] = a;
        if (r[a] == r[b]) r[a]++;
    }
};

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    FastInput in;
    int T;
    if (!in.readInt(T)) return 0;

    string out;
    out.reserve(1 << 22);

    for (int tc = 1; tc <= T; tc++) {
        int n;
        in.readInt(n);
        DSU dsu(n);

        int k;
        in.readInt(k);
        for (int i = 0; i < k; i++) {
            int a, b;
            in.readInt(a);
            in.readInt(b);
            dsu.unite(a, b);
        }

        int m;
        in.readInt(m);

        out += "Scenario ";
        out += to_string(tc);
        out += ":\n";

        for (int i = 0; i < m; i++) {
            int u, v;
            in.readInt(u);
            in.readInt(v);
            out += (dsu.find(u) == dsu.find(v)) ? "1\n" : "0\n";
        }
        out += "\n";
    }
    cout << out;
    return 0;
}