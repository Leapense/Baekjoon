'use strict';
const fs = require('fs');

const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/).map(Number);
let ptr = 0;

const N = input[ptr++];
const d = [];
for (let i = 0; i < N; i++) d.push(input[ptr++]);

const L = d.reduce((a, b) => a + b, 0);

let answer = Infinity;

for (let s = 0; s < N; s++) {
    const a = Array(N).fill(0);
    let dist = 0;
    for (let t = 0; t < N; t++) {
        const idx = (s + t) % N;
        a[idx] = dist;
        dist += d[idx];
    }

    const points = [];
    for (let i = 0; i < N; i++) {
        points.push(a[i]);
        points.push(a[i] + L);
    }

    points.sort((x, y) => x - y);

    let solitary = 0;
    for (let j = 0; j + 1 < points.length; j++) {
        const left = points[j];
        const right = points[j + 1];
        if (right === left) continue;

        let count = 0;
        for (let i = 0; i < N; i++) {
            if (a[i] <= left && left < a[i] + L) count++;
        }
        if (count === 1) solitary += (right - left);
    }

    answer = Math.min(answer, solitary);
}

console.log(String(answer));