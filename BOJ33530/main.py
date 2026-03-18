import sys
import math

def is_prime(n: int) -> bool:
    if n < 2:
        return False
    if n == 2:
        return True
    if n % 2 == 0:
        return False
    
    limit = int(math.isqrt(n))
    for d in range(3, limit + 1, 2):
        if n % d == 0:
            return False
    return True

def main():
    n = int(sys.stdin.readline().strip())
    print("SAFE" if is_prime(n) else "BROKEN", end='')
    
if __name__ == "__main__":
    main()