'use strict';

const fs = require('fs');

const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/);
let idx = 0;

const n = parseInt(input[idx++], 10);
const m = parseInt(input[idx++], 10);

function toSeconds(t) {
    const hh = parseInt(t.slice(0, 2), 10);
    const mm = parseInt(t.slice(3, 5), 10);
    const ss = parseInt(t.slice(6, 8), 10);
    return hh * 3600 + mm * 60 + ss;
}

const arrivals = [];
for (let i = 0; i < n; i++)
    arrivals.push(toSeconds(input[idx++]));

const departures = [];
for (let i = 0; i < m; i++)
    departures.push(toSeconds(input[idx++]));

const s = parseInt(input[idx++], 10);

let best = Infinity;

for (let i = 0; i < n; i++) {
    for (let j = 0; j < m; j++) {
        const diff = departures[j] - arrivals[i];

        if (diff >= s) best = Math.min(best, diff);
    }
}

const ans = (best === Infinity ? -1 : best);
process.stdout.write(String(ans));
process.exit(0);