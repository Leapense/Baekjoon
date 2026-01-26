import sys

def main():
    s = sys.stdin.readline().strip()
    if not s:
        print("")
        return
    
    out = []
    prev = s[0]
    cnt = 1
    
    for ch in s[1:]:
        if ch == prev:
            cnt += 1
        else:
            if cnt > 1:
                out.append(prev * (cnt - 1))
            prev = ch
            cnt = 1
            
    if cnt > 1:
        out.append(prev * (cnt - 1))
    
    sys.stdout.write("".join(out) + '\n')
    
if __name__ == "__main__":
    main()