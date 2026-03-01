import sys

def season_index(y: int, m: int) -> int:
    if m == 12:
        y += 1
        s = 0
    elif m <= 2:
        s = 0
    elif m <= 5:
        s = 1
    elif m <= 8:
        s = 2
    else:
        s = 3
    return y * 4 + s

def main():
    A, B, C = map(int, sys.stdin.readline().split())
    debut = season_index(2015, 1)
    today = season_index(A, B)
    print(today - debut + 1)
    
if __name__ == "__main__":
    main()