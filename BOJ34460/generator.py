import random
import re
import csv

# 문제 제약 조건
MAX_T = 1000
MAX_TOTAL_LEN = 200000

def is_yes(s):
    """
    정규표현식을 사용하여 문자열이 'M(IT)+'의 반복으로 이루어져 있는지 정확히 판별합니다.
    이 함수가 실제 문제의 정답(Expected Output)을 구하는 기준이 됩니다.
    """
    return bool(re.fullmatch(r'(M(IT)+)+', s))

def generate_exact_yes(target_length):
    """지정된 길이의 정확히 조건에 맞는(YES) 문자열을 생성합니다."""
    # 길이가 1, 2, 4인 경우에는 M(IT)+ 조합을 만들 수 없음
    if target_length in (1, 2, 4):
        return None
    
    chunks = []
    rem = target_length
    
    # 남은 길이를 3 이상의 홀수들의 합으로 분할
    while rem > 0:
        if rem == 3:
            chunks.append(3)
            rem = 0
        elif rem == 5:
            chunks.append(5)
            rem = 0
        elif rem == 6:
            chunks.extend([3, 3])
            rem = 0
        elif rem == 7:
            chunks.append(7)
            rem = 0
        else:
            # 남은 길이(rem)에서 선택했을 때, 남게 되는 길이가 1, 2, 4가 되지 않는 홀수 선택
            valid_choices = [x for x in range(3, rem + 1, 2) if (rem - x) not in (1, 2, 4)]
            if not valid_choices:
                # 안전장치 (실제로는 발생하지 않음)
                valid_choices = [3] 
            choice = random.choice(valid_choices)
            chunks.append(choice)
            rem -= choice
            
    # 분할된 길이를 바탕으로 M + IT 반복 문자열 생성
    s = ""
    for c in chunks:
        k = (c - 1) // 2
        s += "M" + "IT" * k
    return s

def generate_exact_no(target_length):
    """지정된 길이의 조건에 맞지 않는(NO) 문자열을 생성합니다."""
    while True:
        r = random.random()
        if r < 0.4 and target_length not in (1, 2, 4):
            # 1. 함정 케이스: 정답 문자열에서 딱 1글자만 무작위 알파벳으로 변경
            s = generate_exact_yes(target_length)
            idx = random.randint(0, target_length - 1)
            s_list = list(s)
            s_list[idx] = random.choice("ABCDEFGHIJKLMNOPQRSTUVWXYZ")
            s = "".join(s_list)
        elif r < 0.7:
            # 2. 함정 케이스: M, I, T 로만 이루어진 무작위 문자열
            s = "".join(random.choices(["M", "I", "T"], k=target_length))
        else:
            # 3. 완전 무작위 문자열
            s = "".join(random.choices("ABCDEFGHIJKLMNOPQRSTUVWXYZ", k=target_length))
        
        # 우연히 정답(YES)이 만들어진 경우가 아니라면 반환
        if not is_yes(s):
            return s

def generate_test_cases():
    test_cases = []
    total_len = 0
    t_count = 0
    
    # 1. 문제에 주어진 예제 우선 추가
    examples = [
        "MITIT", "MITI", "MITITMITMITITIT", 
        "MITITM", "MITBEAVER", "MIIIT"
    ]
    for ex in examples:
        test_cases.append((len(ex), ex, "YES" if is_yes(ex) else "NO"))
        total_len += len(ex)
        t_count += 1

    # 2. 나머지 테스트 케이스 무작위 생성 (제약 조건 내에서)
    while t_count < MAX_T and total_len < MAX_TOTAL_LEN:
        remaining_len = MAX_TOTAL_LEN - total_len
        if remaining_len < 3:
            break
            
        # 다양한 길이의 테스트 케이스 생성 (가끔 아주 긴 문자열도 생성)
        if random.random() < 0.05:
            length = random.randint(1000, min(20000, remaining_len))
        else:
            length = random.randint(3, min(200, remaining_len))
            
        # YES를 만들지 NO를 만들지 무작위 결정
        is_target_yes = random.choice([True, False])
        
        if is_target_yes:
            s = generate_exact_yes(length)
            if not s:  # 길이 4 등 YES 생성이 불가능한 길이면 NO로 대체
                s = generate_exact_no(length)
        else:
            s = generate_exact_no(length)
            
        ans = "YES" if is_yes(s) else "NO"
        test_cases.append((len(s), s, ans))
        total_len += len(s)
        t_count += 1

    return t_count, test_cases

def save_to_csv(filename, t_count, test_cases):
    """생성된 테스트 케이스를 CSV 파일로 저장"""
    with open(filename, mode='w', newline='', encoding='utf-8') as file:
        writer = csv.writer(file)
        
        # CSV 헤더 작성
        writer.writerow(["Test_Case_ID", "String_Length", "Input_String", "Expected_Output"])
        
        # 데이터 작성
        for i, (length, s, ans) in enumerate(test_cases, 1):
            writer.writerow([i, length, s, ans])
            
    print(f"✅ 총 {t_count}개의 테스트 케이스가 '{filename}'에 성공적으로 저장되었습니다.")
    print(f"✅ 테스트 케이스들의 총 문자열 길이 합: {sum(x[0] for x in test_cases)}")

if __name__ == "__main__":
    t_count, test_cases = generate_test_cases()
    save_to_csv("mit_testcases.csv", t_count, test_cases)