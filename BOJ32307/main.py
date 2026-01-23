import sys

def main():
    input = sys.stdin.readline
    n_line = input().strip()
    if not n_line:
        return
    n = int(n_line)

    dict_set = set()
    for _ in range(n):
        dict_set.add(input().strip())
    
    m = int(input().strip())
    for _ in range(m):
        w = input().strip()
        if w in dict_set:
            print(1)
            continue

        found_concat = False
        L = len(w)
        for i in range(1, L):
            if w[:i] in dict_set and w[i:] in dict_set:
                found_concat = True
                break

        print(2 if found_concat else 0)

if __name__ == "__main__":
    main()