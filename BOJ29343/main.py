import sys

def solve():
    input = sys.stdin.readline
    n = int(input().strip())
    s = input().strip()
    n = len(s)
    
    vowels = set("aouie")
    pref = [0] * (n + 1)
    
    for i, ch in enumerate(s, start=1):
        pref[i] = pref[i - 1] + (1 if ch.lower() in vowels else 0)
        
    total = pref[n]
    ans = 0
    for k in range(1, n + 1):
        pv = pref[k]
        sv = total - pref[n - k]
        if pv != 0 and pv == sv:
            ans += 1
            
    print(ans)
    
if __name__ == "__main__":
    solve()