const fs = require("fs");

const input = fs.readFileSync(0, "utf8").trim();
const N = BigInt(input);

const ans = 2n * N;
process.stdout.write(ans.toString());
