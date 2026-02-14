'use strict';

const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/);
let idx = 0;

const n = Number(input[idx++]);
let curMin = null;
let ans = 0n;

for (let i = 1; i <= n; i++) {
    const ti = BigInt(input[idx++]);
    if (curMin === null || ti < curMin) curMin = ti;
    if (i <= n - 1) ans += curMin;
}

process.stdout.write(ans.toString());
process.exit(0);