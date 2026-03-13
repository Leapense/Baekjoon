import sys

def solve():
    input_data = sys.stdin.read().split()
    if not input_data:
        return
    
    n = int(input_data[0])
    k = int(input_data[1])
    p = int(input_data[2])
    
    e_cont_ms = 100 * (n - k) + 100 + p * (n + 4)
    e_back_ms = 100 * (n - k) + 200 + (100 - p) * (n + 4)
    e_rest_ms = 100 * n + 400
    
    min_ms = min(e_cont_ms, e_back_ms, e_rest_ms)
    
    if min_ms == e_cont_ms:
        print("continue", end='')
    elif min_ms == e_back_ms:
        print("backspace", end='')
    else:
        print("restart", end='')
        
if __name__ == "__main__":
    solve()