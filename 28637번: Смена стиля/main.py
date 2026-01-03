import sys
def main():
    N = int(sys.stdin.readline()) # N: 입력받을 변수명의 개수
    variable_names = []
    for _ in range(N):
        variable_names.append(sys.stdin.readline().strip())
    
    # 입력받은 변수명들을 낙타표기법으로 바꾼다.
    for name in variable_names:
        # 예) camelCase -> camel_case
        new_name = name[0].lower()
        for c in name[1:]:
            if c.isupper():
                new_name += "_" + c.lower()
            else:
                new_name += c
        print(new_name)

if __name__ == "__main__":
    main()