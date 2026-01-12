const fs = require("fs");

const data = fs.readFileSync(0, "utf8").trim();
if (!data) process.exit(0);

const arr = data.split(/\s+/).map(Number);
let p = 0;

const T = arr[p++];
const out = [];

for (let tc = 0; tc < T; tc++) {
  const N = arr[p++],
    M = arr[p++],
    L = arr[p++];

  let minS = Infinity;
  for (let i = 0; i < N; i++) {
    const s = arr[p++];
    if (s !== -1 && s < minS) minS = s;
  }

  let freezeTime = M - L;
  if (minS !== Infinity) {
    freezeTime = Math.min(freezeTime, minS);
  }

  const X = M - freezeTime;
  const unit = X === 1 ? "minute" : "minutes";

  out.push(`The scoreboard has been frozen with ${X} ${unit} remaining.`);
}

console.log(out.join("\n"));
