'use strict';

const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim();
const N = Number(input);

const out = [];
if (N % 2 === 0) {
    const R = N / 2;
    out.push(String(R));

    for (let i = 1; i <= R; i++) {
        out.push(`2 ${i} ${N + 1 - i}`);
    }
} else {
    const R = (N + 1) / 2;
    out.push(String(R));

    const m = (N - 1) / 2;
    for (let i = 1; i <= m; i++) {
        out.push(`2 ${i} ${N - i}`);
    }
    out.push(`1 ${N}`);
}
console.log(out.join('\n'));
process.exit(0);