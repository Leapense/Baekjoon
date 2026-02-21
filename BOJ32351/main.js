'use strict';

const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/);
let idx = 0;

const N = parseInt(input[idx++], 10);
let bpm = parseFloat(input[idx++]);
const K = parseInt(input[idx++], 10);

let ans = 0.0;
let curStart = 1;

for (let i = 0; i < K; i++) {
    const M = parseInt(input[idx++], 10);
    const nextBpm = parseFloat(input[idx++]);

    const len = M - curStart;
    ans += len * (240.0 / bpm);

    curStart = M;
    bpm = nextBpm;
}

const lastLen = N - curStart + 1;
ans += lastLen * (240.0 / bpm);

process.stdout.write(ans.toFixed(12));
process.exit(0);