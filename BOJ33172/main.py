import sys

def prefix_function(s: str) -> list[int]:
    """
    Compute the KMP prefix function (pi array) for a given string.
    pi[i] stores the length of the longest proper prefix of s[0..i]
    that is also a suffix of s[0..i].
    """
    n = len(s)
    pi = [0] * n
    j = 0
    for i in range(1, n):
        while j > 0 and s[i] != s[j]:
            j = pi[j - 1]
        if s[i] == s[j]:
            j += 1
        pi[i] = j
    return pi

def main() -> None:
    data = sys.stdin.read().split()
    if len(data) < 2:
        return
    
    s = data[1]
    n = len(s)
    pi = prefix_function(s)
    period = n - pi[-1]
    
    if period < n and n % period == 0:
        print("Yes", end='')
    else:
        print("No", end='')
        
if __name__ == "__main__":
    main()