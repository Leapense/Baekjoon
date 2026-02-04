'use strict';

const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/).map(Number);
const H = input[0];
const M = input[1];

function isLucky(ht, hu, mt, mu) {
    if (hu === 2 && mt === 3 && mu === 9) return true;
    if (ht === 2 && (hu === 0 || hu === 2 || hu === 3) && mt === 3 && mu === 9) return true;
    if (ht === 2 && hu === 3 && (mt === 0 || mt === 3 || mt === 9) && mu === 9) return true;
    if (ht === 2 && hu === 3 && mt === 9) return true;
    return false;
}

let ans = 0;
for (let h = 0; h < H; h++) {
    const ht = Math.floor(h / 10);
    const hu = h % 10;

    for (let m = 0; m < M; m++) {
        const mt = Math.floor(m / 10);
        const mu = m % 10;

        if (isLucky(ht, hu, mt, mu)) ans++;
    }
}

process.stdout.write(ans.toString());
process.exit(0);