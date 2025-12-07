import sys
from itertools import product
from collections import Counter

def solve():
    input_data = sys.stdin.read().split()
    iterator = iter(input_data)
    try:
        while True:
            N = int(next(iterator))
            L = int(next(iterator))
            K = int(next(iterator))
            
            if N == 0 and L == 0 and K == 0:
                break;
            
            alphabet_str = next(iterator)
            alphabet = list(alphabet_str)
            
            guesses = []
            for _ in range(N):
                g_str = next(iterator)
                e_val = int(next(iterator))
                g_val = int(next(iterator))
                guesses.append((g_str, e_val, g_val))
                
            final_password = None
            all_candidates = product(alphabet, repeat=L)
            possible_flag = False
            
            for candidate_tuple in all_candidates:
                candidate = "".join(candidate_tuple)
                is_valid = True
                for g_str, required_e, required_g in guesses:
                    strike = 0
                    for i in range(L):
                        if candidate[i] == g_str[i]:
                            strike += 1
                    if strike != required_e:
                        is_valid = False
                        break
                    
                    cand_counts = Counter(candidate)
                    guess_counts = Counter(g_str)
                    intersection_count = 0
                    for char in cand_counts:
                        if char in guess_counts:
                            intersection_count += min(cand_counts[char], guess_counts[char])
                    ball = intersection_count - strike
                    if ball != required_g:
                        is_valid = False
                        break
                    
                if is_valid:
                    possible_flag = True
                    if final_password is None:
                        final_password = list(candidate)
                    else:
                        all_question_marks = True
                        for i in range(L):
                            if final_password[i] != '?' and final_password[i] != candidate[i]:
                                final_password[i] = '?'
                            if final_password[i] != '?':
                                all_question_marks = False
                        if all_question_marks:
                            break
            if not possible_flag:
                print("?" * L)
            else:
                print("".join(final_password))
    except StopIteration:
        pass
    
if __name__ == "__main__":
    solve()