const fs = require("fs");

const input = fs.readFileSync(0, "utf8").trim().split(/\s+/);
if (input.length < 4) process.exit(0);

const bS = BigInt(input[0]);
const bC = BigInt(input[1]);
const bO = BigInt(input[2]);
const bN = BigInt(input[3]);

const SN = bS + bN;
const COeq = bC + 2n * bO;

const k1 = SN / 3n;
const k2 = COeq / 6n;

const ans = k1 < k2 ? k1 : k2;
process.stdout.write(ans.toString());
