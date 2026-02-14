const fs = require('fs');
const data = fs.readFileSync(0, 'utf8');
let idx = 0;
const L = data.length;

function nextInt() {
    while (idx < L) {
        const c = data.charCodeAt(idx);
        if (c > 32) break;
        idx++;
    }

    let sign = 1;
    if (idx < L && data[idx] === '-') {
        sign = -1;
        idx++;
    }

    let num = 0;
    while (idx < L) {
        const c = data.charCodeAt(idx);
        if (c <= 32) break;
        num = num * 10 + (c - 48);
        idx++;
    }
    return num * sign;
}

const n = nextInt();
const m = nextInt();

const wells = [];
for (let i = 1; i <= n; i++) {
    for (let j = 1; j <= m; j++) {
        const v = nextInt();
        if (v === 1) wells.push([i, j]);
    }
}

function best(rp, cp, rq, cq) {
    let ans = 0;
    if (rp < rq) ans = Math.max(ans, (rq - 1) * m);
    if (rp > rq) ans = Math.max(ans, (n - rq) * m);
    if (cp < cq) ans = Math.max(ans, n * (cq - 1));
    if (cp > cq) ans = Math.max(ans, n * (m - cq));
    return ans;
}

const [r1, c1] = wells[0];
const [r2, c2] = wells[1];

const ans = Math.max(
    best(r1, c1, r2, c2),
    best(r2, c2, r1, c1)
);

process.stdout.write(ans.toString());
process.exit(0);