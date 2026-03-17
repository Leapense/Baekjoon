'use strict';

const fs = require('fs');
const n = Number(fs.readFileSync(0, 'utf8').trim());

let minNonLetters;

if (n % 2 === 1) {
    const m = Math.floor(n / 2);
    minNonLetters = 1 + 2 * Math.floor(m / 3);
} else {
    const m = n / 2;
    minNonLetters = 2 + 2 * Math.floor((m - 1) / 3);
}

const maxNonLetters = n;

process.stdout.write(minNonLetters + ' ' + maxNonLetters);
process.exit(0);