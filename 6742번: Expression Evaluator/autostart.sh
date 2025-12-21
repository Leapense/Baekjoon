#!/bin/bash

TIME_CMD="/usr/bin/time"
MEM_TMP=$(mktemp)

gcc -std=c23 -O2 -Wall -Wextra -pedantic main.c -o main

for input_file in *.in; do
    start=$(date +%s%N)
    "$TIME_CMD" -f "%M" -o "$MEM_TMP" ./main < "$input_file"
    end=$(date +%s%N)
    duration_ms=$(( (end - start) / 1000000 ))

    mem_kb=$(cat "$MEM_TMP")
    mem_mb=$(( mem_kb / 1024 ))

    echo "File: $input_file | Elapsed time: ${duration_ms} ms | Max RSS: ${mem_kb} KB (${mem_mb} MB)"
done

g++ -std=c++2b -O2 -Wall -Wextra -pedantic main.cpp -o main

for input_file in *.in; do
    start=$(date +%s%N)
    "$TIME_CMD" -f "%M" -o "$MEM_TMP" ./main < "$input_file"
    end=$(date +%s%N)
    duration_ms=$(( (end - start) / 1000000 ))

    mem_kb=$(cat "$MEM_TMP")
    mem_mb=$(( mem_kb / 1024 ))

    echo "File: $input_file | Elapsed time: ${duration_ms} ms | Max RSS: ${mem_kb} KB (${mem_mb} MB)"
done
    
rm -f "$MEM_TMP"
