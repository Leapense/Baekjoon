import sys

def main():
    input = sys.stdin.readline
    N = int(input().strip())
    windows = [tuple(map(int, input().split())) for _ in range(N)]
    top_right = [(R1, S2) for (R1, S1, R2, S2) in windows]
    
    g = [[] for _ in range(N)]
    for i in range(N):
        ri, ci = top_right[i]
        for j in range(i + 1, N):
            R1, S1, R2, S2 = windows[j]
            if R1 <= ri <= R2 and S1 <= ci <= S2:
                g[i].append(j)
    visited = [False] * N
    stack = [0]
    visited[0] = True
    
    needed = set()
    while stack:
        u = stack.pop()
        for v in g[u]:
            if not visited[v]:
                visited[v] = True
                needed.add(v)
                stack.append(v)
                
    print(len(needed) + 1)
    
if __name__ == "__main__":
    main()