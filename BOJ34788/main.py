import sys

def solve():
    input = sys.stdin.readline
    n, a, b = map(int, input().split())
    total_len = b - a
    all_relaxed_prob = 1.0
    for _ in range(n):
        s, e = map(int, input().split())
        overlap = max(0, min(b, e) - max(a, s))
        relaxed_prob = overlap / total_len
        all_relaxed_prob *= relaxed_prob
        
    answer = 1.0 - all_relaxed_prob
    print(answer, end='')
    
if __name__ == "__main__":
    solve()