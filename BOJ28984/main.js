'use strict';

const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/);

const s = input[0];
const x = Number(input[1]);

let L = 0;
let R = 0;

for (const ch of s) {
    if (ch === 'L') L++;
    else R++;
}

let answer;

if (L > 0 && R > 0) {
    answer = 'YES';
} else if (L === 0) {
    answer = (x >= R) ? 'YES' : 'NO';
} else {
    answer = (x <= -L) ? 'YES' : 'NO';
}

process.stdout.write(String(answer));
process.exit(0);