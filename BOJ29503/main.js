'use strict';

const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trimEnd().split('\n');

const board = [];
for (let i = 0; i < 8; i++) {
    board.push(input[i].trim().split(''))
}

let idx = 8;
const n = parseInt(input[idx++].trim(), 10);

function toPos(fileChar, rankChar) {
    const col = fileChar.charCodeAt(0) - 97;
    const rank = rankChar.charCodeAt(0) - 48;
    const row = 8 - rank;
    return [row, col];
}

const out = new Array(n);
for (let i = 0; i < n; i++) {
    const mv = input[idx++].trim();
    const [fr, fc] = toPos(mv[0], mv[1]);
    const [tr, tc] = toPos(mv[2], mv[3]);

    const piece = board[fr][fc];
    out[i] = piece;

    board[tr][tc] = piece;
    board[fr][fc] = '.';
}

process.stdout.write(out.join('') + '\n');