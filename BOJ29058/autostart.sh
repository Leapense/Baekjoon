#!/usr/bin/env bash
shopt -s nullglob

###############################################################################
# 실행 커맨드 설정 (원하는 언어에 맞게 하나만 선택)
###############################################################################
# C/C++ (컴파일된 바이너리):
# RUN_CMD=(./main)

# Python:
# python3 -W ignore -c "import py_compile; py_compile.compile(r'main.py')"
# RUN_CMD=(python3 -W ignore main.py)

# Java (예: Main 클래스):
# javac --release 15 -J-Xms1024m -J-Xmx1920m -J-Xss512m -encoding UTF-8 Main.java
# RUN_CMD=(java -Xms1024m -Xmx1920m -Xss512m -Dfile.encoding=UTF-8 -XX:+UseSerialGC Main)

# node.js
RUN_CMD=(node --stack-size=65536 main.js)

# 색상 사용 여부: stdout이 터미널이고, NO_COLOR가 없을 때만 활성화
USE_COLOR=0
if [[ -t 1 && -z "${NO_COLOR:-}" ]]; then
  USE_COLOR=1
fi

ESC=$'\033'
RESET="${ESC}[0m"

print_status_badge() {
  local status="$1"

  if [[ "$USE_COLOR" -eq 0 ]]; then
    printf "%s" "$status"
    return 0
  fi

  case "$status" in
    AC)   printf "%s" "${ESC}[30;42m AC ${RESET}" ;;  # 검정 글자 + 초록 배경
    WA)   printf "%s" "${ESC}[97;41m WA ${RESET}" ;;  # 흰 글자 + 빨강 배경
    RE)   printf "%s" "${ESC}[97;45m RE ${RESET}" ;;  # 흰 글자 + 자주 배경
    TLE)  printf "%s" "${ESC}[30;43m TLE ${RESET}" ;; # 검정 글자 + 노랑 배경
    MLE)  printf "%s" "${ESC}[97;44m MLE ${RESET}" ;; # 흰 글자 + 파랑 배경
    SKIP) printf "%s" "${ESC}[30;47m SKIP ${RESET}" ;;# 검정 글자 + 회색/흰 배경
    *)    printf "%s" "$status" ;;
  esac
}

print_result_line() {
  local status="$1"
  local extra="${2:-}"
  printf "  result   = "
  print_status_badge "$status"
  [[ -n "$extra" ]] && printf " %s" "$extra"
  printf "\n"
}

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

to_leetcode_value() {
  python3 -c '
import sys, json

text = sys.stdin.read()
text = text.rstrip("\r\n")  # CRLF/ LF 모두 처리

if text == "":
    print("[]")
    raise SystemExit

lines = text.splitlines()

# 단일 라인이면 JSON 파싱 시도
if len(lines) == 1:
    s = lines[0].strip()
    try:
        obj = json.loads(s)
        print(json.dumps(obj, ensure_ascii=False))
        raise SystemExit
    except Exception:
        pass

# 여러 줄이거나 JSON이 아니면 "줄 배열"로 표시
arr = [ln.rstrip("\r\n") for ln in lines]
print(json.dumps(arr, ensure_ascii=False))
'
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
compared=0

for input_file in *.in; do
  ((total++))
  base="${input_file%.in}"

  if ! expected_file="$(find_expected "$input_file")"; then
    echo "Case: $input_file"
    echo "  input    = $(cat "$input_file" | to_leetcode_value)"
    echo "  expected = []"
    echo "  output   = []"
    echo "  result   = SKIP (expected output not found)"
    echo
    ((skipped++))
    continue
  fi

  out_tmp="$(mktemp)"
  mem_tmp="$(mktemp)"

  start_ns="$(date +%s%N)"

# 실행 + 메모리 측정 (stdout은 out_tmp로)
  exit_code=0
  if [[ "$HAS_GNU_TIME" -eq 1 ]]; then
    "$TIME_CMD" -f "%M" -o "$mem_tmp" "${RUN_CMD[@]}" < "$input_file" > "$out_tmp" || exit_code=$?
    mem_kb="$(cat "$mem_tmp" 2>/dev/null || true)"
    mem_mb=""
    [[ -n "${mem_kb:-}" ]] && mem_mb="$(( mem_kb / 1024 ))"
  else
    # macOS/BSD: -l은 stderr로 메모리 정보 출력
    "$TIME_CMD" -l "${RUN_CMD[@]}" < "$input_file" > "$out_tmp" 2> "$mem_tmp" || exit_code=$?
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

  ((compared++))

  # LeetCode 스타일 표시 값 준비
  input_val="$(cat "$input_file" | to_leetcode_value)"
  expected_val="$(cat "$expected_file" | to_leetcode_value)"
  output_val="$(cat "$out_tmp" | to_leetcode_value)"

  # 결과 판정
  status=""
  extra=""
  if [[ "$exit_code" -ne 0 ]]; then
    status="RE"
    extra="(exit code: $exit_code)"
  else
    if diff -q "$out_tmp" "$expected_file" >/dev/null 2>&1; then
      status="AC"
      ((accepted++))
    else
      status="WA"
      diff -u "$expected_file" "$out_tmp" > "diff_${base}.patch" || true
      extra="(see diff_${base}.patch)"
    fi
  fi

  # 출력
  echo "Case: $input_file"
  echo "  input    = $input_val"
  echo "  output   = $output_val"
  echo "  expected = $expected_val"
  if [[ -n "${mem_kb:-}" ]]; then
    echo "  stats    = ${duration_ms} ms, MaxRSS ${mem_kb} KB (${mem_mb} MB)"
  else
    echo "  stats    = ${duration_ms} ms, MaxRSS N/A"
  fi
  print_result_line "$status" "$extra"
  echo

  rm -f "$out_tmp" "$mem_tmp"
done

# 요약 (SKIP 제외하고 비교한 케이스 기준)
if [[ "$compared" -le 0 ]]; then
  echo "Summary: No comparable testcases. (Skipped: $skipped)"
  exit 0
fi

percent="$(awk -v a="$accepted" -v t="$compared" 'BEGIN { printf "%.2f", (a*100.0)/t }')"
echo "Summary: ${accepted}/${compared} accepted (${percent}%).  (Skipped: ${skipped}, Total: ${total})"
