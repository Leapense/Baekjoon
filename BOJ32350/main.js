'use strict';

const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/).map(Number);
let idx = 0;

const N = input[idx++], D = input[idx++], p = input[idx++];
const hp = new Array(N);
for (let i = 0; i < N; i++) hp[i] = input[idx++];

let turns = 0;
let i = 0;

while (true) {
    while (i < N && hp[i] <= 0) i++;
    if (i >= N) break;

    hp[i] -= D;

    if (hp[i] < 0 && i + 1 < N) {
        const h = -hp[i];
        const extra = Math.floor((h * p) / 100);
        if (extra > 0) hp[i + 1] -= extra;
    }

    turns++;
}

process.stdout.write(turns.toString());
process.exit(0);