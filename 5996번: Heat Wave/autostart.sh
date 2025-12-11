#!/bin/bash

TIME_CMD="/usr/bin/time"
MEM_TMP=$(mktemp)

if ! command -v "$TIME_CMD" >/dev/null 2>&1; then
    echo "Error: $TIME_CMD not found. Install GNU time (package: time) or adjust the path." >&2
    exit 1
fi

# compile the main.c first and execute the program.
gcc -std=c23 -lm main.c -o main
for input_file in *.in; do
    start=$(date +%s%N)
    "$TIME_CMD" -f "%M" -o "$MEM_TMP" ./main < "$input_file"
    end=$(date +%s%N)
    duration=$(( (end - start) / 1000000 ))
    mem_kb=$(<"$MEM_TMP")
    mem_mb=$(( mem_kb / 1024 ))
    echo "File: $input_file | Elapsed time: ${duration} ms | Max RSS: ${mem_kb} KB (${mem_mb} MB)"
done

# compile the main.cpp and execute the program.
g++ -std=c++2c -lm main.cpp -o main
for input_file in *.in; do
    start=$(date +%s%N)
    "$TIME_CMD" -f "%M" -o "$MEM_TMP" ./main < "$input_file"
    end=$(date +%s%N)
    duration=$(( (end - start) / 1000000 ))
    mem_kb=$(<"$MEM_TMP")
    mem_mb=$(( mem_kb / 1024 ))
    echo "File: $input_file | Elapsed time: ${duration} ms | Max RSS: ${mem_kb} KB (${mem_mb} MB)"
done
