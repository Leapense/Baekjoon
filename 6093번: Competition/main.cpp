#include <bits/stdc++.h>
using namespace std;

struct Node {
    long long t;
    int id;
    int lap;
};

static inline long long cross_time(long long ms, int p, int j) {
    long long q = j / p;
    long long r = j % p;
    long long block = 1LL * p * (p - 1) / 2;

    long long tail = r * (r - 1) / 2;
    return 1LL * j * ms + q * block + tail;
}

struct MinHeap {
    vector<Node> h;
    static inline bool lessNode(const Node &a, const Node &b) {
        if (a.t != b.t) return a.t < b.t;
        return a.id < b.id;
    }
    void reserve(size_t n) { h.reserve(n); }
    bool empty() const { return h.empty(); }

    void push(const Node& x) {
        h.push_back(x);
        int i = (int)h.size() - 1;
        while(i > 0) {
            int p = (i - 1) / 2;
            if (!lessNode(h[i], h[p])) break;
            std::swap(h[i], h[p]);
            i = p;
        }
    }

    Node pop() {
        Node ret = h[0];
        h[0] = h.back();
        h.pop_back();
        int n = (int)h.size();
        int i = 0;
        while (true) {
            int l = i * 2 + 1;
            int r = l + 1;
            int m = i;
            if (l < n && lessNode(h[l], h[m])) m = l;
            if (r < n && lessNode(h[r], h[m])) m = r;
            if (m == i) break;
            std::swap(h[i], h[m]);
            i = m;
        }
        return ret;
    }
};

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int K, N;
    cin >> K >> N;

    vector<int> ms(K), p(K);
    for (int i = 0; i < K; ++i) {
        cin >> ms[i] >> p[i];
    }

    MinHeap pq;
    pq.reserve((size_t)K);

    for (int i = 0; i < K; i++) {
        pq.push(Node{cross_time(ms[i], p[i], 1), i, 1});
    }

    long long prev = -1;
    int run = 0, best = 0;

    while (!pq.empty()) {
        Node cur = pq.pop();
        if (cur.t == prev) run++;
        else {
            best = max(best, run);
            prev = cur.t;
            run = 1;
        }

        if (cur.lap < N) {
            int nxtLap = cur.lap + 1;
            pq.push(Node{cross_time(ms[cur.id], p[cur.id], nxtLap), cur.id, nxtLap});
        }
    }

    best = max(best, run);
    cout << best << '\n';
    return 0;
}