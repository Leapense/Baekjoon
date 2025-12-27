#!/bin/bash

TIME_CMD="/usr/bin/time"
MEM_TMP=$(mktemp)

javac --release 15 -J-Xms1024m -J-Xmx1920m -J-Xss512m -encoding UTF-8 Main.java

for input_file in *.in; do
    start=$(date +%s%N)
    "$TIME_CMD" -f "%M" -o "$MEM_TMP" java -Xms1024m -Xmx1920m -Xss512m -Dfile.encoding=UTF-8 -XX:+UseSerialGC -DONLINE_JUDGE=1 -DBOJ=1 Main < "$input_file"
    end=$(date +%s%N)
    duration_ms=$(( (end - start) / 1000000 ))
    mem_kb=$(cat "$MEM_TMP")
    mem_mb=$(( mem_kb / 1024 ))
    echo "File: $input_file | Elapsed time: ${duration_ms} ms | Max RSS: ${mem_kb} KB (${mem_mb} MB)"
done
    
rm -f "$MEM_TMP"
