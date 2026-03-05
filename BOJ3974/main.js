'use strict';
const fs = require('fs');

const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/);
if (input.length === 1 && input[0] === '') process.exit(0);

let idx = 0;
const t = Number(input[idx++]);

const pow10 = Array(11).fill(0);
pow10[0] = 1;

for (let i = 1; i <= 10; i++)  pow10[i] = pow10[i - 1] * 10;

function ceilDiv(a, b) {
    return Math.floor((a + b - 1) / b);
}

function existsWithSignificantDigits(L, U, a, needLast5) {
    const baseLo = pow10[a - 1];
    const baseHi = pow10[a] - 1;

    for (let k = 0; k <= 9; k++) {
        const div = pow10[k];

        const qL = ceilDiv(L, div);
        const qU = Math.floor(U / div);

        let lo = Math.max(qL, baseLo);
        let hi = Math.min(qU, baseHi);
        if (lo > hi) continue;

        if (needLast5) {
            const rem = ((5 - (lo % 10)) + 10) % 10;
            const first = lo + rem;
            if (first <= hi) return true;
        } else {
            if (lo < hi) return true;
            if (lo % 10 !== 0) return true;
        }
    }

    return false;
}

function significantInfo(n) {
    let x = n;
    while (x % 10 === 0) x = Math.floor(x / 10);
    const a = x.toString().length;
    const lastDigit = x % 10;
    return { x, a, lastDigit };
}

let out = [];
for (let tc = 0; tc < t; tc++) {
    const c = Number(input[idx++]);

    const L = Math.floor((95 * c + 99) / 100);
    const U = Math.floor((105 * c) / 100);

    const { a: aC, lastDigit: dC } = significantInfo(c);

    let absurd = false;

    for (let a = 1; a <= aC - 1 && !absurd; a++) {
        if (existsWithSignificantDigits(L, U, a, false)) absurd = true;
    }

    if (!absurd && dC !== 5) {
        if (existsWithSignificantDigits(L, U, aC, true)) absurd = true;
    }

    out.push(absurd ? 'absurd' : 'not absurd');
}

process.stdout.write(out.join('\n'));
process.exit(0);