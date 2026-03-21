'use strict';

const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/).map(Number);

let idx = 0;
const N = input[idx++];
const T = input[idx++];

const a = new Array(N);
for (let i = 0; i < N; i++) a[i] = input[idx++];

const prefix = new Array(N + 1).fill(0);
for (let i = 0; i < N; i++) {
    prefix[i + 1] = prefix[i] + a[i];
}

const limit = Math.floor(N / 2) + 1;

for (let g = 1; g <= limit; g++) {
    const groups = Math.floor(N / g);

    if (groups <= 1) {
        process.stdout.write(String(g));
        process.exit(0);
    }

    let prevAvg = Math.floor((prefix[g] - prefix[0]) / g);
    let ok = true;

    for (let k = 1; k < groups; k++) {
        const l = k * g;
        const r = l + g;
        const curAvg = Math.floor((prefix[r] - prefix[l]) / g);

        if (Math.abs(curAvg - prevAvg) > T) {
            ok = false;
            break;
        }
        prevAvg = curAvg;
    }
    if (ok) {
        process.stdout.write(String(g));
        process.exit(0);
    }
}
