const fs = require('fs');

const input = fs.readFileSync(0, 'utf8').trim();
const n = BigInt(input);

const answer = n * (n + 1n) * (n + 2n) / 6n;
process.stdout.write(answer.toString());
process.exit(0);