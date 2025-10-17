import sys

def main():
    input = sys.stdin.readline
    n = int(input().strip())
    dice = [list(map(int, input().split())) for _ in range(n)]
    
    opp = [5, 3, 4, 1, 2, 0]
    def side_max(top_val, bottom_val):
        if 6 != top_val and 6 != bottom_val:
            return 6
        if 5 != top_val and 5 != bottom_val:
            return 5
        return 4
    
    best = 0
    for bottom_idx_first in range(6):
        bottom_val = dice[0][bottom_idx_first]
        top_idx = opp[bottom_idx_first]
        top_val = dice[0][top_idx]
        total = side_max(top_val, bottom_val)
        need_bottom_val = top_val
        for i in range(1, n):
            faces = dice[i]
            for j in range(6):
                if faces[j] == need_bottom_val:
                    bottom_idx = j
                    break
            top_idx = opp[bottom_idx]
            top_val = faces[top_idx]
            total += side_max(top_val, faces[bottom_idx])
            need_bottom_val = top_val
            
        if total > best:
            best = total
            
    print(best)
    
if __name__ == "__main__":
    main()