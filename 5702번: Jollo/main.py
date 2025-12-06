import sys
from itertools import permutations

def solve():
    input_data = sys.stdin.read().split()
    if not input_data:
        return
    
    iterator = iter(input_data)
    
    while True:
        try:
            chunk = []
            for _ in range(5):
                chunk.append(int(next(iterator)))
                
            if all(x == 0 for x in chunk):
                break
            
            princess = chunk[:3]
            prince_initial = chunk[3:]
            used_cards = set(princess + prince_initial)
            solution_found = False
            for card in range(1, 53):
                if card in used_cards:
                    continue
                
                prince_hand = prince_initial + [card]
                guaranteed_win = True
                
                for p_perm in permutations(princess):
                    wins = 0
                    if prince_hand[0] > p_perm[0]: wins += 1
                    if prince_hand[1] > p_perm[1]: wins += 1
                    if prince_hand[2] > p_perm[2]: wins += 1
                    
                    if wins < 2:
                        guaranteed_win = False
                        break
                    
                if guaranteed_win:
                    print(card)
                    solution_found = True
                    break
            if not solution_found:
                print(-1)
        except StopIteration:
            break
        
if __name__ == "__main__":
    solve()