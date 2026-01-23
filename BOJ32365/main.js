'use strict';
const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/);

let idx = 0;
const T = parseInt(input[idx++], 10);
const N = parseInt(input[idx++], 10);

let out = [];
for (let t = 0; t < T; t++) {
    const s = input[idx++] || "";
    const freq = new Int32Array(26);

    for (let i = 0; i < N; i++) {
        const c = s.charCodeAt(i) - 97;
        freq[c]++;
    }

    let ok = true;
    let prevHeavy = (freq[s.charCodeAt(0) - 97] > 1);

    for (let i = 1; i < N; i++) {
        const curHeavy = (freq[s.charCodeAt(i) - 97] > 1);
        if (curHeavy === prevHeavy) {
            ok = false;
            break;
        }
        prevHeavy = curHeavy;
    }

    out.push(ok ? 'T' : 'F');
}

process.stdout.write(out.join('\n'));