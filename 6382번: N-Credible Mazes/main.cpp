#include <bits/stdc++.h>
using namespace std;

struct DSU {
  vector<int> p, r;
  int add_node() {
    int id = (int)p.size();
    p.push_back(id);
    r.push_back(0);
    return id;
  }
  int find(int x) {
    while (p[x] != x) {
      p[x] = p[p[x]];
      x = p[x];
    }
    return x;
  }
  void unite(int a, int b) {
    a = find(a);
    b = find(b);
    if (a == b)
      return;
    if (r[a] < r[b])
      swap(a, b);
    p[b] = a;
    if (r[a] == r[b])
      r[a]++;
  }
};

int main() {
  ios::sync_with_stdio(false);
  cin.tie(nullptr);

  int n;
  int mazeNo = 1;
  while (cin >> n) {
    if (n == 0)
      break;

    auto pack_key = [&](const vector<int> &c) -> uint64_t {
      uint64_t key = 0;
      for (int i = 0; i < n; i++) {
        key |= (uint64_t)(c[i] & 15) << (4ULL * i);
      }

      return key;
    };

    DSU dsu;
    unordered_map<uint64_t, int> id;
    id.reserve(1 << 15);

    auto get_id = [&](uint64_t key) -> int {
      auto it = id.find(key);
      if (it != id.end())
        return it->second;
      int nid = dsu.add_node();
      id.emplace(key, nid);
      return nid;
    };

    vector<int> s(n), t(n);
    for (int i = 0; i < n; i++)
      cin >> s[i];
    for (int i = 0; i < n; i++)
      cin >> t[i];

    int sid = get_id(pack_key(s));
    int tid = get_id(pack_key(t));

    while (true) {
      int first;
      cin >> first;
      if (first == -1)
        break;

      vector<int> a(2 * n);
      a[0] = first;
      for (int i = 1; i < 2 * n; i++)
        cin >> a[i];

      vector<int> u(n), v(n);
      for (int i = 0; i < n; i++)
        u[i] = a[i];
      for (int i = 0; i < n; i++)
        v[i] = a[n + i];

      int uid = get_id(pack_key(u));
      int vid = get_id(pack_key(v));

      dsu.unite(uid, vid);
    }

    bool ok = (dsu.find(sid) == dsu.find(tid));
    cout << "Maze #" << mazeNo++ << ' '
         << (ok ? "can be travelled" : "cannot be travelled") << "\n";
  }

  return 0;
}