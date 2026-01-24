import sys

def classify(name: str) -> str:
    if "rest" in name:
        return "rest"
    if "leg" in name:
        return "leg"
    return "arm"

def main():
    input = sys.stdin.readline
    n_line = input().strip()
    if not n_line:
        return
    n = int(n_line)
    exercises = [input().strip() for _ in range(n)]
    glyph = {
        "leg": "🦵",
        "arm": "💪",
        "rest": "😴",
    }

    days = []
    for d in range(31):
        t = classify(exercises[d % n])
        days.append(glyph[t])

    week_ranges = [(0, 7), (7, 14), (14, 21), (21, 28), (28, 31)]
    out_lines = []
    for i, (s, e) in enumerate(week_ranges, start=1):
        out_lines.append(f"{i} {''.join(days[s:e])}")
    
    sys.stdout.write("\n".join(out_lines))

if __name__ == "__main__":
    main()