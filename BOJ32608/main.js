'use strict';

const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/).map(Number);

let idx = 0;
const n = input[idx++];
const h = input[idx++];

const s = new Array(n);
const y = new Array(n);

for (let i = 0; i < n; i++) {
    s[i] = input[idx++];
    y[i] = input[idx++];
}

let totalTime = 0;
let sumS = s[0];
let currentY = y[0];

for (let i = 1; i < n; i++) {
    const dyMm = y[i] - currentY;
    const speed = Math.pow(sumS * 1e-9, 1/6);
    totalTime += (dyMm * 1e-3) / speed;

    sumS += s[i];
    currentY = y[i];
}

{
    const dyMm = h - currentY;
    const speed = Math.pow(sumS * 1e-9, 1/6);
    totalTime += (dyMm * 1e-3) / speed;
}

process.stdout.write(totalTime.toFixed(15).toString());
process.exit(0);