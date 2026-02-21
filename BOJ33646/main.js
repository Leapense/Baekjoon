'use strict';
const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim();
if (input.length === 0) process.exit(0);

const tokens = input.split(/\s+/);
let idx = 0;

const N = parseInt(tokens[idx++], 10);
const K = parseInt(tokens[idx++], 10);

let ans = 0;

for (let i = 0; i < N; i++) {
    const set = new Set();
    for (let j = 0; j < K; j++) {
        set.add(tokens[idx++]);
    }

    ans += (K - set.size);
}

process.stdout.write(String(ans));
process.exit(0);