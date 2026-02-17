import sys

MAX_N = 1_000_000

def main():
    try:
        input_data = sys.stdin.read().split()
        if len(input_data) < 2:
            return
        
        n_raw, d_raw = input_data[0], input_data[1]
        n = int(n_raw)
        d = int(d_raw)
        if n <= 0:
            return
        if n > MAX_N:
            sys.stderr.write(f"Error: N exceeds maximum limit of {MAX_N}\n")
            sys.exit(1)
            
        def generate_sequence(n, d):
            if n % 2 == 1:
                yield str(d)
                k = (n - 1) // 2
                for i in range(1, k + 1):
                    yield str(d - i)
                    yield str(d + i)
            else:
                k = n // 2
                for i in range(1, k + 1):
                    yield str(d - i)
                    yield str(d + i)
                    
        first = True
        for val in generate_sequence(n, d):
            if not first:
                sys.stdout.write(" ")
            sys.stdout.write(val)
            first = False
            
        sys.stdout.write("\n")
    except (ValueError, IndexError):
        sys.stderr.write("Error: Invalid input format. Expected two integers.\n")
        sys.exit(1)
    except EOFError:
        pass
    
if __name__ == "__main__":
    main()