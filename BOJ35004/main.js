'use strict';
const fs = require('fs');

let n = Number(fs.readFileSync(0, 'utf8').trim());
let cnt = 0;

while (n > 0) {
    const b = n.toString(2);
    let rb = b.split('').reverse().join('');
    rb = rb.replace(/^0+/, '');
    const m = rb.length === 0 ? 0 : parseInt(rb, 2);
    n = m - 1;
    cnt++;
}

process.stdout.write(String(cnt));
process.exit(0);