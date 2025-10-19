import sys

def main():
    input = sys.stdin.readline
    k = int(input().strip())
    n = int(input().strip())
    final_order = list(input().strip())
    
    rows = [input().strip() for _ in range(n)]
    q_idx = rows.index('?' * (k - 1))
    
    top_state = [chr(ord('A') + i) for i in range(k)]
    
    for r in range(q_idx):
        row = rows[r]
        for i in range(k - 1):
            if row[i] == '-':
                top_state[i], top_state[i + 1] = top_state[i + 1], top_state[i]
                
    bottom_state = final_order[:]
    for r in range(n - 1, q_idx, -1):
        row = rows[r]
        for i in range(k - 1):
            if row[i] == '-':
                bottom_state[i], bottom_state[i + 1] = bottom_state[i + 1], bottom_state[i]
                
    answer = []
    i = 0
    while i < k - 1:
        if top_state[i] == bottom_state[i]:
            answer.append('*')
            i += 1
        elif top_state[i] == bottom_state[i + 1] and top_state[i + 1] == bottom_state[i]:
            answer.append('-')
            top_state[i], top_state[i + 1] = top_state[i + 1], top_state[i]
            i += 1
        else:
            print('x' * (k - 1))
            return
    print(''.join(answer))
    
if __name__ == "__main__":
    main()