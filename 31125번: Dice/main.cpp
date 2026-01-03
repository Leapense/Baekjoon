#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    
    int B;
    cin >> B;
    
    while (B--) {
        int S, n, f, m;
        cin >> S >> n >> f >> m;
        
        int mn = n + m;
        int mx = n * f + m;
        
        cout << ((mn <= S && S <= mx) ? "YES\n" : "NO\n");
    }
    
    return 0;
}