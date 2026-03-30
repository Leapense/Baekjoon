const fs = require('fs');

const input = fs.readFileSync(0, 'utf8').trim();
const N = Number(input);

const base = 'SSH';
let result = '';

for (let i = 0; i < N; i++) {
    result += base[i % 3];
}

process.stdout.write(result);
process.exit(0);