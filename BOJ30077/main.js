'use strict';

const fs = require('fs');
const data = fs.readFileSync(0, 'utf8').trim().split(/\s+/);
let idx = 0;

const N = Number(data[idx++]);
const M = Number(data[idx++]);
const L = Number(data[idx++]);

let Tmin = Infinity;
const T = new Array(N);

for (let i = 0; i < N; i++) {
    const ti = Number(data[idx++]);
    T[i] = ti;
    if (ti < Tmin) Tmin = ti;
}

let count = 0;
const leftMul = M - 1;
const rightMul = M;

for (let i = 0; i < N; i++) {
    if (leftMul * T[i] < rightMul * Tmin) count++;
}

process.stdout.write(String(count));