const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/).map(Number);
let idx = 0;

const N = input[idx++];
const cnt = [0, 0, 0, 0, 0];

for (let i = 0; i < N; i++) {
    cnt[input[idx++]]++;
}

let c1 = cnt[1];
let c2 = cnt[2];
let c3 = cnt[3];
let c4 = cnt[4];

let boxes = 0;
boxes += c4;
boxes += c3;
const match31 = Math.min(c3, c1);
c1 -= match31;

boxes += Math.floor(c2 / 2);
c2 %= 2;

if (c2 === 1) {
    boxes += 1;
    c1 = Math.max(0, c1 - 2);
}

boxes += Math.ceil(c1 / 4);
console.log(boxes.toString());
process.exit(0);