set datafile separator ","
set terminal pngcairo size 1280,720
set output "benchmark_memory.png"
set title "Memory Usage vs Input Size"
set xlabel "Input Size (auto inferred)"
set ylabel "Memory (KB)"
set key left top
set grid
plot "benchmark_sorted.csv" using 3:5 with linespoints linewidth 2 pointtype 7 title "mem_kb"
