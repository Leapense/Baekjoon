'use strict';

const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/);
let idx = 0;

const n = Number(input[idx++]);
const H = Number(input[idx++]);

let total = 0n;
const INF = Number.MAX_SAFE_INTEGER;

for (let i = 0; i < n; i++) {
    const a = Number(input[idx++]);
    const b = Number(input[idx++]);
    const c = Number(input[idx++]);
    const d = [a, b, c];

    let best = INF;
    for (let v = 0; v < 3; v++) {
        if (d[v] <= H) {
            const x = d[(v + 1) % 3];
            const y = d[(v + 2) % 3];
            const width = Math.min(x, y);
            if (width < best) best = width;
        }
    }

    if (best === INF) {
        process.stdout.write('impossible\n');
        process.exit(0);
    }

    total += BigInt(best);
}

process.stdout.write(total.toString() + '\n');