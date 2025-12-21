#include <bits/stdc++.h>
using namespace std;

struct Term {
    int idx;
    int sign;
    int pre;
    int post;
};

static bool starts_with2(const string& s, size_t i, const char a, const char b) {
    return i + 1 < s.size() && s[i] == a && s[i + 1] == b;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    
    int T;
    cin >> T;
    string dummy;
    getline(cin, dummy);
    
    for (int tc = 0; tc < T; tc++) {
        string orig;
        getline(cin, orig);
        
        string s;
        s.reserve(orig.size());
        for (unsigned char c : orig) {
            if (!isspace(c)) s.push_back((char)c);
        }
        
        array<int, 26> val{};
        for (int i = 0; i < 26; i++) val[i] = i + 1;
        
        vector<Term> terms;
        terms.reserve(64);
        
        array<bool, 26> used{};
        used.fill(false);
        
        size_t i = 0;
        while (i < s.size()) {
            int sign = +1;
            if (i != 0) {
                if (s[i] == '+') { sign = +1; i++; }
                else if (s[i] == '-') { sign = -1; i++; }
            }
            
            int pre = 0, post = 0;
            
            if (starts_with2(s, i, '+', '+')) { pre = +1; i += 2; }
            else if (starts_with2(s, i, '-', '-')) { pre = -1; i += 2; }
            
            char v = s[i];
            int idx = v - 'a';
            i++;
            
            if (starts_with2(s, i, '+', '+')) { post = +1; i += 2; }
            else if (starts_with2(s, i, '-', '-')) { post = -1; i += 2; }
            
            terms.push_back({idx, sign, pre, post});
            used[idx] = true;
        }
        
        for (auto &t : terms) {
            if (t.pre) val[t.idx] += t.pre;
        }
        
        int result = 0;
        for (auto &t: terms) {
            result += t.sign * val[t.idx];
        }
        
        for (auto &t : terms) {
            if (t.post) val[t.idx] += t.post;
        }
        
        cout << "Expression: " << orig << "\n";
        cout << "value = " << result << "\n";
        for (int idx = 0; idx < 26; idx++) {
            if (used[idx]) {
                cout << char('a' + idx) << " = " << val[idx] << "\n";
            }
        }
    }
    
    return 0;
}