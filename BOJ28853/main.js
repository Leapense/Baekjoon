const fs = require('fs');

const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/).map(Number);
const [a, b, c, d] = input;

const K = a + b;
const S = c + d;

const maxX = Math.min(K, S);
const parity = K % 2;

let ans = Infinity;

for (let x = 0; x <= maxX; x++) {
    if (x % 2 !== parity) continue;
    const discomfort = Math.abs(b - x) + Math.abs(d - x);
    if (discomfort < ans) ans = discomfort;
}

process.stdout.write(ans.toString());
process.exit(0);