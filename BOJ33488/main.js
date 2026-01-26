'use strict';

const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/);
let idx = 0;

const T = Number(input[idx++]);
const out = [];

for (let tc = 0; tc < T; tc++) {
    const N = Number(input[idx++]);
    out.push('YES');

    const arr = [];
    for (let i = 1; i <= N; i++) arr.push(String(i));
    out.push(arr.join(' '));
}

console.log(out.join('\n'));