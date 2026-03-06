'use strict';

const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/).map(Number);

if (input.length === 0) process.exit(0);

const n = input[0];
let answer = 0;
let positiveRight = 0;

for (let i = n; i >= 1; i--) {
    const hi = input[i];
    answer = Math.max(answer, hi + positiveRight);
    if (hi > 0) positiveRight++;
}

process.stdout.write(answer.toString());
process.exit(0);