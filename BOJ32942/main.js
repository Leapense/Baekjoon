'use strict';

const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/).map(Number);

const [A, B, C] = input;
const result = [];

for (let x = 1; x <= 10; x++) {
    const reachable = [];
    for (let y = 1; y <= 10; y++) {
        if (A * x + B * y === C) {
            reachable.push(y);
        }
    }

    result.push(reachable.length > 0 ? reachable.join(' ') : '0');
}

process.stdout.write(result.join('\n'));
process.exit(0);