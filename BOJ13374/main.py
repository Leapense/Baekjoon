import sys

MAX_TEST_CASES = 10000
MAX_QUERIES = 100000
MAX_N = 10**9
    
def value_at(n: int, r: int, c: int) -> int:
    t = min(r, c - r, n - 1 - c)
    m = n - 3 * t
    pre = n * (n + 1) // 2 - m * (m + 1) // 2
    if r == t:
        offset = c - 2 * t
    elif c == n - 1 - t:
        offset = m + (r - t - 1)
    else:
        offset = (2 * m - 1) + (n - 2 - 2 * t - r)
            
    return (pre + offset + 1) % 10
        
def solve() -> None:
    input_func = sys.stdin.readline
    try:
        t_str = input_func().strip()
        if not t_str:
            return
        t = int(t_str)
        
        if t < 0 or t > MAX_TEST_CASES:
            sys.stderr.write(f"Error: Number of test cases must be between 0 and {MAX_TEST_CASES}\n")
            return
            
        for _ in range(t):
            n_q_input = input_func().split()
            if len(n_q_input) != 2:
                sys.stderr.write("Error: Expected 2 space-separated integers for n and q.\n")
                return
                
            n, q = map(int, n_q_input)
            if q < 0 or q > MAX_QUERIES or n < 0 or n > MAX_N:
                sys.stderr.write(f"Error: Inputs n and q exceed maximum allowed bounds.\n")
                return
                
            for _ in range(q):
                r_c_input = input_func().split()
                if len(r_c_input) != 2:
                    sys.stderr.write("Error: Expected 2 space-separated integers for r and c.\n")
                    return
                    
                r, c = map(int, r_c_input)
                
                sys.stdout.write(f"{value_at(n, r, c)}\n")
    except ValueError as e:
        sys.stderr.write(f"Input Format Error: Invalid integer provided. Details: {e}\n")
    except Exception as e:
        sys.stderr.write(f"Unexpected Error: {e}\n")
        
if __name__ == "__main__":
    solve()