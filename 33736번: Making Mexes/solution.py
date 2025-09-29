import sys

def solve():
    try:
        N = int(sys.stdin.readline())
    except EOFError:
        return
    except ValueError:
        return
    
    try:
        A = list(map(int, sys.stdin.readline().split()))
    except EOFError:
        A = []
    except ValueError:
        A = []
        
    if N == 0:
        if 0 <= N:
            print(0)
        return
    
    C = [0] * (N + 1)
    for a in A:
        if 0 <= a <= N:
            C[a] += 1
            
    R = [0] * (N + 1)
    missing_count = 0
    
    for i in range(1, N + 1):
        if C[i - 1] == 0:
            missing_count += 1
            
        R[i] = missing_count
        
    output = []
    for i in range(N + 1):
        O_i = max(R[i], C[i])
        output.append(str(O_i))
        
    sys.stdout.write('\n'.join(output) + '\n')
    
solve()