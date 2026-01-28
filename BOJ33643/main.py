import sys

def main():
    input = sys.stdin.readline
    
    n = int(input().strip())
    grabbed = set()
    for _ in range(n):
        grabbed.add(input().strip())
        
    essentials = ["keys", "phone", "wallet"]
    missing = [item for item in essentials if item not in grabbed]
    
    if not missing:
        print("ready")
    else:
        for item in sorted(missing):
            print(item)
            
if __name__ == "__main__":
    main()