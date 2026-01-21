'use strict';

const fs = require('fs');

const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/).map(Number);
let idx = 0;

const n = input[idx++];
const m = input[idx++];

const a = Array.from({ length: n }, () => Array(m));
for (let i = 0; i < n; i++) {
    for (let j = 0; j < m; j++) {
        a[i][j] = input[idx++];
    }
}

const dirs = [
    [-1, 0],
    [1, 0],
    [0, -1],
    [0, 1],
];

let ans = 0;
for (let i = 0; i < n; i++) {
    for (let j = 0; j < m; j++) {
        const cur = a[i][j];
        let ok = true;
        for (const [di, dj] of dirs) {
            const ni = i + di;
            const nj = j + dj;
            if (ni < 0 || ni >= n || nj < 0 || nj >= m) continue;
            if (cur <= a[ni][nj]) {
                ok = false;
                break;
            }
        }

        if (ok) ans++;
    }
}

process.stdout.write(String(ans));