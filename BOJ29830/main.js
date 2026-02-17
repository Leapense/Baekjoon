'use strict';
const readline = require('readline');

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout,
    terminal: false
});

let state = 0;
let N = 0;
let H = null;
let hIndex = 0;
let dIndex = 1;
let altitude = 0;
let outBuffer = [];

rl.on('line', (line) => {
    const tokens = line.trim().split(/\s+/);

    for (const token of tokens) {
        if (!token) continue;
        const num = Number(token);
        if (isNaN(num)) {
            continue;
        }

        if (state === 0) {
            N = num;
            if (N < 0 || N > 50000000) {
                console.error("Input size exceeds limits");
                process.exit(1);
            }
            H = new Float64Array(N + 1);
            state = 1;
        } else if (state === 1) {
            H[hIndex++] = num;
            if (hIndex > N) {
                altitude = H[0];
                state = 2;
            }
        } else if (state === 2) {
            if (dIndex <= N) {
                altitude += num;
                const diff = altitude - H[dIndex];
                outBuffer.push((diff === 0) ? 'M' : (diff > 0 ? 'V' : 'T'));
                dIndex++;

                if (outBuffer.length >= 1000) {
                    process.stdout.write(outBuffer.join(''));
                    outBuffer = [];
                }
            }
        }
    }
});

rl.on('close', () => {
    if (outBuffer.length > 0) {
        process.stdout.write(outBuffer.join(''));
    }
});