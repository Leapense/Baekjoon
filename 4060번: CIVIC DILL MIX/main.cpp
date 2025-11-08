#include <bits/stdc++.h>
using namespace std;

static inline int rvalue(char c) {
    switch (c) {
        case 'I': return 1;
        case 'V': return 5;
        case 'X': return 10;
        case 'L': return 50;
        case 'C': return 100;
        case 'D': return 500;
        case 'M': return 1000;
        default: return 0;
    }
}

int roman_to_int(const string& s) {
    int n = (int)s.size(), sum = 0;
    for (int i = 0; i < n; ++i) {
        int v = rvalue(s[i]);
        int next = (i + 1 < n ? rvalue(s[i + 1]) : 0);
        if (v < next) sum -= v;
        else sum += v;
    }
    return sum;
}

string int_to_roman(int x) {
    static const pair<int, string> table[] = {
        {1000, "M"},{900, "CM"},{500, "D"},{400, "CD"},
        {100, "C"},{90, "XC"},{50, "L"},{40, "XL"},
        {10, "X"},{9, "IX"},{5, "V"},{4, "IV"},{1, "I"}
    };
    string out;
    for (auto [val, sym] : table) {
        while (x >= val) { out += sym; x -= val; }
    }
    return out;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    int n, caseNo = 1;
    while ((cin >> n)) {
        if (n == 0) break;
        long long sum = 0;
        for (int i = 0; i < n; ++i) {
            string tok; cin >> tok;
            sum += roman_to_int(tok);
        }

        cout << "Case " << int_to_roman(caseNo++) << ": " << int_to_roman((int)sum) << "\n";
    }

    return 0;
}