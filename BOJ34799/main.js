const fs = require("fs");
const data = fs.readFileSync(0, "utf8").trim().split(/\s+/);
if (data.length === 0) process.exit(0);

const s = data[0];
const N = BigInt(data[1]);

const M = N - 1n;
const MOD = 26n;
const R_IDX = 17;

let total = 0n;
for (const ch of s) {
    const c = ch.charCodeAt(0) - 97;
    let k = (c - R_IDX) % 26;
    if (k < 0) k += 26;
    const kb = BigInt(k);

    if (M >= kb) {
        total += 1n + (M - kb) / MOD;
    }
}

process.stdout.write(total.toString());
process.exit(0);