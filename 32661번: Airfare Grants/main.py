import sys

def main():
    data = sys.stdin.read().strip().split()
    n = int(data[0])
    prices = list(map(int, data[1:1+n]))
    min_p = min(prices)
    max_p = max(prices)
    limit = max_p // 2
    net_cost = max(0, min_p - limit)
    print(net_cost)
    
if __name__ == "__main__":
    main()