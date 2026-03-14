#!/usr/bin/env bash
shopt -s nullglob

###############################################################################
# 설정 및 인자 파싱 (Time Limit, Memory Limit, Graph)
###############################################################################
TIME_LIMIT_MS=""
MEMORY_LIMIT_MB=""
GENERATE_GRAPH=0
GRAPH_PREFIX="benchmark"
X_MODE="auto"   # auto | filename | first_token | lines | bytes | index

show_usage() {
  cat <<USAGE
Usage: $0 [-t time_limit_ms] [-m memory_limit_mb] [-g] [-p graph_prefix] [-x x_mode]

Options:
  -t ms     Time limit in milliseconds
  -m mb     Memory limit in megabytes
  -g        Generate benchmark CSV + graphs (requires gnuplot for PNG output)
  -p name   Output prefix for CSV/graph files (default: benchmark)
  -x mode   X-axis mode for graph
            auto       : filename tail number -> first token -> line count -> byte size
            filename   : trailing digits in filename, e.g. input100.in -> 100
            first_token: first whitespace-separated integer in input
            lines      : number of lines in input file
            bytes      : size of input file in bytes
            index      : testcase order (1, 2, 3, ...)
  -h        Show this help
USAGE
}

while getopts ":t:m:gp:x:h" opt; do
  case "$opt" in
    t) TIME_LIMIT_MS="$OPTARG" ;;
    m) MEMORY_LIMIT_MB="$OPTARG" ;;
    g) GENERATE_GRAPH=1 ;;
    p) GRAPH_PREFIX="$OPTARG" ;;
    x) X_MODE="$OPTARG" ;;
    h)
      show_usage
      exit 0
      ;;
    :) 
      echo "Option -$OPTARG requires an argument."
      show_usage
      exit 1
      ;;
    \?)
      echo "Unknown option: -$OPTARG"
      show_usage
      exit 1
      ;;
  esac
done
shift "$((OPTIND -1))"

case "$X_MODE" in
  auto|filename|first_token|lines|bytes|index) ;;
  *)
    echo "Invalid -x mode: $X_MODE"
    show_usage
    exit 1
    ;;
esac

###############################################################################
# 실행 커맨드 설정 (언어: Node.js)
###############################################################################
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
    AC)   printf "%s" "${ESC}[30;42m AC ${RESET}" ;;
    WA)   printf "%s" "${ESC}[97;41m WA ${RESET}" ;;
    RE)   printf "%s" "${ESC}[97;45m RE ${RESET}" ;;
    TLE)  printf "%s" "${ESC}[30;43m TLE ${RESET}" ;;
    MLE)  printf "%s" "${ESC}[97;44m MLE ${RESET}" ;;
    SKIP) printf "%s" "${ESC}[30;47m SKIP ${RESET}" ;;
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
text = text.rstrip("\\r\\n")

if text == "":
    print("[]")
    raise SystemExit

lines = text.splitlines()

if len(lines) == 1:
    s = lines[0].strip()
    try:
        obj = json.loads(s)
        print(json.dumps(obj, ensure_ascii=False))
        raise SystemExit
    except Exception:
        pass

arr = [ln.rstrip("\\r\\n") for ln in lines]
print(json.dumps(arr, ensure_ascii=False))
'
}

csv_escape() {
  local s="$1"
  s=${s//\"/\"\"}
  printf '"%s"' "$s"
}

extract_filename_tail_number() {
  local input_file="$1"
  local base="${input_file%.in}"
  if [[ "$base" =~ ([0-9]+)$ ]]; then
    printf "%s" "${BASH_REMATCH[1]}"
    return 0
  fi
  return 1
}

extract_first_token_number() {
  local input_file="$1"
  awk '
    {
      for (i = 1; i <= NF; i++) {
        if ($i ~ /^-?[0-9]+$/) {
          print $i
          exit
        }
      }
    }
  ' "$input_file"
}

infer_x_value() {
  local input_file="$1"
  local case_index="$2"
  local x_value=""
  local x_source=""

  case "$X_MODE" in
    filename)
      if x_value="$(extract_filename_tail_number "$input_file")" && [[ -n "$x_value" ]]; then
        x_source="filename"
      fi
      ;;
    first_token)
      x_value="$(extract_first_token_number "$input_file")"
      [[ -n "$x_value" ]] && x_source="first_token"
      ;;
    lines)
      x_value="$(wc -l < "$input_file" | tr -d '[:space:]')"
      x_source="lines"
      ;;
    bytes)
      x_value="$(wc -c < "$input_file" | tr -d '[:space:]')"
      x_source="bytes"
      ;;
    index)
      x_value="$case_index"
      x_source="index"
      ;;
    auto)
      if x_value="$(extract_filename_tail_number "$input_file")" && [[ -n "$x_value" ]]; then
        x_source="filename"
      else
        x_value="$(extract_first_token_number "$input_file")"
        if [[ -n "$x_value" ]]; then
          x_source="first_token"
        else
          x_value="$(wc -l < "$input_file" | tr -d '[:space:]')"
          if [[ -n "$x_value" ]]; then
            x_source="lines"
          else
            x_value="$(wc -c < "$input_file" | tr -d '[:space:]')"
            x_source="bytes"
          fi
        fi
      fi
      ;;
  esac

  if [[ -z "$x_value" ]]; then
    x_value="$case_index"
    x_source="index_fallback"
  fi

  printf '%s,%s\n' "$x_value" "$x_source"
}

