import sys

def to_meter_cm(length_cm: int):
    return length_cm // 100, length_cm % 100

def main():
    input = sys.stdin.readline
    x, y = map(int, input().split())
    d = int(input().strip())
    P = 100 * x + y
    A = (P + 2 * d) // 4
    B = (P - 2 * d) // 4
    am, ac = to_meter_cm(A)
    bm, bc = to_meter_cm(B)
    print(am, ac)
    print(bm, bc)
    
if __name__ == "__main__":
    main()