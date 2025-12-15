#include <bits/stdc++.h>
using namespace std;

int main() {
  ios::sync_with_stdio(false);
  cin.tie(nullptr);

  int n, m;
  cin >> n >> m;

  vector<tuple<int, int, int>> edges;
  edges.reserve(m);

  for (int i = 0; i < m; i++) {
    int a, b, w;
    cin >> a >> b >> w;
    edges.emplace_back(a, b, w);
  }

  int k;
  cin >> k;

  vector<vector<int>> adj(n + 1);
  for (auto &[a, b, w] : edges) {
    if (w >= k) {
      adj[a].push_back(b);
      adj[b].push_back(a);
    }
  }

  vector<char> vis(n + 1, 0);
  int components = 0;
  vector<int> st;
  st.reserve(n);

  for (int s = 1; s <= n; s++) {
    if (vis[s])
      continue;
    components++;

    st.clear();
    st.push_back(s);
    vis[s] = 1;

    while (!st.empty()) {
      int x = st.back();
      st.pop_back();
      for (int y : adj[x]) {
        if (!vis[y]) {
          vis[y] = 1;
          st.push_back(y);
        }
      }
    }
  }

  cout << max(0, components - 1) << "\n";
  return 0;
}