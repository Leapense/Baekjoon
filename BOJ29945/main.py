import sys

def matches(topic: str, pattern: str) -> bool:
    if len(topic) != len(pattern):
        return False
    for tc, pc in zip(topic, pattern):
        if pc != '*' and pc != tc:
            return False
    return True

def main():
    input = sys.stdin.readline
    n_line = input().strip()
    if not n_line:
        return
    n = int(n_line)

    topics = [input().strip() for _ in range(n)]
    pattern = input().strip()
    
    ans = [t for t in topics if matches(t, pattern)]
    out = [str(len(ans))]
    out.extend(ans)
    sys.stdout.write("\n".join(out))

if __name__ == "__main__":
    main()