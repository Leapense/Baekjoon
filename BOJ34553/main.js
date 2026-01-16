const fs = require('fs');

const S = fs.readFileSync(0, 'utf8').trim();
let sum = 0;
let cur = 0;

for (let i = 0; i < S.length; i++) {
    if (i === 0) {
        cur = 1;
    } else {
        cur = (S.charCodeAt(i) > S.charCodeAt(i - 1)) ? (cur + 1) : 1;
    }

    sum += cur;
}

console.log(sum.toString());