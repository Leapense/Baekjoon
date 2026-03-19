'use strict';

const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/).map(Number);

let idx = 0;
const X1 = input[idx++], Y1 = input[idx++];
const X2 = input[idx++], Y2 = input[idx++];
const X3 = input[idx++], Y3 = input[idx++];

const Q = input[idx++];

function nextTrainTime(dist, interval) {
    return Math.floor((dist + interval - 1) / interval) * interval;
}

let out = [];

for (let q = 0; q < Q; q++) {
    const X = input[idx++], Y = input[idx++];
    const T1 = input[idx++], T2 = input[idx++], T3 = input[idx++];

    const d1 = Math.abs(X - X1) + Math.abs(Y - Y1);
    const d2 = Math.abs(X - X2) + Math.abs(Y - Y2);
    const d3 = Math.abs(X - X3) + Math.abs(Y - Y3);

    const a1 = nextTrainTime(d1, T1);
    const a2 = nextTrainTime(d2, T2);
    const a3 = nextTrainTime(d3, T3);

    out.push(String(Math.min(a1, a2, a3)));
}

process.stdout.write(out.join('\n'));
process.exit(0);