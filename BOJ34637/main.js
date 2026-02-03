const fs = require("fs");
const input = fs.readFileSync(0, "utf8").trim().split(/\s+/).map(Number);
let idx = 0;

const n = input[idx++];
const k = input[idx++];

const set = new Set();
for (let i = 0; i < n; i++) {
    set.add(input[idx++]);
}

const U = set.size;
const ans = Math.min(U, k);

process.stdout.write(String(ans));
process.exit(0);