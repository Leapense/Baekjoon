'use strict';

const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/).map(Number);
let idx = 0;

const M = input[idx++];

let maxGame = -Infinity;
let minGame = Infinity;
let maxMonthSum = -Infinity;
let minMonthSum = Infinity;

for (let m = 0; m < M; m++) {
    const S = input[idx++];
    let monthSum = 0;

    for (let i = 0; i < S; i++) {
        const score = input[idx++];
        monthSum += score;

        if (score > maxGame) maxGame = score;
        if (score < minGame) minGame = score;
    }

    if (monthSum > maxMonthSum) maxMonthSum = monthSum;
    if (monthSum < minMonthSum) minMonthSum = monthSum;
}

console.log([
    maxGame,
    minGame,
    maxMonthSum,
    minMonthSum
].join('\n'));