'use strict';
const readline = require('readline');
const MAX_LINE_LENGTH = 65536;
const MAX_DIGITS = 10000;

const init = [0n, 1n, 2n, 3n];

function isNautilus(line) {
    if (line.length > MAX_LINE_LENGTH) {
        return false;
    }

    let idx = 0;
    const len = line.length;
    let count = 0;
    const buf = [0n, 0n, 0n, 0n];

    while (idx < len) {
        while (idx < len) {
            const c = line.charCodeAt(idx);
            if (c > 32) break;
            idx++;
        }
        if (idx >= len) break;

        let val = 0n;
        let digitCount = 0;

        while (idx < len) {
            const c = line.charCodeAt(idx);
            if (c < 48 || c > 57) break;

            digitCount++;
            if (digitCount > MAX_DIGITS) {
                return false;
            }

            val = val * 10n + BigInt(c - 48);
            idx++;
        }
        count++;
        if (count <= 4) {
            if (val !== init[count - 1]) return false;
        } else {
            const expected = buf[0] + buf[1] + buf[2] + buf[3];
            if (val !== expected) return false;
        }

        buf[(count - 1) & 3] = val;
    }

    return count > 0;
}

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout,
    terminal: false
});

let isFirstLine = true;
let n = 0;
let processed = 0;

rl.on('line', (line) => {
    if (line.endsWith('\r')) line = line.slice(0, -1);

    if (isFirstLine) {
        if (line.trim() === '') return;
        n = parseInt(line.trim(), 10);
        if (isNaN(n)) {
            process.exit(1);
        }
        isFirstLine = false;
        return;
    }

    if (processed < n) {
        const result = isNautilus(line) ? 'NAUTILUS' : 'SNAIL';
        process.stdout.write(result + '\n');
        processed++;
    }
});

rl.on('close', () => {
    process.exit(0);
});