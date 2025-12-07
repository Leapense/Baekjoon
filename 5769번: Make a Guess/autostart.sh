#!/bin/bash

for input_file in *.in; do
    start=$(date +%s%N)
    python3 main.py < "$input_file"
    end=$(date +%s%N)
    duration=$(( ($end - $start) / 1000000 ))
    echo "File: $input_file | Elapsed time: ${duration} ms"
done
