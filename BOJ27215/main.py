import sys
import math

def is_prime(n: int) -> bool:
    if n <= 1:
        return False
    if n <= 3:
        return True
    if n % 2 == 0:
        return False
    i = 3
    while i * i <= n:
        if n % i == 0:
            return False
        i += 2
    return True

def main():
    input = sys.stdin.readline
    x = int(input().strip())
    l = int(input().strip())
    r = int(input().strip())
    prime_cache = {}
    
    ans = []
    for y in range(l, r + 1):
        if y == x:
            continue
        g = math.gcd(x, y)
        if g == 1:
            ans.append(y)
        else:
            if g not in prime_cache:
                prime_cache[g] = is_prime(g)
            if prime_cache[g]:
                ans.append(y)
                
    print(len(ans))
    if ans:
        print(*ans)
    else:
        print()
        
if __name__ == "__main__":
    main()