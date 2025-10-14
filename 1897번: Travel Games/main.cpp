#include <bits/stdc++.h>
using namespace std;

static bool lengthThenLexCmp(const int &a, const int &b, const vector<int> &lens, const vector<string> &words) {
    if (lens[a] != lens[b]) return lens[a] < lens[b];
    return words[a] < words[b];
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int D;
    string seed;
    if (!(cin >> D >> seed)) return 0;

    vector<string> words;
    words.reserve(D);
    for (int i = 0; i < D; ++i) {
        string w;
        cin >> w;
        words.push_back(w);
    }

    sort(words.begin(), words.end());
    words.erase(unique(words.begin(), words.end()), words.end());

    bool hasSeed = binary_search(words.begin(), words.end(), seed);
    if (!hasSeed) {
        cout << seed << "\n";
        return 0;
    }

    const int n = static_cast<int>(words.size());

    unordered_map<string, int> indexOf;
    indexOf.reserve(n * 2);
    for (int i = 0; i < n; ++i) indexOf[words[i]] = i;

    vector<int> lens(n);
    for (int i = 0; i < n; ++i) lens[i] = static_cast<int>(words[i].size());
    vector<int> order(n);
    for (int i = 0; i < n; ++i) {
        order[i] = i;
    }
    sort(order.begin(), order.end(), [&](const int &a, const int &b) { return lengthThenLexCmp(a, b, lens, words); });
    vector<int> dp(n, std::numeric_limits<int>::min());
    vector<int> parent(n, -1);

    const int seedIdx = indexOf[seed];
    dp[seedIdx] = 0;

    int bestIdx = seedIdx;

    for (int t = 0; t < n; ++t) {
        const int i = order[t];
        if (lens[i] < (int)seed.size()) continue;
        if (i == seedIdx) continue;

        const string &w = words[i];
        int bestPred = -1;
        int bestVal = std::numeric_limits<int>::min();

        for (size_t pos = 0; pos < w.size(); ++pos) {
            string v = w.substr(0, pos) + w.substr(pos + 1);
            unordered_map<string, int>::const_iterator it = indexOf.find(v);
            if (it != indexOf.end()) {
                int j = it->second;
                if (dp[j] != std::numeric_limits<int>::min()) {
                    int cand = dp[j] + 1;
                    if (cand > bestVal) {
                        bestVal = cand;
                        bestPred = j;
                    }
                }
            }
        }
        if (bestPred != -1) {
            dp[i] = bestVal;
            parent[i] = bestPred;
            if (lens[i] > lens[bestIdx]) bestIdx = i;
        }
    }

    cout << words[bestIdx] << "\n";
    return 0;
}