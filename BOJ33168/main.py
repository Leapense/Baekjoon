import sys

def main():
    input_data = sys.stdin.read().split()
    data_iter = iter(input_data)
    N = int(next(data_iter))
    arr = [int(x) for x in data_iter]
    
    for _ in range(N - 1):
        next_arr = [arr[i] + arr[i + 1] for i in range(len(arr) - 1)]
        sys.stdout.write(" ".join(map(str, next_arr)) + '\n')
        arr = next_arr
        
if __name__ == "__main__":
    main()