"use strict";
const fs = require("fs");

const data = fs.readFileSync(0);
let idx = 0;
const n = data.length;

function readInt() {
  while (idx < n) {
    const c = data[idx];
    if (c > 32) break;
    idx++;
  }
  let sign = 1;
  if (idx < n && data[idx] === 45) {
    sign = -1;
    idx++;
  }

  let val = 0;
  while (idx < n) {
    const c = data[idx];
    if (c <= 32) break;
    val = val * 10 + (c - 48);
    idx++;
  }

  return val * sign;
}

const a = readInt();
const b = readInt();
const c = readInt();

const move = new Int32Array(101);
for (let i = 0; i < b; i++) {
  const from = readInt();
  const to = readInt();
  if (from >= 1 && from <= 100) move[from] = to;
}

const pos = new Int32Array(a);
pos.fill(1);

let cur = 0;
let finished = false;

for (let i = 0; i < c; i++) {
  const roll = readInt();
  if (!finished) {
    let p = pos[cur] + roll;
    if (p > 100) p = 100;
    const jump = move[p];
    if (jump !== 0) p = jump;
    pos[cur] = p;

    if (p === 100) finished = true;

    cur++;
    if (cur === a) cur = 0;
  }
}

const CHUNK = 20000;
let out = [];
let count = 0;

for (let i = 0; i < a; i++) {
  out.push(`Position of player ${i + 1} is ${pos[i]}.`);
  count++;

  if (count === CHUNK) {
    process.stdout.write(out.join("\n") + "\n");
    out = [];
    count = 0;
  }
}

if (out.length > 0) {
  process.stdout.write(out.join("\n") + "\n");
}
