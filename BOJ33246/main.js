'use strict';

const fs = require('fs');
const input = fs.readFileSync(0, 'utf8').trimEnd().split('\n');
let idx = 0;

const n = parseInt(input[idx++], 10);
const correct = (input[idx++] ?? '').trim();

function expectedAllowed(attempt, correct) {
    if (attempt.length !== correct.length) return false;

    let diff = 0;
    for (let i = 0; i < correct.length; i++) {
        if (attempt[i] !== correct[i]) {
            diff++;
            if (diff > 1) return false;
        }
    }
    return true;
}
let compromised = false;

for (let i = 0; i < n; i++) {
    const line = (input[idx++] ?? '').trim();
    if (!line) continue;

    const parts = line.split(' ');
    const attempt = parts[0];
    const result = parts[1];

    const exp = expectedAllowed(attempt, correct);
    const actual = (result === 'ALLOWED');

    if (exp !== actual) {
        compromised = true;
        break;
    }
}

process.stdout.write(compromised ? 'INTEGRITY OVERFLOW\n' : 'SYSTEM SECURE\n');