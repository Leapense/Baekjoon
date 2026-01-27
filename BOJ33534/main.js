const fs = require("fs");

const input = fs.readFileSync(0, "utf8").trim();
if (input.length === 0) process.exit(0);

const f = Number(input);
const L = 2 * Math.sqrt(Math.PI * f);

const units = Math.ceil(L * 10 - 1e-9);
const ans = (units / 10).toFixed(1);

console.log(ans);