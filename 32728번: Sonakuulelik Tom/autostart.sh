#!/usr/bin/env bash
shopt -s nullglob

RUN_CMD=(java Main)

find_expected() {
    local input="$1"
    local base="${input%.in}"

    if [[ -f "${base}.out" ]]; then
        echo "${base}.out"
        return 0
    fi

    if [[ "$base" =~ ([0-9]+)$ ]]; then
        local n="${BASH_REMATCH[1]}"
        if [[ -f "output${n}.out" ]]; then
            echo "output${n}.out"
            return 0
        fi
    fi
    return 1
}

TIME_CMD="/usr/bin/time"
HAS_GNU_TIME=0
if "$TIME_CMD" --version >/dev/null 2>&1; then
    HAS_GNU_TIME=1
fi

accepted=0
total=0
skipped=0

for input_file in *.in; do
    ((total++))
    base="${input_file%.in}"
    if ! expected_file="$(find_expected "$input_file")"; then
        echo "파일: $input_file | 스킵 (기대 출력 파일 없음)"
        ((skipped++))
        continue
    fi

    out_tmp="$(mktemp)"
    mem_tmp="$(mktemp)"
    start_ns="$(date +%s%N)"

    if [[ "$HAS_GNU_TIME" -eq 1 ]]; then
        "$TIME_CMD" -f "%M" -o "$mem_tmp" "${RUN_CMD[@]}" < "$input_file" > "$out_tmp" || true
        mem_kb="$(cat "$mem_tmp" 2>/dev/null || true)"
        mem_mb=""
        if [[ -n "${mem_kb:-}" ]]; then
            mem_mb="$(( mem_kb / 1024 ))"
        fi
    else
        "$TIME_CMD" -l "${RUN_CMD[@]}" < "$input_file" > "$out_tmp" 2> "$mem_tmp" || true
        mem_bytes="$(awk '/maximum resident set size/ {print $1; exit}' "$mem_tmp" 2>/dev/null || true)"
        mem_kb=""
        mem_mb=""
        if [[ -n "${mem_bytes:-}" ]]; then
            mem_kb="$(( mem_bytes / 1024 ))"
            mem_mb="$(( mem_kb / 1024 ))"
        fi
    fi

    end_ns="$(date +%s%N)"
    duration_ms="$(( (end_ns - start_ns) / 1000000 ))"

    if diff -q "$out_tmp" "$expected_file" >/dev/null 2>&1; then
        ((accepted++))
        status="AC"
        diff_info=""
    else
        status="WA"
        diff -u "$expected_file" "$out_tmp" > "diff_${base}.patch" || true
        diff_info=" (see diff_${base}.patch)"
    fi

    if [[ -n "${mem_kb:-}" ]]; then
        echo "파일: $input_file | $status | ${duration_ms} ms | MaxRSS: ${mem_kb} KB (${mem_mb} MB)${diff_info}"
    else
        echo "파일: $input_file | $status | ${duration_ms} ms | MaxRSS: N/A${diff_info}"
    fi

    rm -f "$out_tmp" "$mem_tmp"
done

compared=$(( total - skipped ))
if [[ "$compared" -le 0 ]]; then
    echo "비교할 수 있는 테스트 케이스가 존재하지 않습니다 (기대 출력 파일 없음)"
    exit 0
fi

percent="$(awk -v a="$accepted" -v t="$compared" 'BEGIN { printf "%.2f", (a*100.0)/t }')"
echo "Summary: ${accepted}/${compared} accepted (${percent}%). (Skipped: ${skipped})"
