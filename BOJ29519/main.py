import sys
from typing import List

def can_reach_end(n: int, nodes: List[int]) -> bool:
    if n < 1 or (n & (n - 1) != 0):
        return False
    
    current_pos = 1
    while current_pos <= n:
        if nodes[current_pos - 1] == 0:
            return False
        if current_pos == n:
            return True
        current_pos *= 2
        
    return False

def main():
    input_data = sys.stdin.read().split()
    if not input_data:
        return
    
    try:
        n = int(input_data[0])
        nodes = list(map(int, input_data[1:n+1]))
        if can_reach_end(n, nodes):
            print("Yes")
        else:
            print("No")
    except (ValueError, IndexError):
        return
    
if __name__ == "__main__":
    main()