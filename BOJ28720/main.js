const fs = require('fs');

const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/);
const n = BigInt(input[0]);
const m = BigInt(input[1]);

const ans = (n - 1n) * (m - 1n);
process.stdout.write(ans.toString());
process.exit(0);