'use strict';

const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/);

const n = BigInt(input[0]);
const m = BigInt(input[1]);

let power = 1n;
let k = 0;

while (power < n) {
    power *= m;
    k++;
}

process.stdout.write(String(k));
process.exit(0);