N = int(input())
universities = []
for _ in range(N):
    universities.append(input().strip())
    
# find the index of yonsei from universities
yonsei_index = universities.index('yonsei')
korea_index = universities.index('korea')

if yonsei_index < korea_index:
    print('Yonsei Won!')
else:
    print('Yonsei Lost...')