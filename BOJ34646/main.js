'use strict';

const fs = require('fs');

const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/).map(Number);
let idx = 0;

const n = input[idx++];
const d = input.slice(idx, idx + n);

const a = d[0] / 3;
const b = d[1] - 2 * a;
const c = d[n - 1] / 3;

process.stdout.write(`${a} ${b} ${c}`);
process.exit(0);