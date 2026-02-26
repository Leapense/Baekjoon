import sys

def input_generator():
    for line in sys.stdin:
        for token in line.split():
            if not token:
                continue
            try:
                yield int(token)
            except ValueError:
                continue
            
def main():
    iterator = input_generator()
    try:
        n = next(iterator)
    except StopIteration:
        return
    
    if n < 1:
        return
    
    count = 1
    cur_len = 1
    max_len = 1
    
    try:
        prev_val = next(iterator)
    except StopIteration:
        return
    
    for _ in range(n - 1):
        try:
            curr_val = next(iterator)
        except StopIteration:
            break
        
        if curr_val >= prev_val:
            cur_len += 1
        else:
            count += 1
            if cur_len > max_len:
                max_len = cur_len
            cur_len = 1
        
        prev_val = curr_val
        
    if cur_len > max_len:
        max_len = cur_len
        
    sys.stdout.write(f"{count} {max_len}")
    
if __name__ == "__main__":
    try:
        main()
    except KeyboardInterrupt:
        sys.exit(0)