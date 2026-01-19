'use strict';
const fs = require('fs');

const input = fs.readFileSync(0);
let idx = 0;
const len = input.length;

function nextInt() {
    while (idx < len) {
        const c = input[idx];
        if (c > 32) break;
        idx++;
    }
    if (idx >= len) return null;

    let sign = 1;
    if (input[idx] === 45) {
        sign = -1;
        idx++;
    }

    let num = 0;
    while (idx < len) {
        const c = input[idx];
        if (c <= 32) break;
        num = num * 10 + (c - 48);
        idx++;
    }

    return num * sign;
}

const N = nextInt();
if (N === null || N === 0) {
    process.stdout.write('POLE');
    process.exit(0);
}

let prevA = nextInt();
let prevB = nextInt();
let prevIsFifth = ((prevA - prevB) % 12 === 7);

const out = [];
for (let i = 1; i < N; i++) {
    const curA = nextInt();
    const curB = nextInt();
    const curIsFifth = ((curA - curB) % 12 === 7);

    if (prevIsFifth && curIsFifth && prevA !== curA && prevB !== curB) {
        out.push(String(i));
    }

    prevA = curA;
    prevB = curB;
    prevIsFifth = curIsFifth;
}

if (out.length === 0) process.stdout.write('POLE');
else process.stdout.write(out.join('\n'));