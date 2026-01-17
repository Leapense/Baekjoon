const fs = require("fs");

const input = fs.readFileSync(0, "utf8").trim().split(/\s+/).map(Number);
let idx = 0;

const N = input[idx++];
const days = new Set();

for (let i = 0; i < N; i++) {
  days.add(input[idx++]);
}

console.log(days.size < 5 ? "YES" : "NO");
