'use strict';

const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/).map(Number);

const n = input[0];
const a = input.slice(1, 1 + n);

for (let i = 1; i < n; i++) {
    const expected = (a[i - 1] % n) + 1;
    if (a[i] !== expected) {
        console.log('YES');
        process.stdout.write(String(i + 1));
        process.exit(0);
    }
}

process.stdout.write('NO');
process.exit(0);