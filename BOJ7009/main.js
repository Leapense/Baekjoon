'use strict';

const fs = require('fs');
const input = fs.readFileSync(0, 'utf-8').trim().split(/\s+/).map(Number);

let idx = 0;
const K = input[idx++];
let out = [];

for (let tc = 1; tc <= K; tc++) {
    const N = input[idx++];
    const T = input[idx++];
    const B = input[idx++];
    const S = input[idx++];

    const count = Array(N).fill(0);
    const totalBoxes = T * B;

    for (let k = 0; k < totalBoxes; k++) {
        const type = (k % T) + 1;
        if (type === S) {
            const person = k % N;
            count[person]++;
        }
    }

    const maxVal = Math.max(...count);
    let maxPositions = [];
    for (let i = 0; i < N; i++) {
        if (count[i] === maxVal) maxPositions.push(i + 1);
    }

    let answer;
    if (maxPositions.length >= 2) {
        answer = maxPositions[maxPositions.length - 1];
    } else {
        let secondVal = -1;
        for (let i = 0; i < N; i++) {
            if (count[i] < maxVal) {
                secondVal = Math.max(secondVal, count[i]);
            }
        }

        for (let i = N - 1; i >= 0; i--) {
            if (count[i] === secondVal) {
                answer = i + 1;
                break;
            }
        }
    }

    out.push(`Data Set ${tc}:`);
    out.push(String(answer));
    out.push('');
}

console.log(out.join('\n'));
process.exit(0);