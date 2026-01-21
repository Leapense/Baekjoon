'use strict';
const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/).map(Number);
const n = input[0];
const m = input[1];

const INF = 1e18;
let dpA = INF, dpB = INF;
if (n < m) dpA = 0;
else dpB = 0;

for (let t = 1; t < 1440; t++) {
    const aDep = (t % n === 0);
    const bDep = (t % m === 0);

    if (!aDep && !bDep) continue;
    if (aDep && !bDep) {
        const newA = Math.min(dpA, dpB + 1);
        dpA = newA;
        dpB = INF;
    } else if (!aDep && bDep) {
        const newB = Math.min(dpB, dpA + 1);
        dpB = newB;
        dpA = INF;
    } else {
        const newA = Math.min(dpB + 1, dpA + 2);
        const newB = Math.min(dpA + 1, dpB + 2);
        dpA = newA;
        dpB = newB;
    }
}

const ans = Math.min(dpA, dpB);
process.stdout.write(String(ans) + '\n');