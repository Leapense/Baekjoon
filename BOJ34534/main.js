'use strict';

const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/);
const nums = input.map(Number);

const set = new Set(nums);

let maxOverlap = 0;

for (let s = 1; s <= 5; s++) {
    let overlap = 0;
    for (let x = s; x <= s + 4; x++) {
        if (set.has(x)) overlap++;
    }

    if (overlap > maxOverlap) maxOverlap = overlap;
}

const answer = 5 - maxOverlap;
process.stdout.write(String(answer));
process.exit(0);