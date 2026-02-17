import sys
from typing import List, Tuple, Iterator

Point = Tuple[int, int]

def solve_recurrence(n: int, k: int, points: List[Point]) -> Tuple[float, float]:
    sum_x = sum(p[0] for p in points)
    sum_y = sum(p[1] for p in points)
    
    current_sx = float(sum_x)
    current_sy = float(sum_y)
    
    alpha = (n + 1) / n
    
    for i in range(k - 1):
        px, py = points[i]
        current_sx = alpha * current_sx - px
        current_sy = alpha * current_sy - py
        
    return current_sx / n, current_sy / n

def get_input_iterator() -> Iterator[str]:
    input_data = sys.stdin.read().split()
    return iter(input_data)

def main():
    iterator = get_input_iterator()
    try:
        n = int(next(iterator))
        k = int(next(iterator))
        
        points: List[Point] = []
        for _ in range(n):
            x = int(next(iterator))
            y = int(next(iterator))
            points.append((x, y))
            
        ans_x, ans_y = solve_recurrence(n, k, points)
        sys.stdout.write(f"{ans_x:.10f} {ans_y:.10f}\n")
        
    except StopIteration:
        pass
    
if __name__ == "__main__":
    main()