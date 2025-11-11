import sys
import math

def steps_for_distance(d: int) -> int:
    if d == 0:
        return 0
    k = math.isqrt(d)
    if d == k * k:
        return 2 * k - 1
    elif d <= k * k + k:
        return 2 * k
    else:
        return 2 * k + 1
    
def main():
    data = sys.stdin.read().strip().split()
    if not data:
        return
    nums = list(map(int, data))
    if len(nums) >= 3 and 1 + 2 * nums[0] == len(nums):
        t = nums[0]
        flat = nums[1:]
    else:
        t = len(nums) // 2
        flat = nums[:2*t]
        
    out = []
    for i in range(t):
        x = flat[2*i]
        y = flat[2*i+1]
        d = y - x
        out.append(str(steps_for_distance(d)))
        
    sys.stdout.write("\n".join(out))
    
if __name__ == "__main__":
    main()