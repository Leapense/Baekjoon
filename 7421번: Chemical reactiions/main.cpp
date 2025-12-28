#include <iostream>
#include <string>
#include <array>
#include <cctype>

using namespace std;

static constexpr int ELEM_SZ = 26 * 27;
using Counts = array<long long, ELEM_SZ>;

static void add_mul(Counts& dst, const Counts& src, long long mult) {
    for (int i = 0; i < ELEM_SZ; i++) dst[i] += src[i] * mult;
}

static long long parse_number(const string& s, size_t& i) {
    long long v = 0;
    while (i < s.size() && isdigit((unsigned char)s[i])) {
        v = v * 10 + (s[i] - '0');
        i++;
    }
    return v;
}

static long long parse_opt_number(const string& s, size_t& i) {
    if (i < s.size() && isdigit((unsigned char)s[i])) return parse_number(s, i);
    return 1;
}

static Counts parse_sequence(const string& s, size_t& i);

static Counts parse_element(const string& s, size_t& i) {
    Counts out{};
    out.fill(0);

    if (s[i] == '(') {
        i++;
        out = parse_sequence(s, i);
        if (i < s.size() && s[i] == ')') i++;
        return out;
    }

    char u = s[i++];
    char l = 0;
    if (i < s.size() && islower((unsigned char)s[i])) l = s[i++];

    int first = u - 'A';
    int second = l ? (l - 'a' + 1) : 0;
    int id = first * 27 + second;

    out[id] = 1;
    return out;
}

static Counts parse_sequence(const string& s, size_t& i) {
    Counts out{};
    out.fill(0);

    while (i < s.size() && s[i] != ')' && s[i] != '+') {
        Counts elem = parse_element(s, i);
        long long mult = parse_opt_number(s, i);
        add_mul(out, elem, mult);
    }

    return out;
}

static Counts parse_formula(const string& s) {
    Counts out{};
    out.fill(0);

    size_t i = 0;
    while (true) {
        long long term_mult = parse_opt_number(s, i);
        Counts seq = parse_sequence(s, i);
        add_mul(out, seq, term_mult);

        if (i < s.size() && s[i] == '+') { i++; continue; }
        break;
    }

    return out;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    string left;
    getline(cin, left);

    int N;
    cin >> N;
    
    Counts L = parse_formula(left);

    for (int k = 0; k < N; k++) {
        string right;
        cin >> right;

        Counts R = parse_formula(right);
        if (L == R) cout << left << "==" << right << "\n";
        else cout << left << "!=" << right << "\n";
    }

    return 0;
}