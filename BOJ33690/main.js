const fs = require('fs');

const N = Number(fs.readFileSync(0, 'utf8').trim());
let answer = 1;

for (let d = 1; d <= 9; d++) {
    let x = 0;
    while (true) {
        x = x * 10 + d;
        if (x <= N) answer++;
        else break;
    }
}

process.stdout.write(answer.toString());
process.exit(0);