N = int(input().strip())
if N == 1:
    print(1) # it is always sorted.
else:
    A = list(map(int, input().strip().split()))
    for i in range(len(A) - 1):
        # 반복문 도는 중, 현재 원소값과 이전 원소값이 중복될 경우, 반복을 중단하고, 0으로 출력한다.
        if A[i] == A[i + 1]:
            print(0)
            exit()
    print(1)