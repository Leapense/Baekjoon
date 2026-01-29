import sys

def main():
    input = sys.stdin.readline
    N = int(input().strip())
    r0 = input().rstrip('\n')
    r1 = input().rstrip('\n')
    
    res = []
    start = 0
    
    for i in range(N + 1):
        if i == N or (r0[i] == '.' and r1[i] == '.'):
            seg_len = i - start
            if seg_len == 4:
                res.append('v')
            elif seg_len == 8:
                res.append('w')
            start = i + 1
            
    print(''.join(res))
    
if __name__ == "__main__":
    main()