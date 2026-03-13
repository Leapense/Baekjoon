'use strict';

const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/);

const N = BigInt(input[0]);
const M = BigInt(input[1]);

const answer = 4n * N * M - 3n * N - 3n * M + 2n;

process.stdout.write(String(answer));
process.exit(0);