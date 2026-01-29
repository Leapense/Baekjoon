'use strict';

const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trim().split(/\r?\n/);
const W = (input[0] || '').trim();
const S = (input[1] || '').trim();

const seen = Array(26).fill(false);
let key = '';

for (const ch of W) {
    const idx = ch.charCodeAt(0) - 65;
    if (!seen[idx]) {
        seen[idx] = true;
        key += ch;
    }
}

let cipher = key;
for (let i = 0; i < 26; i++) {
    if (!seen[i]) cipher += String.fromCharCode(65 + i);
}

let out = '';
for (const ch of S) {
    const idx = ch.charCodeAt(0) - 65;
    out += cipher[idx];
}

process.stdout.write(out + '\n');