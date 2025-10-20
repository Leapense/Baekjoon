import re
import sys

def is_submarine(sound: str) -> bool:
    pattern = re.compile(r'^(100+1+|01)+$')
    return pattern.fullmatch(sound) is not None

def main():
    s = sys.stdin.readline().strip()
    print("SUBMARINE" if is_submarine(s) else "NOISE")
    
if __name__ == "__main__":
    main()