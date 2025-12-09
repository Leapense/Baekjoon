#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N;
    while ((cin >> N) && N != 0) {
        vector<vector<int>> adj(N);
        for (int i = 0; i < N; ++i) {
            int x;
            while (cin >> x && x != 0) {
                adj[i].push_back(x - 1);
            }
        }

        vector<string> result(N, "");

        while (true) {
            int P;
            cin >> P;
            if (!cin) return 0;
            if (P == 0) break;

            int T1, T2;
            string A1, A2, A3;
            cin >> T1 >> T2 >> A1 >> A2 >> A3;

            vector<char> forwarded(N, 0);
            queue<int> q;

            int origin = P - 1;
            forwarded[origin] = 1;
            q.push(origin);

            while (!q.empty()) {
                int v = q.front();
                q.pop();
                for (int u : adj[v]) {
                    if (!forwarded[u]) {
                        forwarded[u] = 1;
                        q.push(u);
                    }
                }
            }

            for (int i = 0; i < N; ++i) {
                int T = forwarded[i] ? static_cast<int>(adj[i].size()) : 0;
                const string* attr;
                if (T < T1) attr = &A1;
                else if (T < T2) attr = &A2;
                else attr = &A3;

                result[i] += *attr + " ";
            }
        }

        vector<string> name(N);
        for (int i = 0; i < N; ++i) {
            cin >> name[i];
        }

        for (int i = 0; i < N; ++i) {
            cout << name[i] << ": " << result[i] << "\n";
        }
    }

    return 0;
}