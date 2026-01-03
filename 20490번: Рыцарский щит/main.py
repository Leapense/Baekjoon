import sys

def main():
    data = sys.stdin.read().strip().split()
    a1, b1, c1, a2, b2, c2 = map(int, data)
    
    s1 = [a1, b1, c1]
    s2 = [a2, b2, c2]
    
    total_perimeter = sum(s1) + sum(s2)
    best_overlap = max(min(x, y) for x in s1 for y in s2)
    
    print(total_perimeter - 2 * best_overlap)
    
if __name__ == "__main__":
    main()