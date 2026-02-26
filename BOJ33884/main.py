import sys

def input_generator():
    """
    Generator that yields tokens from stdin one by one.
    Prevents loading the entire file into memory.
    """
    for line in sys.stdin:
        for token in line.split():
            yield token
            
def main():
    iterator = input_generator()
    try:
        try:
            n_str = next(iterator)
            n = int(n_str)
        except StopIteration:
            print("Error: Empty input.", file=sys.stderr)
            sys.exit(1)
        except ValueError:
            print("Error: Invalid format for 'n'.", file=sys.stderr)
            sys.exit(1)
            
        if n < 0 or n > 10 ** 7:
            print("Error: 'n' is out of acceptable bounds.", file=sys.stderr)
            sys.exit(1)
            
        INF = 10**30
        
        min1x, min1y = INF, INF
        for _ in range(n):
            try:
                x = int(next(iterator))
                y = int(next(iterator))
                if x < min1x or (x == min1x and y < min1y):
                    min1x, min1y = x, y
            except StopIteration:
                print("Error: Unexpected end of input in first set.", file=sys.stderr)
                sys.exit(1)
            except ValueError:
                print("Error: Non-integer data found.", file=sys.stderr)
                sys.exit(1)
                
        min2x, min2y = INF, INF
        for _ in range(n):
            try:
                x = int(next(iterator))
                y = int(next(iterator))
                if x < min2x or (x == min2x and y < min2y):
                    min2x, min2y = x, y
            except StopIteration:
                print("Error: Unexpected end of input in second set.", file=sys.stderr)
                sys.exit(1)
            except ValueError:
                print("Error: Non-integer data found.", file=sys.stderr)
                sys.exit(1)
                
        A = min2x - min1x
        B = min2y - min1y
        print(A, B)
    except Exception as e:
        print(f"An unexpected error occurred: {e}", file=sys.stderr)
        sys.exit(1)

if __name__ == "__main__":
    main()