'use strict';

const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/).map(Number);

const N = input[0];
const A1 = input[1];
const AN = input[N];

const M = N - 1;

function clamp(x, lo, hi) {
    return Math.max(lo, Math.min(hi, x));
}

function value(L) {
    const leftFinal = A1 - L;
    const rightFinal = AN - (M - L);
    return Math.max(leftFinal, rightFinal);
}

const realX = (A1 - AN + M) / 2;
const cand1 = clamp(Math.floor(realX), 1, N - 2);
const cand2 = clamp(Math.ceil(realX), 1, N - 2);

let answer = Math.min(value(cand1), value(cand2));
process.stdout.write(answer.toString());
process.exit(0);