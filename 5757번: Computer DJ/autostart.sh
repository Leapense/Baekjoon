#!/bin/bash

for input_file in *.in; do
    start=$(date +%s%N)
    java Main < "$input_file"
    end=$(date +%s%N)
    duration=$(( ($end - $start) / 1000000 ))
    echo "File: $input_file | Elapsed time: ${duration} ms"
done

