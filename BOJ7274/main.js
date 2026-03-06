'use strict';

const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/).map(Number);

let idx = 0;
const N = input[idx++];
const K = input[idx++];

const a = [];
let total = 0;

for (let i = 0; i < N; i++) {
    const x = input[idx++];
    a.push(x);
    total += x;
}

a.sort((x, y) => y - x);

let saved = 0;
for (let i = 0; i < K; i++) {
    saved += a[i];
}

console.log(total - saved);
process.exit(0);