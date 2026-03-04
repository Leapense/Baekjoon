'use strict';
const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/);

let U = BigInt(input[0]);
let O = BigInt(input[1]);
let S = BigInt(input[2]);

const halfU = U / 2n;

function min2(a, b) { return a < b ? a : b; }
function max2(a, b) { return a > b ? a : b; }

function g(x) {
    const uLeft = U - 2n * x;
    const sRight = S + x;
    return min2(uLeft, sRight);
}

let bestG = 0n;

if (U <= S) {
    bestG = U;
} else {
    const diff = U - S;
    const q = diff / 3n;

    const candSet = new Set();
    function addCand(x) {
        if (x < 0n || x > halfU) return;
        candSet.add(x.toString());
    }

    addCand(0n);
    addCand(halfU);

    for (let d = -2; d <= 2; d++) {
        addCand(q + BigInt(d));
    }

    for (const xs of candSet) {
        const x = BigInt(xs);
        bestG = max2(bestG, g(x));
    }
}

const ans = min2(O, bestG);
process.stdout.write(ans.toString());