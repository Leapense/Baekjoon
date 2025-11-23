import sys

def generate_ms_primes(limit: int):
    ms_primes = []
    for x in range(2, limit + 1):
        r = x % 7
        if r != 1 and r != 6:
            continue
        
        is_prime = True
        for p in ms_primes:
            if p * p > x:
                break
            if x % p == 0:
                is_prime = False
                break
            
        if is_prime:
            ms_primes.append(x)
            
    return ms_primes

def main():
    nums = []
    
    for line in sys.stdin:
        line = line.strip()
        if not line:
            continue
        n = int(line)
        if n == 1:
            break
        nums.append(n)
        
    if not nums:
        return
    max_n = max(nums)
    ms_primes = generate_ms_primes(max_n)
    out_lines = []
    for n in nums:
        factors = []
        for p in ms_primes:
            if p > n:
                break
            if n % p == 0:
                factors.append(p)
                
        line = f"{n}:"
        if factors:
            line += " " + " ".join(map(str, factors))
        out_lines.append(line)
        
    sys.stdout.write("\n".join(out_lines))
    
if __name__ == "__main__":
    main()