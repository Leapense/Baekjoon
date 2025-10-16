import sys
from itertools import permutations

def simulate(women_prefs, men_prefs_reported):
    men_rank = {m: {w: r for r, w in enumerate(men_prefs_reported[m])} for m in range(1, 6)}
    partners = {i: 0 for i in range(1, 11)}
    next_try = {w: 0 for w in range(6, 11)}
    while True:
        progressed = False
        for w in range(6, 11):
            if partners[w] != 0:
                continue
            m = women_prefs[w][next_try[w]]
            next_try[w] += 1
            if partners[m] == 0:
                partners[m] = w
                partners[w] = m
            else:
                cur_w = partners[m]
                if men_rank[m][w] < men_rank[m][cur_w]:
                    partners[m] = w
                    partners[w] = m
                    partners[cur_w] = 0
                    
                progressed = True
        if not progressed:
            break
    return partners[1]

def solve_one_case(male_2to5_prefs, female_6to10_prefs):
    women_prefs = {w: female_6to10_prefs[w - 6][:] for w in range(6, 11)}
    base_men_prefs = {
        2: male_2to5_prefs[0][:],
        3: male_2to5_prefs[1][:],
        4: male_2to5_prefs[2][:],
        5: male_2to5_prefs[3][:],
    }
    true_man1 = [6, 7, 8, 9, 10]
    
    def men_prefs_with(man1_reported):
        d = {1: man1_reported[:]}
        d.update(base_men_prefs)
        return d
    origin_partner = simulate(women_prefs, men_prefs_with(true_man1))
    
    for perm in permutations(true_man1):
        p = simulate(women_prefs, men_prefs_with(list(perm)))
        if p < origin_partner:
            return "YES"
    return "NO"

def main():
    it = iter(sys.stdin.read().strip().split())
    T = int(next(it))
    out_lines = []
    for _ in range(T):
        male_2to5 = [[int(next(it)) for _ in range(5)] for _ in range(4)]
        female_6to10 = [[int(next(it)) for _ in range(5)] for _ in range(5)]
        out_lines.append(solve_one_case(male_2to5, female_6to10))
    print("\n".join(out_lines))
    
if __name__ == "__main__":
    main()