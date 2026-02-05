const readline = require('readline');

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

let lines = [];
rl.on('line', (line) => {
    lines.push(line.trim());
});

rl.on('close', () => {
    const N = parseInt(lines[0]);
    if (N < 4) {
        console.log(0);
        return;
    }

    const X = new Array(N + 1);
    X[1] = 0;
    const distances = lines[1].split(/\s+/).map(Number);
    for (let i = 0; i < distances.length; i++) {
        X[i + 2] = distances[i];
    }

    let minDiff = Infinity;
    let minIdx = -1;
    for (let i = 2; i <= N - 2; i++) {
        const diff = X[i + 1] - X[i];
        if (diff < minDiff) {
            minDiff = diff;
            minIdx = i;
        }
    }

    const totalLength = X[N] + minDiff;

    const b = minIdx;
    const c = minIdx + 1;

    console.log(totalLength);
    console.log(`${c} 1 ${N} ${b}`);
});