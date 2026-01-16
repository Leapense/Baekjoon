#include <bits/stdc++.h>
using namespace std;

static int to_minutes_24(int h12, int m, const string& ap) {
    int h24;
    if (ap == "AM") h24 = (h12 == 12) ? 0 : h12;
    else            h24 = (h12 == 12) ? 12 : (h12 + 12);
    return h24 * 60 + m;
}

static void from_minutes_24(int minutes, int &h12, int &m, string &ap) {
    int h24 = minutes / 60;
    m = minutes % 60;
    ap = (h24 < 12) ? "AM" : "PM";
    if (h24 == 0) h12 = 12;
    else if (h24 <= 12) h12 = h24;
    else h12 = h24 - 12;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    
    string line;
    getline(cin, line);
    int t;
    cin >> t;
    
    int h12 = 0, mm = 0;
    string ap;
    
    {
        auto pos_colon = line.find(':');
        auto pos_space = line.find(' ', pos_colon + 1);
        h12 = stoi(line.substr(0, pos_colon));
        mm = stoi(line.substr(pos_colon + 1, pos_space - (pos_colon + 1)));
        ap = line.substr(pos_space + 1);
    }
    
    int meeting = to_minutes_24(h12, mm, ap);
    int leave = meeting - t;
    leave = ((leave % 1440) + 1440) % 1440;
    
    int out_h, out_m;
    string out_ap;
    from_minutes_24(leave, out_h, out_m, out_ap);
    
    cout << out_h << ':' << setw(2) << setfill('0') << out_m << ' ' << out_ap;
    return 0;
}