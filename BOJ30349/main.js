'use strict';

const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/).map(Number);
let idx = 0;

const M = input[idx++];
const N = input[idx++];
const A = input[idx++];
const B = input[idx++];
const X = input[idx++];
const Y = input[idx++];

const Lx = Math.max(0, -X);
const Rx = Math.min(M - 1, (A - 1) - X);

const Ly = Math.max(0, -Y);
const Ry = Math.min(N - 1, (B - 1) - Y);

if (Lx > Rx || Ly > Ry) {
    process.stdout.write('NEPATAIKYS');
} else {
    const TXmin = Lx + X;
    const TYmin = Ly + Y;
    const ans = TXmin + TYmin;
    process.stdout.write(String(ans));
}