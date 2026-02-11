'use strict';

const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/);
let idx = 0;

const n = Number(input[idx++]);
const b = BigInt(input[idx++]);

let q = 0n;
let ans = 0n;

for (let i = 0; i < n; i++) {
    const ai = BigInt(input[idx++]);
    q += ai;
    ans += q;

    const served = (q < b) ? q : b;
    q -= served;
}

ans += q;
console.log(ans.toString());
process.exit(0);