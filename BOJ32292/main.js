'use strict';

const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/);

let idx = 0;
const t = Number(input[idx++]);
const out = [];

for (let tc = 0; tc < t; tc++) {
    const n = Number(input[idx++]);
    const s = input[idx++];

    let prefixB = 0;
    const parts = [];

    for (let i = s.length - 1; i >= 0; i--) {
        if (s[i] === 'B') {
            prefixB++;
        } else {
            const k = prefixB;
            prefixB = Math.floor(k / 2);

            if (k % 2 === 0) {
                parts.push('A');
            } else {
                parts.push('AB');
            }
        }
    }

    parts.reverse();
    out.push('B'.repeat(prefixB) + parts.join(''))
}

process.stdout.write(out.join('\n'));
process.exit(0);