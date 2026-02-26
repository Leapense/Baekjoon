import sys

MAX_N = 10000
MAX_T = 1000

def solve():
    input_stream = sys.stdin.read().split()
    if not input_stream:
        return
    
    iterator = iter(input_stream)
    try:
        try:
            t_str = next(iterator)
            t = int(t_str)
        except StopIteration:
            return
        
        if t < 0 or t > MAX_T:
            sys.stderr.write(f"Error: t exceeds limit of {MAX_T}\n")
            return
        
        for _ in range(t):
            try:
                n_str = next(iterator)
                n = int(n_str)
            except StopIteration:
                break
            
            if n < 0 or n > MAX_N:
                sys.stderr.write("Error: Input n out of bounds\n")
                continue
            
            if n % 2 == 1:
                sys.stdout.write("0\n")
            else:
                val = 1 << (n // 2)
                sys.stdout.write(f"{val}\n")
            
    except ValueError:
        sys.stderr.write("Error: Invalid integer input\n")
    except Exception as e:
        sys.stderr.write(f"Error: {str(e)}\n")
        
if __name__ == "__main__":
    solve()