const fs = require("fs");

const input = fs.readFileSync(0, "utf8").trim().split(/\s+/).map(Number);
let idx = 0;

const N = input[idx++];
const M = input[idx++];

const mid = (M + 1) / 2;

let bestDist = Infinity;
let bestR = -1;
let bestC = -1;

for (let r = 1; r <= N; r++) {
  for (let c = 1; c <= M; c++) {
    const seat = input[idx++];
    if (seat === 0) {
      const dist = r + Math.abs(mid - c);
      if (dist < bestDist) {
        bestDist = dist;
        bestR = r;
        bestC = c;
      }
    }
  }
}

if (bestR === -1) {
  console.log("-1");
} else {
  console.log(`${bestR} ${bestC}`);
}
