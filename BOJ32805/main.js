'use strict';

// main function
function main(input) {
    const w = BigInt(input[0]);
    const s = BigInt(input[1]);

    const T = 29260n;
    const D = 110n;

    const c = s * (s + 1n) / 2n;
    const W0 = c * T;

    const k = (w - W0) / D;
    console.log(k.toString());
}

const fs = require('fs');
const input = fs.readFileSync(0, 'utf-8').trim().split(/\s+/);

// execute main function
main(input);