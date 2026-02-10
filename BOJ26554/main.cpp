#include <bits/stdc++.h>
using namespace std;

static void repeat_char(char ch, int cnt) {
    for (int i = 0; i < cnt; i++) cout << ch;
}

static void draw_rectangle(int r, int c, char fill) {
    for (int i = 0; i < r; i++) {
        for (int j = 0; j < c; j++) {
            if (fill == 'y') cout << '#';
            else {
                if (i == 0 || i == r - 1 || j == 0 || j == c - 1) cout << '#';
                else cout << ' ';
            }
        }
        cout << '\n';
    }
}

static void draw_left_triangle(int s, char fill) {
    for (int i = 1; i <= s; i++) {
        if (fill == 'y') {
            repeat_char('#', i);
        } else {
            if (i == 1) cout << '#';
            else if (i == s) repeat_char('#', s);
            else {
                cout << '#';
                repeat_char(' ', i - 2);
                cout << '#';
            }
        }

        cout << '\n';
    }
}

static void draw_right_triangle(int s, char fill) {
    for (int i = 1; i <= s; i++) {
        int lead = s - i;
        repeat_char(' ', lead);

        if (fill == 'y') {
            repeat_char('#', i);
        } else {
            if (i == 1) cout << '#';
            else if (i == s) repeat_char('#', s);
            else {
                cout << '#';
                repeat_char(' ', i - 2);
                cout << '#';
            }
        }

        cout << '\n';
    }
}

static void draw_diamond(int s, char fill) {
    int mid = s / 2;
    for (int r = 0; r < s; r++) {
        int dist = abs(mid - r);
        int w = s - 2 * dist;

        repeat_char(' ', dist);
        if (fill == 'y') {
            repeat_char('#', w);
        } else {
            if (w == 1) cout << '#';
            else {
                cout << '#';
                repeat_char(' ', w - 2);
                cout << '#';
            }
        }

        cout << '\n';
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n;
    cin >> n;
    string dummy;
    getline(cin, dummy);

    for (int t = 0; t < n; t++) {
        string line;
        getline(cin, line);

        auto starts_with = [&](const string& p) {
            return line.size() >= p.size() && line.compare(0, p.size(), p) == 0;
        };

        if (starts_with("rectangle")) {
            string name;
            int r, c;
            char fill;
            {
                istringstream iss(line);
                iss >> name >> r >> c >> fill;
            }
            draw_rectangle(r, c, fill);
        } else if (starts_with("left triangle")) {
            string a, b;
            int s;
            char fill;
            {
                istringstream iss(line);
                iss >> a >> b >> s >> fill;
            }
            draw_left_triangle(s, fill);
        } else if (starts_with("right triangle")) {
            string a, b;
            int s;
            char fill;
            {
                istringstream iss(line);
                iss >> a >> b >> s >> fill;
            }
            draw_right_triangle(s, fill);
        } else if (starts_with("diamond")) {
            string name;
            int s;
            char fill;
            {
                istringstream iss(line);
                iss >> name >> s >> fill;
            }
            draw_diamond(s, fill);
        }
    }
    return 0;
}