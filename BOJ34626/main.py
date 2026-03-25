import sys

def solve():
    input = sys.stdin.readline
    t = int(input().strip())
    answers = []

    for _ in range(t):
        n = int(input().strip())
        arr = list(map(int, input().split()))

        distinct_count = len(set(arr))
        answers.append(str(2 * distinct_count - 1))

    print("\n".join(answers))

if __name__ == "__main__":
    solve()