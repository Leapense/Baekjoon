import sys

def lcs_last_row(X, Y):
    m = len(Y)
    dp = [0] * (m + 1)
    for ch in X:
        prev_diag = 0
        for j in range(1, m + 1):
            temp = dp[j]
            if ch == Y[j - 1]:
                dp[j] = prev_diag + 1
            else:
                if dp[j - 1] > dp[j]:
                    dp[j] = dp[j - 1]
            prev_diag = temp
    return dp

def hirschberg_lcs(X, Y):
    n, m = len(X), len(Y)
    if n == 0 or m == 0:
        return ""
    if n == 1:
        ch = X[0]
        return ch if ch in Y else ""

    mid = n // 2
    L1 = lcs_last_row(X[:mid], Y)
    L2 = lcs_last_row(X[mid:][::-1], Y[::-1])

    mlen = len(Y)
    best_k = 0
    best_val = -1
    for k in range(mlen + 1):
        val = L1[k] + L2[mlen - k]
        if val > best_val:
            best_val = val
            best_k = k

    left = hirschberg_lcs(X[:mid], Y[:best_k])
    right = hirschberg_lcs(X[mid:], Y[best_k:])
    return left + right

def main():
    tokens = sys.stdin.read().split()
    out = []
    for i in range(0, len(tokens), 2):
        a = tokens[i]
        b = tokens[i + 1]
        lcs_str = hirschberg_lcs(a, b)
        out.append(str(len(lcs_str)))
    sys.stdout.write("\n".join(out))

if __name__ == "__main__":
    main()
