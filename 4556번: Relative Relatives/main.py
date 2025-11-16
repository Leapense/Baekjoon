import sys
from collections import defaultdict, deque

def solve():
    input = sys.stdin.readline
    t_line = input().strip()
    if not t_line:
        return
    t = int(t_line)
    for dataset_idx in range(1, t + 1):
        X_line = input().strip()
        while X_line == "":
            X_line = input().strip()
        X = int(X_line)
        
        children = defaultdict(list)
        descendants = []
        
        for _ in range(X):
            parts = input().split()
            father = parts[0]
            child = parts[1]
            fage = int(parts[2])
            
            children[father].append((child, fage))
            descendants.append(child)
            
        ages = {}
        ages["Ted"] = 100
        
        q = deque()
        q.append("Ted")
        
        while q:
            father = q.popleft()
            if father not in children:
                continue
            for child, fage in children[father]:
                if child in ages:
                    continue
                ages[child] = ages[father] - fage
                q.append(child)
        unique_desc = list(set(descendants))
        unique_desc.sort(key=lambda name: (-ages[name], name))
        
        print(f"DATASET {dataset_idx}")
        for name in unique_desc:
            print(f"{name} {ages[name]}")
            
if __name__ == "__main__":
    solve()  