'use strict';

const fs = require('fs');
const [x1, y1, x2, y2] = fs.readFileSync(0, 'utf8').trim().split(/\s+/).map(Number);

function isCorner(x, y) {
    return (x === 0 || x === 2024) && (y === 0 || y === 2024);
}

let answer = 0;
if (!isCorner(x1, y1)) answer++;
if (!isCorner(x2, y2)) answer++;

process.stdout.write(String(answer));
process.exit(0);