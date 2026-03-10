'use strict';

const fs = require('fs');
const s = fs.readFileSync(0, 'utf8').trim();

const cost = Object.create(null);

for (const ch of 'BCDGLMNOPSUVWZ') cost[ch] = 1;
for (const ch of 'AEFJQTRXY') cost[ch] = 2;
for (const ch of 'HIK') cost[ch] = 3;

let answer = 0;
for (const ch of s) {
    answer += cost[ch];
}

process.stdout.write(String(answer));
process.exit(0);