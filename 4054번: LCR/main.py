import sys

def simulate(n, s):
    chips = [3] * n
    center = 0
    p = 0
    turn = 0
    
    def left(i):
        return (i + 1) % n
    def right(i):
        return (i - 1 + n) % n
    def winner_index():
        alive = [i for i, c in enumerate(chips) if c > 0]
        if len(alive) == 1:
            return alive[0]
        return None
    while True:
        w = winner_index()
        if w is not None:
            return chips, center, True, w, turn
        
        if chips[turn] == 0:
            turn = left(turn)
            continue
        roll_cnt = min(3, chips[turn])
        
        if p + roll_cnt > len(s):
            return chips, center, False, None, turn
        
        for _ in range(roll_cnt):
            ch = s[p]
            p += 1
            if ch == 'L':
                chips[turn] -= 1
                chips[left(turn)] += 1
            elif ch == 'R':
                chips[turn] -= 1
                chips[right(turn)] += 1
            elif ch == 'C':
                chips[turn] -= 1
                center += 1
            elif ch == '.':
                pass
            else:
                pass
            
        w = winner_index()
        if w is not None:
            return chips, center, True, w, left(turn)
        
        turn = left(turn)
        
def main():
    case_no = 1
    out_lines = []
    for line in sys.stdin:
        line = line.strip()
        if not line:
            continue
        if line == '0':
            break
        parts = line.split(maxsplit=1)
        n = int(parts[0])
        rolls = parts[1] if len(parts) > 1 else ""
        
        chips, center, has_winner, widx, next_turn = simulate(n, rolls)
        
        out_lines.append(f"Game {case_no}:")
        for i in range(n):
            mark = ""
            if has_winner and i == widx:
                mark = "(W)"
            elif (not has_winner) and i == next_turn:
                mark = "(*)"
            out_lines.append(f"Player {i+1}:{chips[i]}{mark}")
        out_lines.append(f"Center:{center}")
        out_lines.append("")
        case_no += 1
        
    print("\n".join(out_lines))
    
if __name__ == "__main__":
    main()