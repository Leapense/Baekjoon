const fs = require('fs');

const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/).map(Number);
const T = input[0];

let idx = 1;
let answer = [];

for (let i = 0; i < T; i++) {
    const A = input[idx++];
    answer.push(String(A - 1));
}

console.log(answer.join('\n'));
process.exit(0);