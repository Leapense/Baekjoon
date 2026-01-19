const fs = require("fs");

const input = fs.readFileSync(0, "utf8").trim().split(/\s+/);
const a1 = BigInt(input[0] || "0");
const a2 = BigInt(input[1] || "0");
const a3 = BigInt(input[2] || "0");
const a4 = BigInt(input[3] || "0");

const m12 = a1 < a2 ? a1 : a2;
const m34 = a3 < a4 ? a3 : a4;
const S = m12 + m34;

function isqrtBigInt(n) {
    if (n < 0n) throw new Error("negative");
    if (n < 2n) return n;

    let hi = 1n;
    while (hi * hi <= n) hi <<= 1n;
    let lo = hi >> 1n;

    while (lo + 1n < hi) {
        const mid = (lo + hi) >> 1n;
        const sq = mid * mid;
        if (sq <= n) lo = mid;
        else hi = mid;
    }

    return lo;
}

const ans = isqrtBigInt(S);
process.stdout.write(ans.toString());