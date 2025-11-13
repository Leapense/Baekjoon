import sys

PANGRAM = "the quick brown fox jumps over the lazy dog"

def find_mapping(lines):
    for line in lines:
        if len(line) != len(PANGRAM):
            continue
        
        mapping = {}
        reverse = {}
        ok = True
        
        for c_enc, c_plain in zip(line, PANGRAM):
            if c_plain == ' ':
                if c_enc != ' ':
                    ok = False
                    break
            else:
                if c_enc == ' ':
                    ok = False
                    break
                
                if c_enc in mapping:
                    if mapping[c_enc] != c_plain:
                        ok = False
                        break
                else:
                    if c_plain in reverse and reverse[c_plain] != c_enc:
                        ok = False
                        break
                    mapping[c_enc] = c_plain
                    reverse[c_plain] = c_enc
                    
        if ok and len(mapping) == 26:
            return mapping
        
    return None

def decode_line(line, mapping):
    result_chars = []
    for c in line:
        if c == ' ':
            result_chars.append(' ')
        else:
            result_chars.append(mapping.get(c, c))
    return ''.join(result_chars)

def read_case(stream):
    lines = []
    for line in stream:
        if line == '\n':
            break
        lines.append(line.rstrip('\n'))
    return lines

def main():
    first_line = sys.stdin.readline()
    if not first_line:
        return
    
    first_stripped = first_line.strip()
    if first_stripped.isdigit():
        t = int(first_stripped)
        _ = sys.stdin.readline()
        outputs = []
        
        for case in range(t):
            lines = read_case(sys.stdin)
            mapping = find_mapping(lines)
            if mapping is None:
                outputs.append("No solution.")
            else:
                for line in lines:
                    outputs.append(decode_line(line, mapping))
                    
            if case != t - 1:
                outputs.append("")
        sys.stdout.write('\n'.join(outputs))
        
    else:
        lines = [first_line.rstrip('\n')]
        for line in sys.stdin:
            lines.append(line.rstrip('\n'))
            
        mapping = find_mapping(lines)
        
        if mapping is None:
            print("No solution.")
        else:
            for line in lines:
                print(decode_line(line, mapping))
                
if __name__ == "__main__":
    main()