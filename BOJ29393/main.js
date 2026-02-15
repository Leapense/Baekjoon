const fs = require("fs");
const input = fs.readFileSync(0, "utf8").trim().split(/\s+/).map(Number);

let idx = 0;
const n = input[idx++];
const diff = new Array(1001).fill(0);

for (let i = 0; i < n; i++) {
    const v = input[idx++];
    const a = Math.abs(v);
    diff[a] += (v > 0 ? 1 : -1);
}

let ans = 0;
for (let x = 1; x <= 1000; x++) {
    if (diff[x] !== 0) {
        ans = (diff[x] === 1 ? -x : x);
        break;
    }
}

process.stdout.write(String(ans));
process.exit(0);