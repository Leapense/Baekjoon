const fs = require('fs');

const input = fs.readFileSync(0, 'utf8').trim().split(/\s+/).map(Number);
let idx = 0;

const n = input[idx++];

const a = [];
for (let i = 0; i < n; i++) a.push(input[idx++]);

let strictlyIncreasing = true;
for (let i = 0; i + 1 < n; i++) {
    if (a[i] >= a[i + 1]) {
        strictlyIncreasing = false;
        break;
    }
}

if (strictlyIncreasing) {
    console.log('0');
} else {
    let out = [];
    out.push(String(n));
    out.push(Array.from({ length: n }, (_, i) => String(i + 1)).join(' '));
    console.log(out.join('\n'));
}