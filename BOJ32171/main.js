'use strict';

const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/).map(Number);
let idx = 0;

const N = input[idx++];

let minX = Infinity, minY = Infinity;
let maxX = -Infinity, maxY = -Infinity;

let out = [];
for (let i = 0; i < N; i++) {
    const a = input[idx++], b = input[idx++], c = input[idx++], d = input[idx++];
    if (a < minX) minX = a;
    if (b < minY) minY = b;
    if (c > maxX) maxX = c;
    if (d > maxY) maxY = d;

    const width = maxX - minX;
    const height = maxY - minY;
    const perimeter = 2 * (width + height);

    out.push(String(perimeter));
}

process.stdout.write(out.join('\n'));