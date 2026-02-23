import sys
from math import gcd
from typing import List, Tuple

def lcm(a: int, b: int) -> int:
    return (a // gcd(a, b)) * b

def main() -> None:
    input_data = sys.stdin.read().split()
    if not input_data:
        return
    
    n = int(input_data[0])
    lights: List[Tuple[int, int, int]] = []
    max_lcm = 1
    
    idx = 1
    for _ in range(n):
        r = int(input_data[idx])
        g = int(input_data[idx + 1])
        idx += 2
        p = r + g
        lights.append((r, g, p))
        max_lcm = lcm(max_lcm, p)
        
    anchor = 0
    for i in range(1, n):
        gi, pi = lights[i][1], lights[i][2]
        ga, pa = lights[anchor][1], lights[anchor][2]
        if gi * pa < ga * pi or (gi * pa == ga * pi and pi > pa):
            anchor = i
            
    ra, ga, pa = lights[anchor]
    jump = ra + 1
    
    others = [(r, p) for i, (r, g, p) in enumerate(lights) if i != anchor]
    others.sort(key=lambda x: (-x[0], x[1]))
    
    rlist = [x[0] for x in others]
    plist = [x[1] for x in others]
    m = len(plist)
    
    trans1 = [tuple((x + 1) % pi for x in range(pi)) for pi in plist]
    transJ = [tuple((x + jump) % pi for x in range(pi)) for pi in plist]
    
    t = ra
    offset = ra
    residues = [t % pi for pi in plist]
    
    while t < max_lcm:
        if all(residues[j] >= rlist[j] for j in range(m)):
            sys.stdout.write(f"{t}")
            return
        
        if offset == pa - 1:
            t += jump
            offset = ra
            for j in range(m):
                residues[j] = transJ[j][residues[j]]
        else:
            t += 1
            offset += 1
            for j in range(m):
                residues[j] = trans1[j][residues[j]]
    sys.stdout.write("-1")
    
if __name__ == "__main__":
    main()