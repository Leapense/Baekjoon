'use strict';

const fs = require('fs');

const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/);
let p = 0;
const Q = Number(input[p++]);

const solved = [
    [1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1],
    [1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1],
    [1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1],
    [1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1],
    [1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1],
];

const wrong = [
    [0, 0, 0, 2, 1, 0, 2, 0, 0, 3, 2, 1, 0],
    [0, 1, 0, 0, 8, 0, 4, 3, 2, 0, 2, 3, 3],
    [0, 0, 0, 1, 1, 0, 2, 3, 0, 0, 1, 1, 0],
    [0, 0, 0, 3, 0, 0, 0, 0, 1, 0, 1, 1, 0],
    [0, 1, 1, 0, 0, 0, 0, 6, 4, 0, 1, 0, 1],
];

let out = [];
for (let i = 0; i < Q; i++) {
    const R = Number(input[p++]);
    const C = input[p++];
    const r = R - 1;
    const c = C.charCodeAt(0) - 65;
    out.push((solved[r][c] ? 'Yes' : 'No') + ' ' + wrong[r][c]);
}

process.stdout.write(out.join('\n'));
process.exit(0);