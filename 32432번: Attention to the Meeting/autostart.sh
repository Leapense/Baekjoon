#!/usr/bin/env bash
shopt -s nullglob

###############################################################################
# 실행 커맨드 설정 (원하는 언어에 맞게 하나만 선택)
###############################################################################
# C/C++ (컴파일된 바이너리):
RUN_CMD=(./main)

# Python:
# RUN_CMD=(python3 main.py)

# Java (예: Main 클래스):
# RUN_CMD=(java Main)

###############################################################################
# 기대 출력 파일 매핑 규칙
# 1) input_file이 "foo.in" 이면 기본적으로 "foo.out"을 찾음
# 2) 없으면 foo 끝의 숫자를 추출해서 "output<숫자>.out"을 찾음 (예: input1.in -> output1.out)
###############################################################################
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

###############################################################################
# time(1) 설정: GNU time이면 %M(최대 RSS, KB) 사용
# macOS/BSD time이면 -l 출력에서 maximum resident set size(bytes) 파싱
###############################################################################
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
    echo "File: $input_file | SKIP (expected output not found)"
    ((skipped++))
    continue
  fi

  out_tmp="$(mktemp)"
  mem_tmp="$(mktemp)"

  start_ns="$(date +%s%N)"

  if [[ "$HAS_GNU_TIME" -eq 1 ]]; then
    # GNU time: 최대 RSS(KB)를 mem_tmp에 기록
    "$TIME_CMD" -f "%M" -o "$mem_tmp" "${RUN_CMD[@]}" < "$input_file" > "$out_tmp" || true
    mem_kb="$(cat "$mem_tmp" 2>/dev/null || true)"
    mem_mb=""
    if [[ -n "${mem_kb:-}" ]]; then
      mem_mb="$(( mem_kb / 1024 ))"
    fi
  else
    # macOS/BSD time: -l 의 stderr를 mem_tmp에 저장 후 파싱 (bytes)
    "$TIME_CMD" -l "${RUN_CMD[@]}" < "$input_file" > "$out_tmp" 2> "$mem_tmp" || true
    # "maximum resident set size" 라인을 찾아 숫자(bytes) 추출
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
    # 실패 시 diff를 파일로 저장 (원하면 주석 처리 가능)
    diff -u "$expected_file" "$out_tmp" > "diff_${base}.patch" || true
    diff_info=" (see diff_${base}.patch)"
  fi

  if [[ -n "${mem_kb:-}" ]]; then
    echo "File: $input_file | $status | ${duration_ms} ms | MaxRSS: ${mem_kb} KB (${mem_mb} MB)${diff_info}"
  else
    echo "File: $input_file | $status | ${duration_ms} ms | MaxRSS: N/A${diff_info}"
  fi

  rm -f "$out_tmp" "$mem_tmp"
done

# 퍼센트 계산 (소수점 2자리)
# SKIP은 전체에서 제외할지 포함할지 애매하니, 여기서는 "실제로 비교한 케이스" 기준으로 계산합니다.
compared=$(( total - skipped ))
if [[ "$compared" -le 0 ]]; then
  echo "No comparable testcases (missing expected outputs)."
  exit 0
fi

percent="$(awk -v a="$accepted" -v t="$compared" 'BEGIN { printf "%.2f", (a*100.0)/t }')"
echo "Summary: ${accepted}/${compared} accepted (${percent}%).  (Skipped: ${skipped})"
