#include <bits/stdc++.h>
using namespace std;

static inline int idx(char c) {
    switch(c) {
        case 'A': return 0;
        case 'C': return 1;
        case 'G': return 2;
        case 'T': return 3;
    }
    return 0;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    int M;
    string S;
    if (!(cin >> M)) return 0;
    cin >> S;
    const int L = (int)S.size();
    M = min(M, L);

    int answer = INT_MAX;

    for (int P = 1; P <= M; ++P) {
        int changes = 0;
        for (int r = 0; r < P; ++r) {
            int cnt[4] = {0,0,0,0};
            int group_size = 0;
            for (int j = r; j < L; j += P) {
                ++cnt[idx(S[j])];
                ++group_size;
            }
            int best = max(max(cnt[0], cnt[1]), max(cnt[2], cnt[3]));
            changes += (group_size - best);
        }
        answer = min(answer, changes);
    }

    cout << answer << "\n";
    return 0;
}