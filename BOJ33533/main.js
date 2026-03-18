'use strict';

const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim();

const n = BigInt(input);
const answer = n * (n + 1n) * (2n * n + 1n) / 6n;

process.stdout.write(answer.toString());
process.exit(0);