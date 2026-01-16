'use strict';

const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/);
let idx = 0;
const t = Number(input[idx++]);
let out = [];

for (let tc = 0; tc < t; tc++) {
    const k = Number(input[idx++]);
    const nStr = input[idx++];

    let res = '';
    for (let i = 0; i < nStr.length; i++) {
        const d = nStr.charCodeAt(i) - 48;
        res += String((d + k) % 10);
    }
    out.push(res);
}

process.stdout.write(out.join('\n'));