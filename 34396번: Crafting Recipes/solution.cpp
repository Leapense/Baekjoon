#include <bits/stdc++.h>
using namespace std;

struct BigInt {
    static constexpr uint32_t BASE = 1'000'000'000u;
    vector<uint32_t> d;
    BigInt(uint64_t x = 0) { from_u64(x); }

    void from_u64(uint64_t x) {
        d.clear();
        if (x == 0) {d.push_back(0); return;}
        while (x) {d.push_back(uint32_t(x % BASE)); x /= BASE;}
    }
    bool is_zero() const { return d.size() == 1 && d[0] == 0; }
    void trim() {
        while (d.size() > 1 && d.back() == 0) d.pop_back();
    }

    BigInt& operator+=(const BigInt& b) {
        size_t n = max(d.size(), b.d.size());
        if (d.size() < n) d.resize(n, 0);
        uint64_t carry = 0;
        for (size_t i = 0; i < n; ++i) {
            uint64_t ai = d[i];
            uint64_t bi = (i < b.d.size() ? b.d[i] : 0);
            uint64_t s = ai + bi + carry;
            d[i] = uint32_t(s % BASE);
            carry = s / BASE;
        }
        if (carry) d.push_back(uint32_t(carry));
        return *this;
    }

    BigInt& mul_small(uint32_t m) {
        if (m == 0) {d.assign(1,0); return *this;}
        if (m == 1) return *this;
        uint64_t carry = 0;
        for (size_t i = 0; i < d.size(); ++i) {
            uint64_t prod = (uint64_t)d[i] * m + carry;
            d[i] = uint32_t(prod % BASE);
            carry = prod / BASE;
        }
        if (carry) d.push_back(uint32_t(carry));
        return *this;
    }
    BigInt operator*(uint32_t m) const { BigInt r = *this; r.mul_small(m); return r; }
    string to_string() const {
        string s;
        if (d.empty()) return "0";
        s = std::to_string(d.back());
        char buf[16];
        for (int i = (int)d.size() - 2; i >= 0; --i) {
            snprintf(buf, sizeof(buf), "%09u", d[i]);
            s += buf;
        }

        return s;
    }
};

static ostream& operator<<(ostream& os, const BigInt& x) {
    return os << x.to_string();
}

struct Part {
    int id;
    int qty;
};

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N;
    if (!(cin >> N)) return 0;

    unordered_map<string, int> id;
    id.reserve(600);

    vector<string> names;
    vector<char> isRaw;
    vector<vector<Part>> parts;
    vector<BigInt> cost;
    vector<char> hasRecipe, computed;

    auto get_id = [&](const string& s) -> int {
        auto it = id.find(s);
        if (it != id.end()) return it->second;
        int newId = (int)names.size();
        id.emplace(s, newId);
        names.push_back(s);
        isRaw.push_back(0);
        parts.emplace_back();
        cost.emplace_back(0);
        hasRecipe.push_back(0);
        computed.push_back(0);
        return newId;
    };

    for (int i = 0; i < N; ++i) {
        string name;
        long long c;
        cin >> name >> c;
        int u = get_id(name);
        isRaw[u] = 1;
        cost[u] = BigInt((uint64_t)c);
        computed[u] = 1;
    }

    int M;
    cin >> M;
    for (int i = 0; i < M; ++i) {
        string comp;
        int P;
        cin >> comp >> P;
        int u = get_id(comp);
        hasRecipe[u] = 1;
        parts[u].resize(P);
        for (int j = 0; j < P; ++j) {
            string pn;
            int q;
            cin >> pn >> q;
            int v = get_id(pn);
            parts[u][j] = {v, q};
        }
    }

    function<BigInt&(int)> solve = [&](int u) -> BigInt& {
        if (computed[u]) return cost[u];
        BigInt acc(0);
        for (const auto& e : parts[u]) {
            BigInt term = solve(e.id);
            term.mul_small((uint32_t)e.qty);
            acc += term;
        }

        cost[u] = std::move(acc);
        computed[u] = 1;
        return cost[u];
    };

    int cap = get_id("Capstone");
    cout << solve(cap) << "\n";
    return 0;
}