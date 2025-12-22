#include <bits/stdc++.h>
using namespace std;

int main() {
  ios::sync_with_stdio(false);
  cin.tie(nullptr);

  int L;
  cin >> L;
  while (L--) {
    int n;
    cin >> n;

    if (n == 0) {
      cout << 0 << "\n";
      continue;
    }

    vector<string> names(n);
    for (int i = 0; i < n; i++)
      cin >> names[i];

    string root_name = names.back();
    unordered_map<string, int> id;
    id.reserve((size_t)n * 2);

    int nextId = 0;
    auto getId = [&](const string &s) -> int {
      auto it = id.find(s);
      if (it != id.end())
        return it->second;
      int v = nextId++;
      id.emplace(s, v);
      return v;
    };

    int root = getId(root_name);

    vector<int> node_stack;
    vector<int> max_stack;
    vector<int> cnt_stack;
    node_stack.reserve(nextId + 1);
    max_stack.reserve(nextId + 1);
    cnt_stack.reserve(nextId + 1);

    node_stack.push_back(root);
    max_stack.push_back(0);
    cnt_stack.push_back(0);

    for (int i = 0; i < n; i++) {
      int x = getId(names[i]);
      int sp = (int)node_stack.size();
      if (sp >= 2 && x == node_stack[sp - 2]) {
        int child_max = max_stack.back();
        int child_cnt = cnt_stack.back();
        node_stack.pop_back();
        max_stack.pop_back();
        cnt_stack.pop_back();

        int S_child = (child_cnt == 0) ? 1 : (2 + child_max);

        cnt_stack.back() += 1;
        max_stack.back() = max(max_stack.back(), S_child);
      } else {
        node_stack.push_back(x);
        max_stack.push_back(0);
        cnt_stack.push_back(0);
      }
    }

    long long new_units =
        (cnt_stack[0] == 0) ? 0LL : (1LL + (long long)max_stack[0]);
    long long old_units = (long long)n;
    long long saved_seconds = (old_units - new_units) * 10LL;
    cout << saved_seconds << "\n";
  }

  return 0;
}