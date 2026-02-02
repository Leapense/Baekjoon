import sys

def main():
    s = sys.stdin.buffer.readline().strip()
    present = [False] * 26
    for b in s:
        idx = b - 65
        if 0 <= idx < 26:
            present[idx] = True
            
    missing = [chr(i + 65) for i in range(26) if not present[i]]
    if not missing:
        sys.stdout.write("Alphabet Soup!\n")
    else:
        sys.stdout.write("".join(missing) + "\n")
        
if __name__ == "__main__":
    main()