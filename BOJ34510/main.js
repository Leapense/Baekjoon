'use strict';

const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/);
let idx = 0;

const H1 = BigInt(input[idx++]);
const H2 = BigInt(input[idx++]);
const H3 = BigInt(input[idx++]);
const N  = BigInt(input[idx++]);

const A = H1 + H2 + H3;
const pairHeight = H2 + 2n * H3;

const pairs = N / 2n;
const odd   = N % 2n;

const ans = pairs * pairHeight + odd * A;

process.stdout.write(ans.toString());
process.exit(0);