generate_graphs() {
  local csv_file="$1"
  local prefix="$2"
  local x_label="$3"
  local sorted_csv="${prefix}_sorted.csv"
  local time_gp="${prefix}_time.gp"
  local mem_gp="${prefix}_memory.gp"
  local has_gnuplot=0

  if command -v gnuplot >/dev/null 2>&1; then
    has_gnuplot=1
  fi

  {
    head -n 1 "$csv_file"
    tail -n +2 "$csv_file" | sort -t, -k3,3n
  } > "$sorted_csv"

  cat > "$time_gp" <<EOF_TIME
set datafile separator ","
set terminal pngcairo size 1280,720
set output "${prefix}_time.png"
set title "Execution Time vs Input Size"
set xlabel "${x_label}"
set ylabel "Time (ms)"
set key left top
set grid
plot "${sorted_csv}" using 3:4 with linespoints linewidth 2 pointtype 7 title "time_ms"
EOF_TIME

  cat > "$mem_gp" <<EOF_MEM
set datafile separator ","
set terminal pngcairo size 1280,720
set output "${prefix}_memory.png"
set title "Memory Usage vs Input Size"
set xlabel "${x_label}"
set ylabel "Memory (KB)"
set key left top
set grid
plot "${sorted_csv}" using 3:5 with linespoints linewidth 2 pointtype 7 title "mem_kb"
EOF_MEM

  echo
  echo "Graph data files:"
  echo "  - $csv_file"
  echo "  - $sorted_csv"
  echo "  - $time_gp"
  echo "  - $mem_gp"

  if [[ "$has_gnuplot" -eq 1 ]]; then
    if gnuplot "$time_gp" && gnuplot "$mem_gp"; then
      echo "Graph images:"
      echo "  - ${prefix}_time.png"
      echo "  - ${prefix}_memory.png"
    else
      echo "Graph generation failed while running gnuplot."
      return 1
    fi
  else
    echo "gnuplot not found. CSV and .gp files were generated, but PNG graphs were not created."
    echo "Install with: sudo dnf install gnuplot"
  fi
}

###############################################################################
# time(1) 설정 및 timeout 설정
###############################################################################
TIME_CMD="/usr/bin/time"
HAS_GNU_TIME=0
if "$TIME_CMD" --version >/dev/null 2>&1; then
  HAS_GNU_TIME=1
fi

TIMEOUT_CMD=()
if [[ -n "$TIME_LIMIT_MS" ]]; then
  TIMEOUT_SEC=$(awk -v ms="$TIME_LIMIT_MS" 'BEGIN { printf "%.3f", ms/1000 }')
  TIMEOUT_CMD=("timeout" "${TIMEOUT_SEC}s")
fi

accepted=0
total=0
skipped=0
compared=0
case_index=0

CSV_FILE="${GRAPH_PREFIX}.csv"
if [[ "$GENERATE_GRAPH" -eq 1 ]]; then
  echo 'case_index,input_file,x_value,time_ms,mem_kb,mem_mb,status,exit_code,x_source' > "$CSV_FILE"
fi

