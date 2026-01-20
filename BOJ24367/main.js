'use strict';

const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/).map(Number);
const M = input[0];
const N = input[1];
const w = input.slice(2, 6);

function canDoInK(k) {
    const totalAssignments = Math.pow(k, 4);
    for (let x = 0; x < totalAssignments; x++) {
        let tmp = x;
        const sum = Array(k).fill(0);
        const cnt = Array(k).fill(0);

        for (let i = 0; i < 4; i++) {
            const g = tmp % k;
            tmp = Math.floor(tmp / k);
            sum[g] += w[i];
            cnt[g] += 1;
        }

        let ok = true;
        for (let g = 0; g < k; g++) {
            if (cnt[g] === 0) { ok = false; break; }
            if (cnt[g] > M) { ok = false; break; }
            if (sum[g] > N) { ok = false; break; }
        }

        if (ok) return true;
    }

    return false;
}

let answer = 4;
for (let k = 1; k <= 4; k++) {
    if (canDoInK(k)) {
        answer = k;
        break;
    }
}

process.stdout.write(String(answer));