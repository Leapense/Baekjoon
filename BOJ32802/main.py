import sys

def main():
    input = sys.stdin.readline
    
    n = int(input().strip())
    events = []
    
    for _ in range(n):
        t, s, c, g = input().split()
        s = int(s)
        c = int(c)
        g = int(g)
        events.append((t, s, c, g))
        
    k = int(input().strip())
    
    cheese = 0
    glory = 0
    
    for t, s, c, g in events:
        if s < k:
            if t == "CAUGHT":
                cheese += c
                glory += g
            else:
                cheese -= c
                glory -= g
                
    print(cheese, glory)
    
if __name__ == "__main__":
    main()