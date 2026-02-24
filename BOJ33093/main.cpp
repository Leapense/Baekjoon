#include <iostream>
#include <vector>
#include <string>
#include <unordered_set>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N, M, K;
    if (!(cin >> N >> M >> K)) return 0;

    unordered_set<string> blocked_institutes;
    blocked_institutes.reserve(M + K);

    for (int i = 0; i < M; ++i) {
        string name, inst;
        cin >> name >> inst;
        blocked_institutes.insert(inst);
    }

    vector<string> winners;
    winners.reserve(K);

    for (int i = M; i < N; ++i) {
        string name, inst;
        cin >> name >> inst;
        if (winners.size() == K) continue;

        if (!blocked_institutes.contains(inst)) {
            blocked_institutes.insert(inst);

            winners.push_back(name);
        }
    }

    cout << winners.size() << "\n";

    for (const auto& name : winners) {
        cout << name << "\n";
    }

    return 0;
}