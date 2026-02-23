'use strict';

const readline = require('readline');

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout,
    terminal: false
});

let n = null;
let count = 0;
const foundChars = new Set();

rl.on('line', (line) => {
    const tokens = line.trim().split(/\s+/);

    for (const token of tokens) {
        if (!token) continue;

        if (n === null) {
            n = parseInt(token, 10);
            if (isNaN(n) || n < 0) {
                console.error('Error: Invalid input count.');
                process.exit(1);
            }
            continue;
        }

        if (count < n) {
            foundChars.add(token[0].toLowerCase());
            count++;
        }
    }
});

rl.on('close', () => {
    if (n !== null) {
        let resultString = '';
        for (let i = 0; i < 26; i++) {
            const char = String.fromCharCode(97 + i);
            resultString += foundChars.has(char) ? char : '.';
        }

        const formatted = resultString.match(/.{1,6}/g);
        if (formatted) {
            process.stdout.write(formatted.join('\n'));
        }
    }
});