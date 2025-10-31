#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
using namespace std;

static string strip_leading_zeros(const string& s) {
    size_t i = 0;
    while (i + 1 < s.size() && s[i] == '0') ++i;
    return s.substr(i);
}

static string add_str(const string& a, const string& b) {
    int i = (int)a.size() - 1, j = (int)b.size() - 1, c = 0;
    string r;
    while (i >= 0 || j >= 0 || c) {
        int da = (i >= 0 ? a[i] - '0' : 0);
        int db = (j >= 0 ? b[j] - '0' : 0);
        int s = da + db + c;
        r.push_back(char('0' + (s % 10)));
        c = s / 10;
        --i; --j;
    }
    reverse(r.begin(), r.end());
    return strip_leading_zeros(r);
}

static string sub_str(const string& a, const string& b) {
    int i = (int)a.size() - 1, j = (int)b.size() - 1, borrow = 0;
    string r;
    while (i >= 0) {
        int da = (a[i] - '0') - borrow;
        int db = (j >= 0 ? b[j] - '0' : 0);
        if (da < db) {
            da += 10;
            borrow = 1;
        } else {
            borrow = 0;
        }
        r.push_back(char('0' + (da - db)));
        --i, --j;
    }
    reverse(r.begin(), r.end());
    return strip_leading_zeros(r);
}

static string mul_digit(const string& a, int d) {
    if (d == 0) return "0";
    int i = (int)a.size() - 1, c = 0;
    string r;
    while (i >= 0 || c) {
        int da = (i >= 0 ? a[i] - '0' : 0);
        int prod = da * d + c;
        r.push_back(char('0' + (prod % 10)));
        c = prod / 10;
        --i;
    }
    reverse(r.begin(), r.end());
    return strip_leading_zeros(r);
}

static string mul_str(const string& a, const string& b) {
    int n = (int)a.size(), m = (int)b.size();
    vector<int> v(n + m, 0);
    for (int i = n - 1; i >= 0; --i) {
        for (int j = m - 1; j >= 0; --j) {
            v[i + j + 1] += (a[i] - '0') * (b[j] - '0');
        }
    }
    for (int k = n + m - 1; k > 0; --k) {
        v[k - 1] += v[k] / 10;
        v[k] %= 10;
    }

    int p = 0;
    while (p + 1 < (int)v.size() && v[p] == 0) ++p;
    string r;
    r.reserve(v.size() - p);
    for (; p < (int)v.size(); ++p) r.push_back(char('0' + v[p]));
    return r;
}

static inline void print_repeat(char ch, int cnt) { while (cnt-- > 0) cout << ch; }
static inline void print_right(const string& s, int W) { print_repeat(' ', W - (int)s.size()); cout << s << '\n'; }

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    if (!(cin >> T)) return 0;
    while (T--) {
        string line;
        cin >> line;
        int pos = -1; char op = 0;
        for (int i = 0; i < (int)line.size(); ++i) {
            if (line[i] == '+' || line[i] == '-' || line[i] == '*') { pos = i; op = line[i]; break; }
        }
        string A = line.substr(0, pos);
        string B = line.substr(pos + 1);

        string R;
        vector<string> parts;
        if (op == '+') R = add_str(A, B);
        else if (op == '-') R = sub_str(A, B);
        else {
            R = mul_str(A, B);
            for (int k = (int)B.size() - 1; k >= 0; --k) {
                parts.push_back(mul_digit(A, B[k] - '0'));
            }
        }
        int lenA = (int)A.size();
        int lenBop = (int)B.size() + 1;
        int lenR = (int)R.size();

        if (op == '+' || op == '-') {
            int W = max({lenA, lenBop, lenR});
            int dash1 = max(lenBop, lenR);
            print_right(A, W);
            print_right(string(1, op) + B, W);
            print_right(string(dash1, '-'), W);
            print_right(R, W);
            cout << '\n';
        } else {
            if ((int)B.size() == 1) {
                int W = max({lenA, lenBop, lenR});
                int dash1 = max(lenBop, (int)parts[0].size());
                print_right(A, W);
                print_right(string(1, '*') + B, W);
                print_right(string(dash1, '-'), W);
                print_right(R, W);
                cout << '\n';
            } else {
                int max_part = 0;
                for (int i = 0; i < (int)parts.size(); ++i) 
                    max_part = max(max_part, (int)parts[i].size() + i);

                int W = max({lenA, lenBop, lenR, max_part});
                int dash1 = max(lenBop, (int)parts[0].size());
                int dash2 = max(lenR, max_part);

                print_right(A, W);
                print_right(string(1, '*') + B, W);
                print_right(string(dash1, '-'), W);
                for (int i = 0; i < (int)parts.size(); ++i) {
                    int lead = W - ((int)parts[i].size() + i);
                    print_repeat(' ', lead);
                    cout << parts[i] << '\n';
                }
                print_right(string(dash2, '-'), W);
                print_right(R, W);
                cout << '\n';
            }
        }
    }

    return 0;
}