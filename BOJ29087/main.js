'use strict';
const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trimEnd().split('\n');
const n = parseInt(input[0], 10);
const s = (input[1] || '').trim();

let cur = 0;
let ans = 0;

for (let i = 0; i < n; i++) {
    const c = s[i];
    const isLaughChar = (c === 'a' || c === 'h');
    if (!isLaughChar) {
        cur = 0;
        continue;
    }

    if (cur === 0) {
        cur = 1;
    } else {
        const prev = s[i - 1];
        if (prev === 'a' || prev === 'h') {
            if (c !== prev) cur += 1;
            else cur = 1;
        } else {
            cur = 1;
        }
    }

    if (cur > ans) ans = cur;
}

process.stdout.write(ans.toString());
process.exit(0);