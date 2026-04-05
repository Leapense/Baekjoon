'use strict';

const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/).map(Number);

let idx = 0;
const Y0 = input[idx++];
const M0 = input[idx++];
const D0 = input[idx++];

const Y1 = input[idx++];
const M1 = input[idx++];
const D1 = input[idx++];

const N = input[idx++];

const hosts = [];
for (let i = 0; i < N; i++) {
    const y = input[idx++];
    const m = input[idx++];
    const d = input[idx++];
    hosts.push({y, m, d});
}

function compareDate(y1, m1, d1, y2, m2, d2) {
    if (y1 !== y2) return y1 < y2 ? -1 : 1;
    if (m1 !== m2) return m1 < m2 ? -1 : 1;
    if (d1 !== d2) return d1 < d2 ? -1 : 1;
    return 0;
}

let todayJoy = 0;
for (const host of hosts) {
    if (host.m === M0 && host.d === D0) {
        todayJoy = Y0 - host.y;
        break;
    }
}

let answer = 0;

for (const host of hosts) {
    for (let year = Y0; year <= Y1; year++) {
        const cmpStart = compareDate(year, host.m, host.d, Y0, M0, D0);
        const cmpEnd = compareDate(year, host.m, host.d, Y1, M1, D1);

        if (cmpStart <= 0) continue;
        if (cmpEnd > 0) continue;

        const joy = year - host.y;
        if (joy > todayJoy) answer++;
    }
}

console.log(answer.toString());
process.exit(0);