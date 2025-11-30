import sys

def solve():
    input = sys.stdin.readline
    K_line = input().strip()
    while K_line == "":
        K_line = input().strip()
        
    K = int(K_line)
    outputs = []
    
    for tc in range(1, K + 1):
        while True:
            line = input()
            if not line:
                return
            line = line.strip()
            if line != "":
                break
            
        M, N = map(int, line.split())
        problem_masks = []
        for _ in range(N):
            line = input().strip()
            while line == "":
                line = input().strip()
                
            nums = list(map(int, line.split()))
            mask = 0
            for a in nums:
                mask |= 1 << (a - 1)
            problem_masks.append(mask)
            
        full_mask = (1 << M) - 1
        suffix = [0] * (N + 1)
        for i in range(N - 1, -1, -1):
            suffix[i] = suffix[i + 1] | problem_masks[i]
            
        ans_indices = None
        
        def dfs(pos, cnt, mask, chosen, target_size):
            nonlocal ans_indices
            
            if ans_indices is not None:
                return True
            
            if cnt == target_size:
                if mask == full_mask:
                    ans_indices = chosen.copy()
                    return True
                return False
            
            if pos == N:
                return False
            
            remaining_slots = target_size - cnt
            remaining_probs = N - pos
            if remaining_probs < remaining_slots:
                return False
            
            if (mask | suffix[pos]) != full_mask:
                return False
            
            chosen.append(pos)
            if dfs(pos + 1, cnt + 1, mask | problem_masks[pos], chosen, target_size):
                return True
            chosen.pop()
            
            if dfs(pos + 1, cnt, mask, chosen, target_size):
                return True
            
            return False
        
        for size in range(1, N + 1):
            ans_indices = None
            dfs(0, 0, 0, [], size)
            if ans_indices is not None:
                break
            
        letters = [chr(ord('A') + idx) for idx in ans_indices]
        outputs.append(f"Data Set {tc}: " + " ".join(letters))
        
    for i, line in enumerate(outputs):
        if i > 0:
            print()
        print(line)
        
if __name__ == "__main__":
    solve()