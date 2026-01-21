import sys

def main():
    data = sys.stdin.read().split()
    it = iter(data)
    
    G = int(next(it))
    N = int(next(it))
    
    sets = []
    nikola_first_set_wins = 0
    josip_set_wins = 0
    
    for si in range(N):
        X = int(next(it))
        games = []
        n_cnt = 0
        j_cnt = 0
        for _ in range(X):
            w = int(next(it))
            games.append(w)
            if w == 1:
                n_cnt += 1
            else:
                j_cnt += 1
        sets.append(games)
        if si == 0:
            nikola_first_set_wins = n_cnt
        
        if j_cnt == G:
            josip_set_wins += 1
        
    best_g1 = 1
    best_wins = -1
    
    for g1 in range(1, G):
        nikola_wins = 0
        for games in sets:
            n = 0
            j = 0
            for w in games:
                if w == 1:
                    n += 1
                else:
                    j += 1
                if n == g1 or j == g1:
                    break
            if n == g1:
                nikola_wins += 1
        if nikola_wins > best_wins or (nikola_wins == best_wins and g1 < best_g1):
            best_wins = nikola_wins
            best_g1 = g1
    print(nikola_first_set_wins)
    print(josip_set_wins)
    print(best_g1)

if __name__ == "__main__":
    main()