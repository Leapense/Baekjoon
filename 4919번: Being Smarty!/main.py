import sys
import shlex

def main():
    case_no = 1
    out_lines = []
    for line in sys.stdin:
        line = line.strip()
        if not line:
            continue
        if line == "0":
            break
        
        parts = shlex.split(line)
        R = int(parts[0])
        N = int(parts[1])
        P1 = parts[2]
        P2 = parts[3]
        
        block_idx = (R - 1) // N
        
        if block_idx % 2 == 0:
            chosen = P1
        else:
            chosen = P2
            
        result = chosen.lower()
        out_lines.append(f"{case_no}. {result}")
        case_no += 1
        
    sys.stdout.write("\n".join(out_lines))
    
if __name__ == "__main__":
    main()
            
        