const fs = require('fs');

const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/).map(Number);
const N = input[0];
let cnt1 = 0;
let cnt2 = 0;

for (let i = 1; i <= N; i++) {
    if (input[i] === 1) cnt1++;
    else cnt2++;
}

if (cnt1 >= cnt2 && (cnt1 - cnt2) % 3 === 0) {
    process.stdout.write('Yes');
} else {
    process.stdout.write('No');
}

process.exit(0);