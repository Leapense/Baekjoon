'use strict';
const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/);
let ptr = 0;

const n = Number(input[ptr++]);
const a = new Array(n);
const b = new Array(n);

for (let i = 0; i < n; i++) a[i] = Number(input[ptr++]);
for (let i = 0; i < n; i++) b[i] = Number(input[ptr++]);

function gcd(x, y) {
    x = Math.abs(x); y = Math.abs(y);
    while (y !== 0) {
        const t = x % y;
        x = y;
        y = t;
    }

    return x;
}

let idxZero = -1;
let idxPos = -1, pVal = 0;
let idxNeg = -1, qVal = 0;

for (let i = 0; i < n; i++) {
    const d = a[i] - b[i];
    if (d === 0) {
        idxZero = i + 1;
        break;
    }

    if (d > 0 && idxPos === -1) {
        idxPos = i + 1;
        pVal = d;
    } else if (d < 0 && idxNeg === -1) {
        idxNeg = i + 1;
        qVal = d;
    }
}

if (idxZero !== -1) {
    process.stdout.write(`1\n${idxZero}\n`);
    process.exit(0);
}

if (idxPos !== -1 && idxNeg !== -1) {
    const p = pVal;
    const q = qVal;
    const g = gcd(p, -q);
    const c = (-q) / g;
    const d = p / g;

    const k = c + d;
    const res = [];

    for (let i = 0; i < c; i++) res.push(String(idxPos));
    for (let i = 0; i < d; i++) res.push(String(idxNeg));

    process.stdout.write(`${k}\n${res.join(' ')}\n`);
    process.exit(0);
}

process.stdout.write(`-1\n`);
process.exit(0);