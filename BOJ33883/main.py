import sys


def main():
    s = sys.stdin.readline().strip()
    vowels = set("aeiou")
    vowel_positions = [i for i, ch in enumerate(s) if ch in vowels]
    last_char = s[-1]
    if last_char in vowels or last_char in {"n", "s"}:
        if len(vowel_positions) < 2:
            print(-1)
        else:
            print(vowel_positions[-2] + 1)
    else:
        if len(vowel_positions) < 1:
            print(-1)
        else:
            print(vowel_positions[-1] + 1)


if __name__ == "__main__":
    main()
