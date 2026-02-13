'use strict';
const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trimEnd().split('\n');
if (input.length === 0) process.exit(0);

const [n, m, k] = input[0].trim().split(/\s+/).map(Number);
const docs = Array.from({ length: n }, () => []);

let cur = 0;
let buffer = '';

for (let i = 1; i <= m; i++) {
    const cmd = (input[i] ?? '').trim();

    if (cmd === 'Next') {
        cur = (cur + 1) % n;
    } else if (cmd === 'Backspace') {
        if (docs[cur].length > 0) docs[cur].pop();
    } else if (cmd === 'Copy') {
        const arr = docs[cur];
        const start = Math.max(0, arr.length - k);
        buffer = arr.slice(start).join('');
    } else if (cmd === 'Paste') {
        if (buffer.length > 0) {
            const arr = docs[cur];
            for (let j = 0; j < buffer.length; j++) arr.push(buffer[j]);
        }
    } else {
        if (cmd.length === 1) docs[cur].push(cmd);
    }
}

const arr = docs[cur];
if (arr.length === 0) {
    process.stdout.write('Empty');
} else {
    const start = Math.max(0, arr.length - k);
    process.stdout.write(arr.slice(start).join(''));
}

process.exit(0);