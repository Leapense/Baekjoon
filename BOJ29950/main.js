'use strict';

const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/);

const A = BigInt(input[0]);
const B = BigInt(input[1]);

function fullMonths(n) {
    let lo = 0n;
    let hi = 2000000n;

    while (lo < hi) {
        const mid = (lo + hi + 1n) >> 1n;
        const days = mid * (mid + 1n) / 2n;
        if (days <= n) {
            lo = mid;
        } else {
            hi = mid - 1n;
        }
    }

    return lo;
}

function prefix(n) {
    if (n <= 0n) return 0n;

    const m = fullMonths(n);
    const usedDays = m * (m + 1n) / 2n;
    const r = n - usedDays;

    const full = m * (m + 1n) * (m + 2n) / 6n;
    const partial = r * (r + 1n) / 2n;

    return full + partial;
}

const ans = prefix(B) - prefix(A - 1n);
process.stdout.write(String(ans));
process.exit(0);