'use strict';
const fs = require('fs');

const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/).map(Number);
let idx = 0;

const legs = input[idx++];
let out = [];
for (let leg = 0; leg < legs; leg++) {
    const routes = input[idx++];
    let bestIndex = 1;
    let bestSpeed = input[idx++];
    let bestDist = input[idx++];

    for (let r = 2; r <= routes; r++) {
        const speed = input[idx++];
        const dist = input[idx++];

        if (dist * bestSpeed < bestDist * speed) {
            bestDist = dist;
            bestSpeed = speed;
            bestIndex = r;
        }
    }

    out.push(String(bestIndex));
}

process.stdout.write(out.join('\n'));