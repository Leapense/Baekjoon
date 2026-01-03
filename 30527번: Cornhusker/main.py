import sys

def main():
    data = sys.stdin.read().strip().split()
    nums = list(map(int, data))
    
    kernels_sum = 0
    for i in range(0, 10, 2):
        A = nums[i]
        L = nums[i + 1]
        kernels_sum += A * L
            
    N = nums[10]
    KWF = nums[11]

    avg_kernels_per_ear = kernels_sum // 5
    bushels = (avg_kernels_per_ear * N) // KWF
        
    print(bushels)
    
if __name__ == "__main__":
    main()