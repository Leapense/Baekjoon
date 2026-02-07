def is_dark(hour, H, R, T):
    local = hour % H
    if R < T:
        return not (R < local < T)
    else:
        return T <= local <= R
    
def main():
    N = int(input())
    settlements = []
    max_H = 0
    
    for _ in range(N):
        H, R, T = map(int, input().split())
        settlements.append((H, R, T))
        max_H = max(max_H, H)
        
    max_hours = 1825 * max_H
    for hour in range(max_hours + 1):
        if all(is_dark(hour, H, R, T) for H, R, T in settlements):
            print(hour)
            return
    print("impossible")
    
main()