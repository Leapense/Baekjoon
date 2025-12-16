#!/bin/bash

TIME_CMD="/usr/bin/time"
MEM_TMP=$(mktemp)

if ! command -v "$TIME_CMD" >/dev/null 2>&1; then
    echo "Error: $TIME_CMD not found. Install GNU time (package: time) or adjust the path." >&2
    exit 1
fi

javac -J-Xms1024m -J-Xmx1920m -J-Xss512m -encoding UTF-8 Main.java

for input_file in *.in; do
    start=$(date +%s%N)
    "$TIME_CMD" -f "%M" -o "$MEM_TMP" java -Xms1024m -Xmx1920m -Xss512m -Dfile.encoding=UTF-8 -XX:+UseSerialGC Main < "$input_file"
    end=$(date +%s%N)
    duration=$(( (end - start) / 1000000 ))
    mem_kb=$(<"$MEM_TMP")
    mem_mb=$(( mem_kb / 1024 ))
    echo "File: $input_file | Elapsed time: ${duration} ms | Max RSS: ${mem_kb} KB (${mem_mb} MB)"
done
