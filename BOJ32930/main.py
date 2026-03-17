import sys

def solve():
    input = sys.stdin.readline
    N, M = map(int, input().split())
    current_targets = [tuple(map(int, input().split())) for _ in range(N)]
    new_targets = [tuple(map(int, input().split())) for _ in range(M)]
    
    cx, cy = 0, 0
    total_score = 0
    for i in range(M):
        best_idx = -1
        best_dist2 = -1
        
        for idx, (x, y) in enumerate(current_targets):
            dist2 = (x - cx) ** 2 + (y - cy) ** 2
            if dist2 > best_dist2:
                best_dist2 = dist2
                best_idx = idx
        tx, ty = current_targets[best_idx]
        total_score += best_dist2
        cx, cy = tx, ty
        
        current_targets.pop(best_idx)
        current_targets.append(new_targets[i])
        
    print(total_score, end='')
    
if __name__ == "__main__":
    solve()