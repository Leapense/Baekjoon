import sys

def solve() -> None:
    data = sys.stdin.read().split()
    it = iter(data)
    n = int(next(it))
    names = []
    prices = []
    index = {}
    
    for i in range(n):
        name = next(it)
        price = int(next(it))
        names.append(name)
        prices.append(price)
        index[name] = i
        
    counts = [0] * n
    
    m = int(next(it))
    for _ in range(m):
        gift_name = next(it)
        counts[index[gift_name]] += 1
        
    original_total = 0
    for i in range(n):
        original_total += counts[i] * prices[i]
        
    best_reduction = 0
    
    for i in range(n):
        for j in range(i + 1, n):
            reduction = (counts[i] - counts[j]) * (prices[i] - prices[j])
            if reduction > best_reduction:
                best_reduction = reduction
                
    print(original_total - best_reduction, end='')
    
if __name__ == "__main__":
    solve()