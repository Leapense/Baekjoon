import sys
from collections import deque

def main():
    input = sys.stdin.buffer.readline
    N, M = map(int, input().split())
    H = [list(map(int, input().split())) for _ in range(N)]
    visited = bytearray(N * M)
    dr = (-1, -1, -1, 0, 0, 1, 1, 1)
    dc = (-1, 0, 1, -1, 1, -1, 0, 1)
    
    peaks = 0
    for r0 in range(N):
        row = H[r0]
        base_idx = r0 * M
        for c0 in range(M):
            idx0 = base_idx + c0
            if visited[idx0]:
                continue
            visited[idx0] = 1
            h = row[c0]
            q = deque()
            q.append((r0, c0))
            
            is_peak = True
            while q:
                r, c = q.popleft()
                for k in range(8):
                    nr = r + dr[k]
                    nc = c + dc[k]
                    if 0 <= nr < N and 0 <= nc < M:
                        nh = H[nr][nc]
                        if nh > h:
                            is_peak = False
                        elif nh == h:
                            nidx = nr * M + nc
                            if not visited[nidx]:
                                visited[nidx] = 1
                                q.append((nr, nc))
                                
            if is_peak:
                peaks += 1
    print(peaks)
    
if __name__ == "__main__":
    main()