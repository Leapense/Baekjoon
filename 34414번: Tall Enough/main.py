def main():
    n = int(input().strip())
    for _ in range(n):
        h = int(input().strip())
        if h < 48:
            print("False")
            return
            
    print("True")
    
if __name__ == "__main__":
    main()