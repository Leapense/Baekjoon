set datafile separator ","
set terminal pngcairo size 1280,720
set output "benchmark_time.png"
set title "Execution Time vs Input Size"
set xlabel "Input Size (auto inferred)"
set ylabel "Time (ms)"
set key left top
set grid
plot "benchmark_sorted.csv" using 3:4 with linespoints linewidth 2 pointtype 7 title "time_ms"
