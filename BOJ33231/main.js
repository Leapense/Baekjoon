'use strict';

const fs = require('fs');

const input = fs.readFileSync(0, 'utf8');
let p = 0;
const L = input.length;

function nextInt() {
    while (p < L) {
        if (input.charCodeAt(p) > 32) break;
        p++;
    }
    let sign = 1;
    if (p < L && input.charCodeAt(p) === 45) {
        sign = -1;
        p++;
    }

    let x = 0;
    while (p < L) {
        const c = input.charCodeAt(p);
        if (c <= 32) break;
        x = x * 10 + (c - 48);
        p++;
    }

    return x * sign;
}

const n = nextInt();
const m = nextInt();

const coatis = new Array(n);
const owls = new Array(m);

for (let i = 0; i < n; i++) coatis[i] = nextInt();
for (let j = 0; j < m; j++) owls[j] = nextInt();

function ceilDivBigInt(a, b) {
    return (a + b - 1n) / b;
}

let i = n - 1;
let j = 0;

while (i >= 0 && j < m) {
    const c = coatis[i];
    const o = owls[j];

    if (c === o) {
        i--;
        j++;
        continue;
    }

    const cBig = BigInt(c);
    const oBig = BigInt(o);

    if (c < o) {
        const loss = Number(ceilDivBigInt(cBig * cBig, oBig));
        owls[j] = o - loss;
        i--;
    } else {
        const loss = Number(ceilDivBigInt(oBig * oBig, cBig));
        coatis[i] = c - loss;
        j++;
    }
}

if (i < 0 && j >= m) {
    process.stdout.write('stalemate');
} else if (i < 0) {
    let rem = 0n;
    for (let k = j; k < m; k++) rem += BigInt(owls[k]);
    process.stdout.write(`owls\n${rem}`);
} else {
    let rem = 0n;
    for (let k = 0; k <= i; k++) rem += BigInt(coatis[k]);
    process.stdout.write(`coatis\n${rem}`);
}