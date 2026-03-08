import sys
from typing import Iterator

def iter_valid_rotations(digits: str) -> Iterator[str]:
    n = len(digits)
    doubled = digits + digits
    
    for start, ch in enumerate(digits):
        if ch != "0":
            yield doubled[start:start + n]
            
def select_rotation(digits: str, *, prefer_max: bool) -> str:
    selector = max if prefer_max else min
    try:
        return selector(iter_valid_rotations(digits))
    except ValueError as exc:
        raise ValueError("No valid rotation exists without a leading zero.") from exc
    
def compute_difference(s: str, t: str) -> int:
    max_s = select_rotation(s, prefer_max=True)
    min_t = select_rotation(t, prefer_max=False)
    
    return int(max_s) - int(min_t)

def main() -> None:
    s = sys.stdin.readline().strip()
    t = sys.stdin.readline().strip()
    
    print(compute_difference(s, t))
    
if __name__ == "__main__":
    main()