const fs = require("fs");

const input = fs.readFileSync(0, "utf8").trim().split(/\s+/).map(Number);
const w = input[0];
const h = input[1];
const r = input[2];

const visible = (Math.PI * r * r) / 4;
const invisible = w * h - visible;

let out = invisible.toFixed(10);
out = out.replace(/\.?0+$/, "");

console.log(out);
