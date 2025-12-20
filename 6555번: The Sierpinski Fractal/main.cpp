#include <bits/stdc++.h>
using namespace std;

static void draw(int r, int c, int h, vector<string>& g) {
    if (h == 2) {
        g[r][c + 1] = '/';
        g[r][c + 2] = '\\';
        g[r + 1][c] = '/';
        g[r + 1][c + 1] = '_';
        g[r + 1][c + 2] = '_';
        g[r + 1][c + 3] = '\\';
        return;
    }
    
    int half = h / 2;
    draw(r, c + half, h / 2, g);
    draw(r + half, c, h / 2, g);
    draw(r + half, c + h, h / 2, g);
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    
    int n;
    while (cin >> n) {
        if (n == 0) break;
        int H = 1 << n;
        int W = 2 * H;
        vector<string> g(H, string(W, ' '));
        draw(0, 0, H, g);
        
        for (int r = 0; r < H; r++) {
            int last = W - 1;
            while (last >= 0 && g[r][last] == ' ') last--;
            if (last >= 0) cout << g[r].substr(0, last + 1);
            cout << '\n';
        }
        cout << '\n';
    }
    
    return 0;
}