import sys 

input = sys.stdin.readline

n = int(input().strip())
events = input().split()
L = int(input().strip())

D = 1

for event in events:
    if event == "COIN":
        D *= 2
    elif event == "DIE":
        D *= 6
    elif event == "CARDS":
        D *= 52
        
W = L * (D - 1)
print(W,end='')