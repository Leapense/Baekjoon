import sys

def main():
    V = 999
    E = 1501
    a = 60
    b = 61
    
    out = []
    out.append(f"{V} {E}\n")
    for _ in range(E):
        if b > 61:
            b = a
            a -= 1
        out.append(f"{a} {b}\n")
        b += 1
    sys.stdout.write("".join(out))

if __name__ == "__main__":
    main()