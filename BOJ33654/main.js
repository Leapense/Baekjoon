const fs = require("fs");

const input = fs.readFileSync(0, "utf8").trim().split(/\s+/);
let idx = 0;

const N = Number(input[idx++]);
let maxSoFar = -Infinity;
const res = [];

for (let i = 0; i < N; i++) {
    const x = Number(input[idx++]);
    if (x >= maxSoFar) {
        res.push(x);
        maxSoFar = x;
    }
}

console.log(res.join(" "));