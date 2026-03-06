'use strict';

const MAX_INPUT_BYTES = 1024;

let input = '';
let received = 0;

process.stdin.setEncoding('utf8');

process.stdin.on('data', (chunk) => {
    received += Buffer.byteLength(chunk, 'utf8');
    if (received > MAX_INPUT_BYTES) {
        console.error('Input too large');
        process.exit(1);
    }

    input += chunk;
});

process.stdin.on('end', () => {
    try {
        const tokens = input.trim().split(/\s+/);

        if (tokens.length !== 2) {
            throw new Error('Expected exactly two integer values');
        }

        const [nStr, kStr] = tokens;

        if (!/^\d+$/.test(nStr) || !/^\d+$/.test(kStr)) {
            throw new Error('Inputs must be non-negative integers');
        }

        const n = BigInt(nStr);
        const k = BigInt(kStr);

        const period = (n % 2n === 0n) ? (n / 2n) : n;
        const answer = (k + 1n < period) ? (k + 1n) : period;

        process.stdout.write(`${answer.toString()}`);
    } catch (err) {
        console.error(`Invalid input: ${err.message}`);
        process.exit(1);
    }
});

process.stdin.resume();