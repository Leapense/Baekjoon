#include <iostream>
#include <string>
#include <cctype>
#include <algorithm>

using namespace std;

static bool allowed83(char c) {
    if ('A' <= c && c <= 'Z') return true;
    if ('0' <= c && c <= '9') return true;
    const string extra = "'-!#$%&()@^_{}.~";
    return extra.find(c) != string::npos;
}

static bool valid83(const string& s) {
    int dots = 0;
    for (char c : s) {
        if (!allowed83(c)) return false;
        if (c == '.') ++dots;
    }

    if (dots > 1) return false;

    size_t p = s.find('.');
    if (p == string::npos) {
        return s.size() <= 8;
    }

    return p <= 8 && (s.size() - p - 1) <= 3;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    string s;
    getline(cin, s);

    for (char& c : s) {
        c = static_cast<char>(toupper(static_cast<unsigned char>(c)));
    }

    if (valid83(s)) {
        cout << s;
        return 0;
    }

    string t;

    for (char c : s) {
        if (c == ' ') continue;
        if (c == '"' || c == '/' || c == '\\' || c == '[' || c == ']' || c == ':' || c == ';' || c == '=' || c == ',') {
            t += '_';
        } else {
            t += c;
        }
    }

    while (!t.empty() && t.back() == '.') {
        t.pop_back();
    }

    size_t lastDot = t.rfind('.');
    if (lastDot != string::npos) {
        string u;
        for (size_t i = 0; i < t.size(); ++i) {
            if (t[i] == '.' && i != lastDot) continue;
            u += t[i];
        }

        t = u;
    }

    lastDot = t.rfind('.');
    if (lastDot == string::npos) {
        string X = t.substr(0, min<size_t>(6, t.size()));
        cout << X << "~1";
    } else {
        string left = t.substr(0, lastDot);
        string right = t.substr(lastDot + 1);
        string X = left.substr(0, min<size_t>(6, left.size()));
        string Y = right.substr(0, min<size_t>(3, right.size()));

        cout << X << "~1." << Y; 
    }

    return 0;
}