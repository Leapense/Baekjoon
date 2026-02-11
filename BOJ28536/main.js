'use strict';
const fs = require('fs');

const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/);
let idx = 0;

const n = Number(input[idx++]);
const m = Number(input[idx++]);

const lens = new Array(n + 1);
const prefix = new Array(n + 1);
prefix[0] = 0;

for (let i = 1; i <= n; i++) {
    const w = input[idx++];
    lens[i] = w.length;
    prefix[i] = prefix[i - 1] + lens[i];
}

function lowerBoundPrefix(x) {
    let lo = 1, hi = n;
    while (lo < hi) {
        const mid = (lo + hi) >> 1;
        if (prefix[mid] >= x) hi = mid;
        else lo = mid + 1;
    }
    return lo;
}

const out = [];
for (let qi = 0; qi < m; qi++) {
    const x = Number(input[idx++]);
    const wordIdx = lowerBoundPrefix(x);
    const posInWord = x - prefix[wordIdx - 1];
    out.push(wordIdx + ' ' + posInWord);
}

process.stdout.write(out.join('\n'));
process.exit(0);