for input_file in *.in; do
  ((total++))
  ((case_index++))
  base="${input_file%.in}"

  if ! expected_file="$(find_expected "$input_file")"; then
    echo "Case: $input_file"
    echo "  input    = $(cat "$input_file" | to_leetcode_value)"
    echo "  expected = []"
    echo "  output   = []"
    echo "  result   = SKIP (expected output not found)"
    echo

    if [[ "$GENERATE_GRAPH" -eq 1 ]]; then
      IFS=, read -r x_value x_source < <(infer_x_value "$input_file" "$case_index")
      echo "${case_index},$(csv_escape "$input_file"),${x_value},,${mem_kb:-},${mem_mb:-},SKIP,,${x_source}" >> "$CSV_FILE"
    fi

    ((skipped++))
    continue
  fi

  out_tmp="$(mktemp)"
  mem_tmp="$(mktemp)"

  start_ns="$(date +%s%N)"

  exit_code=0
  mem_kb=""
  mem_mb=""
  if [[ "$HAS_GNU_TIME" -eq 1 ]]; then
    "$TIME_CMD" -f "%M" -o "$mem_tmp" "${TIMEOUT_CMD[@]}" "${RUN_CMD[@]}" < "$input_file" > "$out_tmp" || exit_code=$?
    mem_kb="$(cat "$mem_tmp" 2>/dev/null || true)"
    [[ -n "${mem_kb:-}" ]] && mem_mb="$(( mem_kb / 1024 ))"
  else
    "$TIME_CMD" -l "${TIMEOUT_CMD[@]}" "${RUN_CMD[@]}" < "$input_file" > "$out_tmp" 2> "$mem_tmp" || exit_code=$?
    mem_bytes="$(awk '/maximum resident set size/ {print $1; exit}' "$mem_tmp" 2>/dev/null || true)"
    if [[ -n "${mem_bytes:-}" ]]; then
      mem_kb="$(( mem_bytes / 1024 ))"
      mem_mb="$(( mem_kb / 1024 ))"
    fi
  fi

  end_ns="$(date +%s%N)"
  duration_ms="$(( (end_ns - start_ns) / 1000000 ))"

  ((compared++))

  input_val="$(cat "$input_file" | to_leetcode_value)"
  expected_val="$(cat "$expected_file" | to_leetcode_value)"
  output_val="$(cat "$out_tmp" | to_leetcode_value)"

  status=""
  extra=""

  if [[ "$exit_code" -eq 124 ]] || [[ -n "$TIME_LIMIT_MS" && "$duration_ms" -gt "$TIME_LIMIT_MS" ]]; then
    status="TLE"
    extra="(limit: ${TIME_LIMIT_MS} ms)"
  elif [[ -n "$MEMORY_LIMIT_MB" && -n "$mem_mb" && "$mem_mb" -gt "$MEMORY_LIMIT_MB" ]]; then
    status="MLE"
    extra="(limit: ${MEMORY_LIMIT_MB} MB)"
  elif [[ "$exit_code" -ne 0 ]]; then
    status="RE"
    extra="(exit code: $exit_code)"
  else
    if diff -q "$out_tmp" "$expected_file" >/dev/null 2>&1; then
      status="AC"
      if [[ -f "diff_${base}.patch" ]]; then
        rm "diff_${base}.patch"
      fi
      ((accepted++))
    else
      status="WA"
      diff -u "$expected_file" "$out_tmp" > "diff_${base}.patch" || true
      extra="(see diff_${base}.patch)"
    fi
  fi

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

  if [[ "$GENERATE_GRAPH" -eq 1 ]]; then
    IFS=, read -r x_value x_source < <(infer_x_value "$input_file" "$case_index")
    echo "${case_index},$(csv_escape "$input_file"),${x_value},${duration_ms},${mem_kb:-},${mem_mb:-},${status},${exit_code},${x_source}" >> "$CSV_FILE"
  fi

  rm -f "$out_tmp" "$mem_tmp"
done

if [[ "$compared" -le 0 ]]; then
  echo "Summary: No comparable testcases. (Skipped: $skipped)"
  exit 0
fi

percent="$(awk -v a="$accepted" -v t="$compared" 'BEGIN { printf "%.2f", (a*100.0)/t }')"
echo "Summary: ${accepted}/${compared} accepted (${percent}%).  (Skipped: ${skipped}, Total: ${total})"

if [[ "$GENERATE_GRAPH" -eq 1 ]]; then
  case "$X_MODE" in
    auto)   X_LABEL="Input Size (auto inferred)" ;;
    filename) X_LABEL="Input Size (filename tail number)" ;;
    first_token) X_LABEL="Input Size (first integer token)" ;;
    lines) X_LABEL="Input Size (line count)" ;;
    bytes) X_LABEL="Input Size (byte size)" ;;
    index) X_LABEL="Testcase Order" ;;
  esac
  generate_graphs "$CSV_FILE" "$GRAPH_PREFIX" "$X_LABEL"
fi
