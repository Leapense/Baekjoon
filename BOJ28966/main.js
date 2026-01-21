'use strict';
const fs = require('fs');

const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/).map(Number);
let idx = 0;

const n = input[idx++];
const a = new Array(n + 1);
for (let i = 1; i <= n; i++) a[i] = input[idx++];

const bad = [];
for (let i = 1; i <= n; i++) {
    const ip = i & 1;
    const vp = a[i] & 1;
    if (ip !== vp) bad.push(i);
}

function printAns(i, j) {
    process.stdout.write(`${i} ${j}\n`);
}

if (bad.length === 2) {
    const p = bad[0], q = bad[1];
    const ok = ((p & 1) === (a[q] & 1)) && ((q & 1) === (a[p] & 1));
    if (ok) printAns(p, q);
    else printAns(-1, -1);
} else if (bad.length === 0) {
    if (n === 2) {
        printAns(-1, -1);
    } else {
        printAns(1, 3);
    }
} else {
    printAns(-1, -1);
}