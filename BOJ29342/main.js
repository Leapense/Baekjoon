'use strict';

const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/);
let idx = 0;

const n = Number(input[idx++]);

let even = 0n;
let odd = 0n;

for (let i = 0; i < n; i++) {
    const x = Number(input[idx++]);
    if ((x & 1) === 0) even++;
    else odd++;
}

const ans = even * odd;
console.log(ans.toString());