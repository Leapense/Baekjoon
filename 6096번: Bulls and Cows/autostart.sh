#!/bin/bash

TIME_CMD="/usr/bin/time"
MEM_TMP=$(mktemp)

if ! command -v "$TIME_CMD" >/dev/null 2>&1; then
    echo "Error: $TIME_CMD not found. Install GNU time (package: time) or adjust the path." >&2
    exit 1
fi

javac Main.java
for input_file in *.in; do
    st=$(date +%s%N)
    "$TIME_CMD" -f "%M" -o "$MEM_TMP" java Main < "$input_file"
    en=$(date +%s%N)
    duration=$(( (en - st) / 1000000 ))
    mem_kb=$(<"$MEM_TMP")
    mem_mb=$(( mem_kb / 1024 ))
    echo "File: $input_file | Elapsed time: ${duration} ms | Max RSS: ${mem_kb} KB (${mem_mb} MB)"
done

