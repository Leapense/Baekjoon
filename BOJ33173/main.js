'use strict';
const fs = require('fs');

function solve() {
    const input = fs.readFileSync(0, 'utf8');
    let idx = 0;
    const n = input.length;

    function nextInt() {
        while (idx < n) {
            const c = input.charCodeAt(idx);
            if (c > 32) break;
            idx++;
        }

        let sign = 1;
        if (idx < n && input.charCodeAt(idx) === 45) {
            sign = -1;
            idx++;
        } 

        let num = 0;
        while (idx < n) {
            const c = input.charCodeAt(idx);
            if (c <= 32) break;
            num = num * 10 + (c - 48);
            idx++;
        }
        return num * sign;
    }

    const H = nextInt();
    const W = nextInt();
    const Q = nextInt();

    const Hm1 = H - 1;
    const Wm1 = W - 1;
    const B = Hm1 * Wm1;
    const INF = Q + 1;

    const coverTime = new Int32Array(B);
    coverTime.fill(INF);

    const paintTimes = new Array(B);
    const paintColors = new Array(B);

    for (let t = 1; t <= Q; t++) {
        const type = nextInt();
        const x = nextInt() - 1;
        const y = nextInt() - 1;
        const bid = x * Wm1 + y;

        if (type === 1) {
            const c = nextInt();
            let pt = paintTimes[bid];
            if (pt === undefined) {
                paintTimes[bid] = [t];
                paintColors[bid] = [c];
            } else {
                pt.push(t);
                paintColors[bid].push(c);
            }
        } else {
            if (coverTime[bid] > t) coverTime[bid] = t;
        }
    }

    function lastBefore(arr, T) {
        let l = 0, r = arr.length - 1;
        let ans = -1;
        while (l <= r) {
            const m = (l + r) >> 1;
            if (arr[m] < T) { ans = m; l = m + 1; }
            else r = m - 1;
        }
        return ans;
    }

    const lines = [];
    const bids = new Int32Array(4);

    for (let i = 0; i < H; i++) {
        const row = new Array(W);
        for (let j = 0; j < W; j++) {
            let t0 = INF;
            let cnt = 0;

            if (i > 0 && j > 0) {
                const b = (i - 1) * Wm1 + (j - 1);
                const ct = coverTime[b];
                if (ct < t0) t0 = ct;
                bids[cnt++] = b;
            }
            if (i > 0 && j < Wm1) {
                const b = (i - 1) * Wm1 + j;
                const ct = coverTime[b];
                if (ct < t0) t0 = ct;
                bids[cnt++] = b;
            }
            if (i < Hm1 && j > 0) {
                const b = i * Wm1 + (j - 1);
                const ct = coverTime[b];
                if (ct < t0) t0 = ct;
                bids[cnt++] = b;
            }
            if (i < Hm1 && j < Wm1) {
                const b = i * Wm1 + j;
                const ct = coverTime[b];
                if (ct < t0) t0 = ct;
                bids[cnt++] = b;
            }

            let bestTime = -1;
            let bestColor = 0;

            for (let k = 0; k < cnt; k++) {
                const b = bids[k];
                const pt = paintTimes[b];
                if (pt === undefined) continue;
                const pos = lastBefore(pt, t0);
                if (pos !== -1) {
                    const time = pt[pos];
                    if (time > bestTime) {
                        bestTime = time;
                        bestColor = paintColors[b][pos];
                    }
                }
            }

            row[j] = String(bestColor);
        }

        lines.push(row.join(' '));
    }

    process.stdout.write(lines.join('\n'));
}

solve();