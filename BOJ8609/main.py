import sys

def solve():
    input = sys.stdin.buffer.readline
    n = int(input())
    queries = list(map(int, input().split()))
    article = input().rstrip(b"\r\n")
    
    spaces = 0
    numbers = 0
    words = 0
    sentences = 0
    palindromes = 0
    
    has_word_since_last_dot = False
    s = article
    m = len(s)
    i = 0
    
    while i < m:
        c = s[i]
        if c == 32:
            spaces += 1
            i += 1
            continue
        if 48 <= c <= 57:
            numbers += 1
            i += 1
            while i < m and 48 <= s[i] <= 57:
                i += 1
            continue
        if (65 <= c <= 90) or (97 <= c <= 122):
            start = i
            i += 1
            while i < m and ((65 <= s[i] <= 90) or (97 <= s[i] <= 122)):
                i += 1
            end = i - 1
            words += 1
            has_word_since_last_dot = True
            l, r = start, end
            is_pal = True
            while l < r:
                if (s[l] | 32) != (s[r] | 32):
                    is_pal = False
                    break
                l += 1
                r -= 1
            if is_pal:
                palindromes += 1
            
            continue
        
        if c == 46:
            if has_word_since_last_dot:
                sentences += 1
            has_word_since_last_dot = False
            
        i += 1
        
    stats = [spaces, numbers, words, sentences, palindromes]
    print(' '.join(str(stats[q - 1]) for q in queries))
    
if __name__ == "__main__":
    solve()