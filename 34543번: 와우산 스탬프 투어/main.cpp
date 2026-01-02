#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    
    int N, W;
    cin >> N >> W;
    
    int score = N * 10;
    
    if (N >= 3) score += 20;
    if (N == 5) score += 50;
    if (W > 1000) score -= 15;
    
    score = max(score, 0);
    cout << score << "\n";
    return 0;
}