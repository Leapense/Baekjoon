import sys

def state(x: int, low: int, high: int) -> int:
    """
    Returns:
    0 -> below expected range
    1 -> lower half of expected range
    2 -> upper half of expected range
    3 -> above expected range
    """
    if x < low:
        return 0
    if x > high:
        return 3
    
    upper_start = (low + high) // 2
    if x >= upper_start:
        return 2
    return 1

def iter_ints():
    """
    Incrementally yields integers from stdin to avoid reading the entire input into memory at once.
    """
    for line in sys.stdin:
        for token in line.split():
            try:
                yield int(token)
            except ValueError:
                raise ValueError(f"Invalid integer token: {token!r}")
            
def classify_record(h: int, w: int, s: int, r: int, p: int) -> str:
    vals = [
        state(h, 190, 220),
        state(w, 200, 250),
        state(s, 10, 20),
        state(r, 2, 6),
        state(p, 3, 7),
    ]
    
    above_count = sum(v == 3 for v in vals)
    upper_or_above_count = sum(v >= 2 for v in vals)
    in_range_or_above_count = sum(v >= 1 for v in vals)
    
    height_or_wingspan_above = vals[0] == 3 or vals[1] == 3
    if above_count >= 3 and height_or_wingspan_above:
        return "0"
    elif (above_count >= 2 and upper_or_above_count >= 3) or (
        in_range_or_above_count == 5 and upper_or_above_count >= 3
    ):
        return "1"
    elif (above_count >= 1 and upper_or_above_count >= 2) or (upper_or_above_count >= 3):
        return "2"
    else:
        return "3"
    
def main() -> int:
    try:
        ints = iter_ints()
        try:
            n = next(ints)
        except StopIteration:
            raise ValueError("Missing record count.")
        
        if n < 0:
            raise ValueError("Record count must be non-negative.")
        
        out = []
        for record_index in range(n):
            fields = []
            for _ in range(5):
                try:
                    fields.append(next(ints))
                except StopIteration:
                    raise ValueError(
                        f"Incomplete input: expected 5 integers for record "
                        f"{record_index + 1}."
                    )
                    
            h, w, s, r, p = fields
            out.append(classify_record(h, w, s, r, p))
            
        try:
            extra = next(ints)
            raise ValueError(f"Unexpected extra input detected: {extra}")
        except StopIteration:
            pass
        
        sys.stdout.write("\n".join(out))
        return 0
    except ValueError as exc:
        sys.stderr.write(f"Input error: {exc}\n")
        return 1
    
if __name__ == "__main__":
    raise SystemExit(main())
    
    