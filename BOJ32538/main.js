'use strict';

const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim();
if (input.length === 0) process.exit(0);

const tokens = input.split(/\s+/);
const y = parseInt(tokens[0], 10);

const DIGITS = '0123456789abcdef';

function toBase(n, b) {
    if (n === 0) return '0';
    let x = n;
    const out = [];
    while (x > 0) {
        const d = x % b;
        out.push(DIGITS[d]);
        x = Math.floor(x / b);
    }

    out.reverse();
    return out.join('');
}

for (let b = 2; b <= 16; b++) {
    const bb = b * b;
    if (y % bb === 0) {
        const repr = toBase(y, b);
        process.stdout.write(`${b} ${repr}`);
        process.exit(0);
    }
}

process.stdout.write('impossible');
process.exit(0);