#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    const double PI = acos(-1.0);

    string line;
    while (getline(cin, line)) {
        if (line.empty()) continue;
        string original = line;

        istringstream iss(line);
        vector<string> tok;
        string s;
        while (iss >> s) tok.push_back(s);

        int width_mm = stoi(tok[1]);
        int ratio_pct = stoi(tok[3]);
        int rim_in = stoi(tok.back());

        double section_height_mm = 1.0 * width_mm * ratio_pct / 100.0;
        double rim_mm = rim_in * 25.4;
        double overall_diameter_mm = rim_mm + 2.0 * section_height_mm;
        double circumference_cm = PI * (overall_diameter_mm / 10.0);
        long long ans = llround(circumference_cm);
        
        cout << original << ": " << ans << "\n";
    }

    return 0;
}