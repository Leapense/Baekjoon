import sys
from typing import NamedTuple

class StudentRecord(NamedTuple):
    scholarship: int
    cost: int
    name: str
    
def main():
    input_data = sys.stdin.readline
    try:
        n = int(input_data().strip())
    except ValueError:
        return
    
    students = []
    for _ in range(n):
        parts = input_data().split()
        if len(parts) != 4:
            continue
        
        name, score_str, risk_str, cost_str = parts
        score, risk, cost = int(score_str), int(risk_str), int(cost_str)
        
        denominator = cost * (risk + 1)
        scholarship = (score ** 3) // denominator if denominator != 0 else 0
        students.append(StudentRecord(scholarship, cost, name))
        
    students.sort(key=lambda s: (-s.scholarship, s.cost, s.name))
    
    if len(students) >= 2:
        print(students[1].name, end='')
    elif len(students) == 1:
        print(students[0].name, end='')
        
if __name__ == "__main__":
    main()