#!/bin/bash

TIME_CMD="/usr/bin/time"
MEM_TMP=$(mktemp)

python3 -W ignore -c "import py_compile; py_compile.compile(r'main.py')"

for input_file in *.in; do
    start=$(date +%s%N)
    "$TIME_CMD" -f "%M" -o "$MEM_TMP" python3 -W ignore main.py < "$input_file"
    end=$(date +%s%N)
    duration_ms=$(( (end - start) / 1000000 ))

    mem_kb=$(cat "$MEM_TMP")
    mem_mb=$(( mem_kb / 1024 ))

    echo "File: $input_file | Elapsed time: ${duration_ms} ms | Max RSS: ${mem_kb} KB (${mem_mb} MB)"
done
    
rm -f "$MEM_TMP"
